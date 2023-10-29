package com.ahamed.miband6.ui;

import static android.provider.Settings.Global.DEVICE_NAME;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ahamed.miband6.databinding.FragmentWatchBinding;
import com.ahamed.miband6.model.BandModel;
import com.ahamed.miband6.viewmodel.LocalViewModel;
import com.bumptech.glide.Glide;
import com.khmelenko.lab.miband.MiBand;

import java.util.Arrays;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class WatchFragment extends Fragment {
    private static BandModel selectedBand = null;
    private static final int PERMISSION_REQUEST_CODE = 1234;

    public WatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentWatchBinding binding = FragmentWatchBinding.inflate(inflater, container, false);
        LocalViewModel localViewModel = new ViewModelProvider(requireActivity()).get(LocalViewModel.class);
        MiBand band = new MiBand(requireActivity());

        localViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), bandModel -> {
            selectedBand = bandModel;
            Glide.with(requireActivity()).load(selectedBand.getImage()).into(binding.bandImage);
        });


        binding.btnDownload.setOnClickListener(new View.OnClickListener() {

            @SuppressLint({"MissingPermission", "CheckResult"})
            @Override
            public void onClick(View v) {
                //  bluetoothDevice();
                band.onConnectionEstablished();

                band.pair().subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(Void unused) {
                        Log.d("TAG", "onNext: " + unused);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: ");
                    }
                });

                Intent intent = requireActivity().getIntent();
                BluetoothDevice device = intent.getParcelableExtra("device");

            }
        });


        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());


        return binding.getRoot();
    }

    @SuppressLint("MissingPermission")
    private void bluetoothDevice() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Log.d("TAG", "bluetoothDevice: not found");
            Toast.makeText(getActivity(), "bluetoothDevice: not found", Toast.LENGTH_SHORT).show();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                // Bluetooth is not enabled, prompt the user to enable it
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, PERMISSION_REQUEST_CODE);
                Toast.makeText(getActivity(), "permission plz", Toast.LENGTH_SHORT).show();
            } else {

                @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                Toast.makeText(getActivity(), "" + pairedDevices.size(), Toast.LENGTH_SHORT).show();

                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice device : pairedDevices) {

                        Toast.makeText(getActivity(), "" + device.getName(), Toast.LENGTH_SHORT).show();

                        if (device.getName().equals(DEVICE_NAME)) {
                            Log.d("TAG", "bluetoothDevice: " + device.getName() + Arrays.toString(device.getUuids()));
                            Toast.makeText(getActivity(), "bluetoothDevice:" + device.getName() + Arrays.toString(device.getUuids()), Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bluetoothDevice();
            } else {
                // Permission denied, handle accordingly
                Log.e("TAG", "Permission denied");
            }
        }
    }

}
package com.ahamed.miband6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ahamed.miband6.adapter.BandAdapter;
import com.ahamed.miband6.databinding.FragmentHomeBinding;
import com.ahamed.miband6.model.BandModel;
import com.ahamed.miband6.utils.Fab;
import com.ahamed.miband6.utils.FileDownloader;
import com.ahamed.miband6.viewmodel.WatchViewModel;
import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private static final int PERMISSION_REQUEST_CODE = 1;
    private List<String> imgList;
    private List<BandModel> list;
    private FileDownloader fileDownloader;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        WatchViewModel watchViewModel = new ViewModelProvider(requireActivity()).get(WatchViewModel.class);
        fileDownloader = new FileDownloader();

        list = new ArrayList<>();
        BandAdapter adapter = new BandAdapter(list, requireActivity());

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rvBand.setLayoutManager(layoutManager);
        binding.rvBand.setAdapter(adapter);


        watchViewModel.allWatch(R.raw.watch7).observe(getViewLifecycleOwner(), bandModels -> {
            list.clear();
            list.addAll(bandModels);
            Collections.shuffle(list);
            adapter.notifyDataSetChanged();


            /*if (checkPermission()) {
                makeStringList(list);
            } else {
                requestPermission();
            }*/
        });

        int sheetColor = ContextCompat.getColor(requireActivity(), R.color.black_background);
        int fabColor = ContextCompat.getColor(requireActivity(), R.color.primary_color);

        MaterialSheetFab<Fab> materialSheetFab = new MaterialSheetFab<>(binding.fab, binding.fabSheet, binding.overlay, sheetColor, fabColor);


        return binding.getRoot();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, download images
                makeStringList(list);
            } else {
                // Permission denied, handle accordingly
                Log.e("TAG", "Permission denied");
            }
        }
    }

    private void makeStringList(List<BandModel> list) {
        imgList = new ArrayList<>();
        for (BandModel bandModel : list) {
            imgList.add(bandModel.getUrl());
        }
        fileDownloader.downloadImages(imgList);
    }


}
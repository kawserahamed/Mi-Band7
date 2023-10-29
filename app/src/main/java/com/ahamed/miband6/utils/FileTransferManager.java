package com.ahamed.miband6.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class FileTransferManager {
    private Context context;

    public FileTransferManager(Context context) {
        this.context = context;
    }

    public void transferFileToBluetoothWatch(File file, BluetoothDevice watchDevice) {
        try {
            // Establish a Bluetooth connection with the watch
            BluetoothSocket socket = connectToWatch(watchDevice);

            // Transfer the binary file to the watch
            transferFileOverBluetooth(socket, file);

            // Close the socket and perform any cleanup if needed
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @SuppressLint("MissingPermission")
    private BluetoothSocket connectToWatch(BluetoothDevice watchDevice) throws IOException {
        // Use the Android Bluetooth API to connect to the Bluetooth watch
        BluetoothSocket socket = watchDevice.createRfcommSocketToServiceRecord(UUID.fromString("YOUR_UUID_HERE"));
        socket.connect();
        return socket;
    }

    private void transferFileOverBluetooth(BluetoothSocket socket, File file) throws IOException {
        // Transfer the file to the Bluetooth watch using the socket
        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = socket.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.flush();
        fileInputStream.close();
    }
}

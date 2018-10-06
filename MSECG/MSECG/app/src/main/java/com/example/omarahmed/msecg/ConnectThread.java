package com.example.omarahmed.msecg;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by Omar Ahmed on 10/6/2018.
 */

public class ConnectThread extends Thread {

    private final BluetoothSocket mBluetoothSocket;
    private  final BluetoothDevice mBluetoothDevice;
    private final UUID BOARDUUID= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothAdapter mBluetoothAdapter =BluetoothAdapter.getDefaultAdapter();


    public ConnectThread(BluetoothDevice device)
    {
        this.mBluetoothDevice = device;
        BluetoothSocket tem=null;
        try {
            tem=device.createRfcommSocketToServiceRecord(BOARDUUID);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        mBluetoothSocket=tem;
    }

    public void run()
    {
        mBluetoothAdapter.cancelDiscovery();

        try {
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            mBluetoothSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            try {
                mBluetoothSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, "Could not close the client socket", closeException);
            }
            return;
        }

        // The connection attempt succeeded. Perform work associated with
        // the connection in a separate thread.
        manageMyConnectedSocket(mBluetoothSocket);
    }

    private void manageMyConnectedSocket(BluetoothSocket mBluetoothSocket) {

        ConnectedThread connected=new ConnectedThread(mBluetoothSocket);


    }

    public void cancel() {
        try {
            mBluetoothSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the client socket", e);
        }
    }



}

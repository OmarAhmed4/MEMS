package com.example.omarahmed.msecg;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String action =intent.getAction();
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            //Device found
        try {
            if (!Bluetooth_Activity.listViewContents1.contains(device)) {
                if (device != null) {
                    String deviceName = device.getName();
                    Bluetooth_Activity.dataOfList.add(deviceName);
                    Bluetooth_Activity.listViewContents1.add(device);
                    Bluetooth_Activity.items.notifyDataSetChanged();
                }
            }
        }catch (Exception e)
                {
                    Log.e("Firebase","Error Of That device");

                }

        }
        else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            //Device is now connected
            Toast.makeText(context, "ACTION_ACL_CONNECTED", Toast.LENGTH_SHORT).show();
        }
        else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            //Done searching
            Toast.makeText(context, "ACTION_DISCOVERY_FINISHED", Toast.LENGTH_SHORT).show();

        }
        else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
            //Done searching
            Toast.makeText(context, "ACTION_DISCOVERY_STARTED", Toast.LENGTH_SHORT).show();

        }
        else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
            //Device is about to disconnect
            Toast.makeText(context, "ACTION_ACL_DISCONNECT_REQUESTED", Toast.LENGTH_SHORT).show();

        }
        else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            //Device has disconnected
            Toast.makeText(context, "ACTION_ACL_DISCONNECTED", Toast.LENGTH_SHORT).show();

        }
        else if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED))
        {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    Toast.makeText(context, "STATE_OFF", Toast.LENGTH_SHORT).show();

                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Toast.makeText(context, "STATE_TURNING_OFF", Toast.LENGTH_SHORT).show();
                    Bluetooth_Activity.listViewContents1.clear();
                    Bluetooth_Activity.dataOfList.clear();
                    Bluetooth_Activity.items.notifyDataSetChanged();

                    break;
                case BluetoothAdapter.STATE_ON:
                    Toast.makeText(context, "STATE_ON", Toast.LENGTH_SHORT).show();

                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Toast.makeText(context, "STATE_TURNING_ON", Toast.LENGTH_SHORT).show();

                    break;
            }

        }



        //throw new UnsupportedOperationException("Not yet implemented");
    }
}

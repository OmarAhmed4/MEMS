package com.example.omarahmed.msecg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        int status = NetworkUtil.getConnectivityStatusString(context);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {

                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
                Log.e("firebase","No Connection");

            } else {
                Toast.makeText(context, "Now Connected To Internet", Toast.LENGTH_SHORT).show();
            }
        }


    }
}

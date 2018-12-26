package com.example.omarahmed.msecg;

import android.bluetooth.BluetoothSocket;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.widget.Toast;

public class Setting_Preference_activity extends AppCompatActivity  {

    public static BluetoothSocket bluetoothSocket;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,new Preference_Settings()).commit();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false); // intialization

       //TODO : Listener of preferences

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("power_switch"))
                {
                    boolean led=sharedPreferences.getBoolean("power_switch",false);
                    Toast.makeText(Setting_Preference_activity.this,"power is "+String.valueOf(led),Toast.LENGTH_LONG).show();
                }

                else if(key.equals("led_switch"))
                {
                    boolean led=sharedPreferences.getBoolean("led_switch",false);
                    Toast.makeText(Setting_Preference_activity.this,"led is "+String.valueOf(led),Toast.LENGTH_LONG).show();
                }
                else if(key.equals("buzzer_switch"))
                {
                    boolean led=sharedPreferences.getBoolean("buzzer_switch",false);
                    Toast.makeText(Setting_Preference_activity.this,"buzzer is "+String.valueOf(led),Toast.LENGTH_LONG).show();
                }
                else if(key.equals("list_of_modes"))
                {
                    String led=sharedPreferences.getString("list_of_modes","normal");
                    Toast.makeText(Setting_Preference_activity.this,"mode is : "+led,Toast.LENGTH_LONG).show();
                }




            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

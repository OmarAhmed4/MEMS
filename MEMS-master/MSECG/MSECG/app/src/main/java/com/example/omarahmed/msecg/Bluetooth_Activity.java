package com.example.omarahmed.msecg;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Bluetooth_Activity extends AppCompatActivity {

    //TODO:  Stay Alert to the Value Being Used to Send it to the simulator
    //TODO:  Connect to Arduino code to try it virtually

    private static final int BLUETOOTH_REQUEST =100 ;
    public static ArrayList<String> dataOfList=new ArrayList<>();
    public static ArrayAdapter<String> items;
    public static ArrayList<BluetoothDevice> listViewContents1=new ArrayList<BluetoothDevice>();
    BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
    ConnectionReceiver receiver=new ConnectionReceiver();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_);
        TextView bluetooth_text= (TextView) findViewById(R.id.textView2);
        Button bluetooth_button= (Button) findViewById(R.id.button2);


        //Intent Filter of Bluetooth


        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(String.valueOf(BluetoothDevice.ERROR));
        registerReceiver(receiver,filter);




        //list view
        items=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataOfList);
        ListView listView= (ListView) findViewById(R.id.list_view);
        listView.setAdapter(items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listViewContents1.get(position)!=null) {
                    Thread connect = new ConnectThread(listViewContents1.get(position), Bluetooth_Activity.this);
                    connect.start();
                }
                else
                {
                    Toast.makeText(Bluetooth_Activity.this, "Not available device ", Toast.LENGTH_SHORT).show();

                }
                // we try to connect right now continue the UI u want



            }
        });

        //bluetooth acting
        if(mBluetoothAdapter==null);
        else{ if(mBluetoothAdapter.isEnabled())
         {   bluetooth_button.setText("Search");
             bluetooth_text.setText("Start Serarching");
              search_button(bluetooth_button);
         }
             if(!mBluetoothAdapter.isEnabled())
             {
                  search_button(bluetooth_button);
             }
        }

        turnOnReceiver();






    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
        if(mBluetoothAdapter!=null)mBluetoothAdapter.disable();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==BLUETOOTH_REQUEST)
        {

            if(resultCode==RESULT_OK)
            {   turnOnReceiver();
                dataOfList.clear();
                listViewContents1.clear();
                Set<BluetoothDevice> mPairedbluetoothDvices= mBluetoothAdapter.getBondedDevices();
                if(mPairedbluetoothDvices.size()>0)
                {
                    for(BluetoothDevice d:mPairedbluetoothDvices)
                    {
                        listViewContents1.add(d);
                        String deviceName=d.getName();
                        dataOfList.add(deviceName+"  is Paired Device");
                        items.notifyDataSetChanged();

                    }
                }
            }
        }

        if(requestCode==RESULT_CANCELED)
        {


        }

    }




    public void search_button(View view)
    {
            //add UI

        bluetoothActing();





    }




    public void bluetoothActing()
    {
        Button bluetooth_button= (Button) findViewById(R.id.button2);
        TextView text_of_bluetooth= (TextView) findViewById(R.id.textView2);


        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter==null)
        {
            listViewContents1.clear();
            dataOfList.clear();
            bluetooth_button.setEnabled(false);
            text_of_bluetooth.setText("No Bluetooth In this Device");
            Toast t=Toast.makeText(this,"No Bluetooth",Toast.LENGTH_LONG);
            t.show();
        }
        else {
            bluetooth_button.setEnabled(true);
            if (!mBluetoothAdapter.isEnabled())
            {
                dataOfList.clear();
                listViewContents1.clear();

                Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enable, BLUETOOTH_REQUEST);
            }
            if (mBluetoothAdapter.isEnabled())
            {
                mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
                mBluetoothAdapter.startDiscovery();
                dataOfList.clear();

                Set<BluetoothDevice> mPairedbluetoothDvices= mBluetoothAdapter.getBondedDevices();
                if(mPairedbluetoothDvices.size()>0)
                {
                    for(BluetoothDevice d:mPairedbluetoothDvices)
                    {
                        listViewContents1.add(d);
                        String deviceName=d.getName();
                        dataOfList.add(deviceName+"  is Paired Device");
                        items.notifyDataSetChanged();


                    }


                }


            }


        }


    }


    public void turnOnReceiver()
    {
        if(mBluetoothAdapter!=null) {
            int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
            mBluetoothAdapter.startDiscovery();

            if (mBluetoothAdapter.isDiscovering()) {

            }


            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(receiver, filter);
        }
    }



    public void bluetooth_states(String status)
    {



    }







}   // end of class

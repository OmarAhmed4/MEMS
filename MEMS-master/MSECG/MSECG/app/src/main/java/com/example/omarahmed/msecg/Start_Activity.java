    package com.example.omarahmed.msecg;

    import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

    public class Start_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterOfRecycleview.ListOfRecycleviewClicked {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("msecg-d1281").child("store");
        ArrayList<StoreData> arrayListOfData=new ArrayList<>();
        AdapterOfRecycleview adapterOfRecycleview;
        NetworkConnectionReceiver networkConnectionReceiver=new NetworkConnectionReceiver();

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // NETWORK CONNECTION BROADCAST    ///////// not connected to the filter yet      //// has been added Successfully

        IntentFilter connIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        connIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        try {
            registerReceiver(networkConnectionReceiver, connIntentFilter);
        }catch (Exception e)
        {
            Log.e("firebase", String.valueOf(e));
        }





        // firebase acting
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.getKey();
                    reitrivingDataFromFirebase(name);
                    // Log.d("firebase name",name);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });




        // drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //recycleview
        View viewFirstInclude = findViewById(R.id.app_bar_start_1);
        View viewSecondInclude = findViewById(R.id.content_start_app_bar);
        RecyclerView start_recycleview= (RecyclerView) findViewById(R.id.recycleview_of_start);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        start_recycleview.setLayoutManager(layoutManager);
        start_recycleview.setHasFixedSize(true);
        adapterOfRecycleview=new AdapterOfRecycleview(this,arrayListOfData);
        start_recycleview.setAdapter(adapterOfRecycleview);




    }


        @Override
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(networkConnectionReceiver);
        }



        @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_manage) {

            Intent intent =new Intent(Start_Activity.this,LineChartTest.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {
            Intent intent =new Intent(Start_Activity.this,Bluetooth_Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:010456789"));
            startActivity(intent);
        }else if(id == R.id.pieChartmenu){
            Intent intent =new Intent(Start_Activity.this,Piechart_Test.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListItemOfRecycleviewClick(int clickedNumber) {

            // every thing is okay
        Context context=getApplicationContext();
        Intent intent=new Intent (context,Devices_Details.class);
        StoreData s=arrayListOfData.get(clickedNumber);
       intent.putExtra(String.valueOf(R.string.device_details),  arrayListOfData.get(clickedNumber)); // this is the problem right now
        startActivity(intent);

    }



    public void  reitrivingDataFromFirebase(String keyOfChild)
    {

        myRef.child(keyOfChild).child("Device details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            if(dataSnapshot !=null) {
                StoreData storeData = (StoreData) dataSnapshot.getValue(StoreData.class);
                arrayListOfData.add(storeData);
               // Log.d("firebase",storeData.toString());
                adapterOfRecycleview.notifyChanged(arrayListOfData);
                adapterOfRecycleview.notifyDataSetChanged();
                //Log.d("firebase", String.valueOf(adapterOfRecycleview.getItemCount()));
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addNewDeviceToFirebase(StoreData storeData)
    {

        Map<String, StoreData> users = new HashMap<>();
        users.put("Device details",storeData);
        myRef.push().setValue(users);
    }


}

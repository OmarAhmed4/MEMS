package com.example.omarahmed.msecg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class Devices_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices__details);

        ImageView imageView=(ImageView)findViewById(R.id.image_details);
        TextView name = (TextView) findViewById(R.id.device_name);
        TextView price = (TextView) findViewById(R.id.device_price);
        TextView details = (TextView) findViewById(R.id.device_details);
        TextView warranty = (TextView) findViewById(R.id.device_warranty);
        TextView time = (TextView) findViewById(R.id.device_time);
        TextView contact = (TextView) findViewById(R.id.device_contact);
        TextView brand = (TextView) findViewById(R.id.device_brand);


        StoreData storeData=(StoreData) getIntent().getSerializableExtra(String.valueOf(R.string.device_details));
        Picasso.with(this).load(storeData.image_url).placeholder(R.drawable.medical_icon).memoryPolicy(MemoryPolicy.NO_STORE)
        .error(R.drawable.ic_menu_camera).into(imageView);
        name.setText(storeData.name);
        price.setText("Price : "+storeData.price);
        details.setText("Details : "+storeData.details);
        warranty.setText("Wrranty : "+storeData.warranty);
        time.setText("Time Added : "+storeData.time);
        contact.setText("Contacts : "+storeData.contact);
        brand.setText("Brand : "+storeData.brand);


    }
}

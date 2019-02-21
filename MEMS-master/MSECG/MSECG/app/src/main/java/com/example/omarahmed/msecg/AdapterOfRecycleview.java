package com.example.omarahmed.msecg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Omar Ahmed on 10/3/2018.
 */

public class AdapterOfRecycleview extends RecyclerView.Adapter<AdapterOfRecycleview.StoreAdapter> {

    public ListOfRecycleviewClicked mListOfRecycleviewClicked;

    ArrayList<StoreData> arrayListOfData=new ArrayList<>();

    public AdapterOfRecycleview(ListOfRecycleviewClicked listener,ArrayList<StoreData> arrayListOfData) {
        mListOfRecycleviewClicked=listener;

        this.arrayListOfData=arrayListOfData;

    }

    @Override
    public StoreAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_view_content_of_start_activity,parent,false);
        StoreAdapter storeAdapter=new StoreAdapter(view);
        return storeAdapter;
    }

    @Override
    public void onBindViewHolder(StoreAdapter holder, int position) {


            holder.bind(position);

            Log.d("image","fault in installing image");

    }



    @Override
    public int getItemCount() {
        return arrayListOfData.size();
    }

    public class StoreAdapter extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView device_image;
        TextView device_name;
        TextView device_details;


        public StoreAdapter(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

             device_image= (ImageView) itemView.findViewById(R.id.device_image);
             device_name= (TextView) itemView.findViewById(R.id.device_name);
             device_details= (TextView) itemView.findViewById(R.id.device_details);

        }

        public void bind(int listIndex)  {
                // to fill data of recycleview

            //URL url = new URL(arrayListOfData.get(listIndex).image_url);
            //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            Picasso.with((Context) mListOfRecycleviewClicked).load(arrayListOfData.get(listIndex).image_url).placeholder(R.drawable.medical_icon).memoryPolicy(MemoryPolicy.NO_STORE)
            .error(R.drawable.ic_menu_camera).into(device_image);


//            device_image.setImageBitmap(bmp);
//            device_image.setImageResource(R.drawable.medical_icon);
            device_name.setText(arrayListOfData.get(listIndex).name);
            device_details.setText(arrayListOfData.get(listIndex).details);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            mListOfRecycleviewClicked.onListItemOfRecycleviewClick(position);
        }
    } // end of Store adapter

    public interface ListOfRecycleviewClicked
    {

        void onListItemOfRecycleviewClick(int clickedNumber);


    }

    public void notifyChanged(ArrayList<StoreData>arrayListOfData)
    {
        this.arrayListOfData=arrayListOfData;
    }


}

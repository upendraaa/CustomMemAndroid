package com.example.upendra.testapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import interview.upendra.com.custommemory.Customer;
import interview.upendra.com.custommemory.ImageUtil;
import interview.upendra.com.custommemory.Location;
import interview.upendra.com.custommemory.R;
import interview.upendra.com.custommemory.ViewClickListener;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ImageViewHolder> {


    public List<Location> locationList;
    private Context context;



    ViewClickListener viewClickListener;

    private boolean isOnline;


    public LocationAdapter(Activity activity, Customer customer)
    {
        this.context = activity;
        this.locationList = customer.locations;

        viewClickListener = (ViewClickListener) activity;
        isOnline = ImageUtil.isNetworkAvailable(activity);
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                parent,false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {

        final Location location = locationList.get(position);

       // holder.imageView.setImageURI(location.image);

      if(isOnline)
      {
          Glide.with(context)
                  .load(location.url)
                  .into(holder.imageView);
      }
      else {
          holder.imageView.setImageBitmap(ImageUtil.getImage(location.byteArray));
      }


        holder.tvPlace.setText(location.place);
        holder.tvDate.setText(location.date);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            viewClickListener.onViewClick(location);
            }
        });

        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(!locationList.get(position).isLike)
               {
                   locationList.get(position).isLike = true;
                   holder.ivLike.setImageDrawable(
                           context.getResources().getDrawable(R.drawable.red_heart));

               }else
               {
                   locationList.get(position).isLike = false;
                   holder.ivLike.setImageDrawable(
                           context.getResources().getDrawable(R.drawable.blank_heart));

               }


            }
        });


    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    class  ImageViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView,ivLike;
        public TextView tvPlace,tvDate;

        public ImageViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.ivImage);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPlace = itemView.findViewById(R.id.tvPlaceName);
            ivLike = itemView.findViewById(R.id.ivLike);
        }
    }



}

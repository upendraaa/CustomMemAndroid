package com.example.upendra.testapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.upendra.testapplication.adapter.LocationAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewClickListener {

    private LocationAdapter locationAdapter;
    private RecyclerView recyclerView;

    private TextView tvHeader;
    private DatabaseHandler databaseHandler;

    private Customer mCustomer;

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        tvHeader = findViewById(R.id.tvHeader);
       databaseHandler = new DatabaseHandler(MainActivity.this);


        final CustomerViewModel model = ViewModelProviders.of(this).get(CustomerViewModel.class);

        model.getCustomers().observe(this, new Observer<Customer>() {
            @Override
            public void onChanged(@Nullable Customer customer) {
                mCustomer = customer;
                locationAdapter = new LocationAdapter(MainActivity.this,
                         customer);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                recyclerView.setAdapter(locationAdapter);
                tvHeader.setText("Hi "+customer.cust_name+",");
            }
        });


    }

    @Override
    public void onViewClick(Location location) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("location",location.place);
        startActivity(intent);
    }



}

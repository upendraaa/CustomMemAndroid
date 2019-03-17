package com.example.upendra.testapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.upendra.testapplication.CustomerApi.API_URL;

public class CustomerViewModel extends ViewModel {




    MutableLiveData<Customer> customer;
    DatabaseHandler databaseHandler;

    public LiveData<Customer> getCustomers()
    {
        databaseHandler = new DatabaseHandler(
                TestApplication.getContext());
        if(customer ==null)
        {
            customer = new MutableLiveData<>();
            getCustomerList();
        }

        return customer;
    }


    public void getCustomerList()
    {
        if(!ImageUtil.isNetworkAvailable(TestApplication.getContext()))
        {
           getOfflineCustomerList();
           return;
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        CustomerApi customerApi = retrofit.create(CustomerApi.class);
        Call<Customer> call = customerApi.getCustomers();

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                customer.setValue(response.body());
                AddToDatabase addToDatabase = new AddToDatabase();
                addToDatabase.execute(customer.getValue());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            System.out.println(t.getMessage());

            }
        });
    }

    public void getOfflineCustomerList()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler(TestApplication.getContext());

        ArrayList<Location> locations = databaseHandler.getLocations();
        Customer customer1 = new Customer();
        //Currently this field is not there in database
        customer1.cust_name="Rohit";
        customer1.locations = locations;
        customer.setValue(customer1);

    }




}

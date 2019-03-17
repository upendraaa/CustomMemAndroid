package com.example.upendra.testapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CustomerApi {


    String API_URL = "http://www.mocky.io/v2/5c261ccb3000004f0067f6ec/";
  //  @Headers("Accept: application/json")
    @GET(API_URL)
    Call<Customer> getCustomers();
}

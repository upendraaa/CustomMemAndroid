package com.example.upendra.testapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class AddToDatabase extends AsyncTask<Customer, Void, Void> {

    DatabaseHandler databaseHandler;


    private final String TAG = AddToDatabase.class.getSimpleName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        databaseHandler = new DatabaseHandler(TestApplication.getContext());
    }

    @Override
    protected Void doInBackground(Customer... params) {

        Customer customer = params[0];
      addTodatabase(customer);
        return null;
    }



    public void addTodatabase(final Customer customer)
    {
        if(customer==null)
        {
            return;
        }

                for(Location location: customer.locations)
                {
                    Bitmap bitmap = getBitmap(location.url);
                    byte[] bytes = ImageUtil.getBytes(bitmap);

                    location.byteArray = bytes;
                    databaseHandler.addEntry(location);

                }
    }



    private Bitmap getBitmap(final String input)
    {
        Bitmap image = null;
        try {
            URL url = new URL(input);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }

        if(image == null)
        {
            image = ((BitmapDrawable)TestApplication.getContext().getResources().getDrawable(R.drawable.sample)).getBitmap();
        }

        return image;

    }




}
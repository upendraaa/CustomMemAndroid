package interview.upendra.com.custommemory;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Location implements Serializable {


   public String  place;
   public String url;
   public String date;
   public double rate;
   public String description;

   //To store like
   @Expose
   public boolean isLike;
   //byte array for Image
   @Expose
   public byte[] byteArray;
}

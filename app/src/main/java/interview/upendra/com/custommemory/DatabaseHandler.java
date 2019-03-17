package interview.upendra.com.custommemory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="testDb";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(LocationTable.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEntry( Location location) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(LocationTable.DATE,   location.date);
        cv.put(LocationTable.DESCRIPTION,location.description);
        cv.put(LocationTable.PLACE,location.place);
        cv.put(LocationTable.IMAGE,location.byteArray);
        cv.put(LocationTable.PRICE,location.rate);
        cv.put(LocationTable.IMAGE_URL,location.url);
        database.insert( LocationTable.TABLE_NAME, null, cv );
    }


    public Location getLocationByPlace(String place)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "Select * from "+LocationTable.TABLE_NAME
                + " Where "+LocationTable.PLACE + " ='"+place+"'";

        Location location = null;

        Cursor cursor = database.rawQuery(query,null);

        if(cursor!=null&& cursor.moveToFirst())
        {

           do {

                location =getLocationFromCursor(cursor);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return location;

    }


    public ArrayList<Location> getLocations() throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "Select * from "+LocationTable.TABLE_NAME;

        ArrayList<Location> locations = new ArrayList<>();

        Cursor cursor = database.rawQuery(query,null);

        if(cursor!=null&& cursor.moveToFirst())
        {
            while (cursor.moveToNext())
                {
                    Location location = new Location();
                    try {
                        location.byteArray = cursor.getBlob(cursor.getColumnIndex(
                                LocationTable.IMAGE));
                        location.date = cursor.getString(cursor.getColumnIndex(LocationTable.DATE));

                        location.description = cursor.getString(cursor.getColumnIndex(
                                LocationTable.DESCRIPTION));
                        location.place = cursor.getString(cursor.getColumnIndex(LocationTable.PLACE));

                        location.rate = cursor.getDouble(cursor.getColumnIndex(
                                LocationTable.PRICE));
                        location.url = cursor.getString(cursor.getColumnIndex(LocationTable.IMAGE_URL));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    locations.add(location);
                }


        }
        cursor.close();

        return locations;
        }


        private Location getLocationFromCursor(Cursor cursor)
        {
            Location location = new Location();
            try {
                location.byteArray = cursor.getBlob(cursor.getColumnIndex(
                        LocationTable.IMAGE));
                location.date = cursor.getString(cursor.getColumnIndex(LocationTable.DATE));

                location.description = cursor.getString(cursor.getColumnIndex(
                        LocationTable.DESCRIPTION));
                location.place = cursor.getString(cursor.getColumnIndex(LocationTable.PLACE));

                location.rate = cursor.getDouble(cursor.getColumnIndex(
                        LocationTable.PRICE));
                location.url = cursor.getString(cursor.getColumnIndex(LocationTable.IMAGE_URL));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return location;

        }

}

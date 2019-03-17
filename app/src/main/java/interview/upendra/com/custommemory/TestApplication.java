package interview.upendra.com.custommemory;

import android.app.Application;
import android.content.Context;

public class TestApplication extends Application {


    private static Context mContext;


    public static Context getContext()
    {
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}

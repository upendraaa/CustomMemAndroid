package interview.upendra.com.custommemory;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailImage;
    private TextView tvPlaceName;
    private TextView tvDate;
    private TextView tvDetails;
    private CardView cardView;
    private TextView tvPricePerPerson;
    private TextView tvPrice;
    private View tvDivider;
    private TextView tvBookNow;
    private ImageView ivBack;

    private DatabaseHandler databaseHandler;
    private Handler handler = new Handler();
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeView();
        databaseHandler = new DatabaseHandler(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
          getLocation();
    }

    private Location mLocation;


    private void getLocation()
    {

        final String place = getIntent().getStringExtra("location");

        new Thread(new Runnable() {
            @Override
            public void run() {
                mLocation = databaseHandler.getLocationByPlace(place);
                handler.post(runnable);
            }
        }).start();

        runnable=new Runnable() {
            @Override
            public void run() {
             setView(mLocation);
            }
        };

    }


    private void setView(Location location)
    {



        if(!ImageUtil.isNetworkAvailable(DetailActivity.this))
        {
            if(location.byteArray!=null)
            {
                ivDetailImage.setImageBitmap(ImageUtil.getImage(location.byteArray));
            }
        }
        else {
            Glide.with(DetailActivity.this)
                    .load(location.url)
                    .into(ivDetailImage);
        }

        tvDate.setText(location.date);
        tvDetails.setText(location.description);
        tvPlaceName.setText(location.place);
        tvPrice.setText(getResources().getString(R.string.rupee_symbol)
                +" "+String.valueOf(location.rate));

    }


    private void initializeView()
    {

        ivDetailImage = (ImageView)findViewById( R.id.ivDetailImage );
        tvPlaceName = (TextView)findViewById( R.id.tvPlaceName );
        tvDate = (TextView)findViewById( R.id.tvDate );
        tvDetails = (TextView)findViewById( R.id.tvDetails );
        cardView = (CardView)findViewById( R.id.cardView );
        tvPricePerPerson = (TextView)findViewById( R.id.tvPricePerPerson );
        tvPrice = (TextView)findViewById( R.id.tvPrice );
        tvDivider = (View)findViewById( R.id.tvDivider );
        tvBookNow = (TextView)findViewById( R.id.tvBookNow );
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }





}

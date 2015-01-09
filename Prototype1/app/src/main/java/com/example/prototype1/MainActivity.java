package com.example.prototype1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity
{

    GoogleMap googleMap;
    private SupportMapFragment mMapFragment;
    Button btnShowLocation;
    Localizar gps;

    private LatLng orig;
    private LatLng dest;


    double latitude;
    double longitude;

    double latitude2;//=40.2279997;
    double longitude2;//=-3.7740324;

    String latitude1="prueba latitude";
    String longitude1="prueba longitude";

    public final static String Extra_latitude="com.example.prototype1.latitude";

    public final static String Extra_longitude="com.example.prototype1.longitude";

    public final static String Extra_latitude_ori="com.example.prototype1.latitude_origen";

    public final static String Extra_longitude_ori="com.example.prototype1.longitude_origen";





    /**
     * Initialises the mapview
     */
    private void createMapView()
    {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try
        {
            if(null == googleMap)
            {
                //googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();


                mMapFragment=(SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.mapView));
                ViewGroup.LayoutParams params = mMapFragment.getView().getLayoutParams();
                params.height=1080;
                params.width=1080;
                //mMapFragment.getView().setLayoutParams(params);

                googleMap = mMapFragment.getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap)
                {
                    Toast.makeText(getApplicationContext(),"Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (NullPointerException exception)
        {
            Log.e("mapApp", exception.toString());
        }
    }

    /**
     * Adds a marker to the map
     */

    private void addMarker()
    {

        /* Make sure that the map has been initialised */

        if(null != googleMap)
        {
            dest =new LatLng(latitude,longitude);
            googleMap.addMarker(new MarkerOptions().position(dest).title("Marker").draggable(true));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dest,17));
        }
    }



     private void ShowLocation()
     {
         btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
         // show location button click event
         btnShowLocation.setOnClickListener(new View.OnClickListener()
         {

             @Override
             public void onClick(View arg0)
             {
                 // create class object
                 gps = new Localizar(MainActivity.this);

                 // check if GPS enabled
                 if(gps.canGetLocation())
                 {

                     latitude = gps.getLatitude();
                     longitude = gps.getLongitude();

                     // \n is for new line
                     Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                     TextView tvlatitude = (TextView) findViewById(R.id.valueLa);
                     tvlatitude.setText(Double.toString(latitude));


                     TextView tvlongitude=(TextView) findViewById(R.id.valueLon);
                     tvlongitude.setText(Double.toString(longitude));

                     addMarker();
                 }
                 else
                 {
                     // can't get location
                     // GPS or Network is not enabled
                     // Ask user to enable GPS/network in settings
                     gps.showSettingsAlert();
                 }
             }
         });


     }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMapView();

        ShowLocation();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void SavePosition(View view)
    {

        Intent intent = new Intent(this,SavePositionActivity.class);

        intent.putExtra(Extra_latitude,Double.toString(latitude));
        intent.putExtra(Extra_longitude,Double.toString(longitude));
        startActivity(intent);

    }

    public void PruebaGet(View view)
    {
        Intent intent=new Intent(this,pruebaActivity.class);
        startActivity(intent);
    }


    public void GetMyCar(View view)
    {
        btnShowLocation = (Button) findViewById(R.id.GetMyCar);


        // create class object
        gps = new Localizar(MainActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation())
        {

            latitude2 = gps.getLatitude();
            longitude2 = gps.getLongitude();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    /*TextView tvlatitude = (TextView) findViewById(R.id.valueLa);
                    tvlatitude.setText(Double.toString(latitude));


                    TextView tvlongitude=(TextView) findViewById(R.id.valueLon);
                    tvlongitude.setText(Double.toString(longitude));

                    addMarker();*/
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


        Intent intent = new Intent(this,GetMyCarActivity.class);
        //intent.putExtra(Extra_latitude,Double.toString(latitude));
        //intent.putExtra(Extra_longitude,Double.toString(longitude));

        intent.putExtra(Extra_latitude_ori,Double.toString(latitude2));
        intent.putExtra(Extra_longitude_ori,Double.toString(longitude2));
        startActivity(intent);



    }






}

package com.example.prototype1;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class SavePositionActivity extends FragmentActivity
{
    private final static String TAG = SavePositionActivity.class.getName();

    private static String latitude=null;

    private static String longitude=null;



    GoogleMap googleMap;
    private  SupportMapFragment mMapFragment;



    private class PostPositionTask extends AsyncTask<String,Void,Posicion>
    {
        private ProgressDialog pd;

        @Override
        protected Posicion doInBackground(String... params)
        {
            Posicion posicion = null;
            try
            {
                posicion=CarAPI.getInstance(SavePositionActivity.this).CreatePosicion(params[0],params[1],params[2],params[3]);
            }
            catch (AppException e)
            {
                Log.e(TAG, e.getMessage(), e);
            }
            return posicion;
        }



        @Override
        protected void onPostExecute(Posicion result)
        {

            Toast.makeText(getApplicationContext(),"Position Saved",Toast.LENGTH_LONG).show();
            showPosicion(result);
            if (pd != null)
            {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute()
        {
            pd = new ProgressDialog(SavePositionActivity.this);
            pd.setTitle("Saving Position...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_position);

        Intent intent = getIntent();
        latitude = intent.getStringExtra(MainActivity.Extra_latitude);
        longitude=intent.getStringExtra(MainActivity.Extra_longitude);

        TextView tvlatitude= (TextView) findViewById(R.id.valueLa);
        tvlatitude.setText(latitude);



        TextView tvlongitud= (TextView) findViewById(R.id.valueLon);
        tvlongitud.setText(longitude);


        createMapView();
        addMarker();

    }

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


    private void addMarker()
    {

        /* Make sure that the map has been initialised */

        if(null != googleMap)
        {

            LatLng dest =new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
            googleMap.addMarker(new MarkerOptions().position(dest).title("Marker").draggable(true));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dest,17));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_position, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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






    public void cancel(View v)
    {
        setResult(RESULT_CANCELED);
        finish();
    }






    public void PostPosition(View view)
    {
        EditText etdescription = (EditText) findViewById(R.id.edit_description);
        String description=etdescription.getText().toString();
        String username="yifei";

        (new PostPositionTask()).execute(username,latitude,longitude,description);


    }

    private void showPosicion(Posicion result)
    {
        /*String json = new Gson().toJson(result);
        Bundle data= new Bundle();
        data.putString("json-posicion",json);
        Intent intent = new Intent();
        intent.putExtra(data);
        setResult(RESULT_OK,intent);*/
        finish();
    }






}

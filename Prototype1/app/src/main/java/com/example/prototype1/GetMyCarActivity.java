package com.example.prototype1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GetMyCarActivity extends FragmentActivity
{
    private final static String TAG = GetMyCarActivity.class.getName();

    private static String latitude=null;

    private static String longitude=null;

    private static String latitude_ori=null;

    private static String longitude_ori=null;

    private static LatLng origin;

    private  static  LatLng dest;

    private  static  String url="http://147.83.7.155:8080/Car-api/posicion/last?username=yifei";

    GoogleMap googleMap;
    private SupportMapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_car);


        Intent intent = getIntent();
        //latitude=intent.getStringExtra(MainActivity.Extra_latitude);
        //TextView tvlatitude= (TextView) findViewById(R.id.valueLa);
        //tvlatitude.setText(latitude);

        //longitude=intent.getStringExtra(MainActivity.Extra_longitude);
        //TextView tvlongitude= (TextView) findViewById(R.id.valueLon);
        //tvlongitude.setText(longitude);

        latitude_ori=intent.getStringExtra(MainActivity.Extra_latitude_ori);
        TextView tvlatitude_ori=(TextView) findViewById(R.id.Value_La_Ori);
        tvlatitude_ori.setText(latitude_ori);

        longitude_ori=intent.getStringExtra(MainActivity.Extra_longitude_ori);
        TextView tvlongitude_ori= (TextView) findViewById(R.id.Value_Lon_Ori);
        tvlongitude_ori.setText(longitude_ori);

        createMapView();

        (new FetchPosicionTask()).execute();


    }


    private  void loadingPosicion(Posicion posicion)
    {
        TextView tvlatitude= (TextView) findViewById(R.id.valueLa);
        TextView tvlongitude= (TextView) findViewById(R.id.valueLon);

        tvlatitude.setText(Double.toString(posicion.getCoordenadaX()));
        tvlongitude.setText(Double.toString(posicion.getCoordenadaY()));
    }



    private class FetchPosicionTask extends AsyncTask<String,Void,Posicion>
    {
        private ProgressDialog pd;

        @Override
        protected Posicion doInBackground(String... params)
        {
            Posicion posicion=null;
            try
            {
                posicion=CarAPI.getInstance(GetMyCarActivity.this).getPosicion();
            }
            catch (AppException e)
            {
                Log.d(TAG,e.getMessage(),e);
            }
            return posicion;
        }


        @Override
        protected void onPostExecute(Posicion result)
        {
            loadingPosicion(result);
            if(pd!=null)
            {
                pd.dismiss();
            }

        }


        @Override
        protected void onPreExecute()
        {
            pd=new ProgressDialog(GetMyCarActivity.this);
            pd.setTitle("Getting Posicion...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }














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
                params.height=1330;
                params.width=1330;
                //mMapFragment.getView().setLayoutParams(params);

                googleMap = mMapFragment.getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap)
                {
                    Toast.makeText(getApplicationContext(), "Error creating map", Toast.LENGTH_SHORT).show();
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
            origin =new LatLng(Double.parseDouble(latitude_ori),Double.parseDouble(longitude_ori));
            dest = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
            //LatLng prueba =new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));

            googleMap.addMarker(new MarkerOptions().position(origin).title("Marker").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin, 12));

            googleMap.addMarker(new MarkerOptions().position(dest).title("Marker").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dest, 12));


            //Polyline line= googleMap.addPolyline(new PolylineOptions().add(origin,dest).width(8).color(Color.BLUE));


            String url=getDirectionsUrl(origin, dest);
            DownloadTask downloadTask = new DownloadTask();

            // Start downloading json data from Google Directions API
            downloadTask.execute(url);
        }
    }


    private String getDirectionsUrl(LatLng origin,LatLng dest)
    {

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Travelling Mode
        String mode = "mode=walking";


        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException
    {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e)
        {
            Log.d("Exception while downloading url", e.toString());
        }
        finally
        {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }





    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>
    {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url)
        {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }


        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >
    {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData)
        {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }


        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result)
        {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++)
            {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++)
                {
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(9);

                // Changing the color polyline according to the mode
                lineOptions.color(Color.BLUE);
            }

            if(result.size()<1)
            {
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Drawing polyline in the Google Map for the i-th route
            googleMap.addPolyline(lineOptions);
        }
    }

    public void cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }


    public void ShowRoute(View view)
    {
        addMarker();
    }





        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_my_car, menu);
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
}

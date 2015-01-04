package com.example.prototype1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class pruebaActivity extends Activity
{
    private final static String TAG = pruebaActivity.class.getName();
    //private  static  String url="http://192.168.1.40:8000/Car-api/posicion/last?username=yifei";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        (new FetchPosicionTask()).execute();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prueba, menu);
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
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    private  void loadingPosicion(Posicion posicion)
    {
        double latitude=posicion.getCoordenadaX();
        double longitude=posicion.getCoordenadaY();

        String descripcion=posicion.getDescripcion();
        Long fecha=posicion.getFecha();
        int id=posicion.getIdposicion();




        TextView tvlatitude= (TextView) findViewById(R.id.valueLa);
        TextView tvlongitude= (TextView) findViewById(R.id.valueLon);

        tvlatitude.setText(Double.toString(latitude));
        tvlongitude.setText(Double.toString(longitude));
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
                posicion=CarAPI.getInstance(pruebaActivity.this).getPosicion();
            }
            catch (AppException e)
            {
                Log.d(TAG, e.getMessage(), e);
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
            pd=new ProgressDialog(pruebaActivity.this);
            pd.setTitle("Getting Posicion...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }

}

package com.example.prototype1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.TextView;
import com.example.prototype1.R;
/**
 * Created by yifeige on 28/12/14.
 */
public class CarAPI
{
    private final static String TAG = CarAPI.class.getName();
    private static CarAPI instance =null;
    private URL url;

    private CarRootAPI rootAPI=null;

    private  CarAPI(Context context) throws IOException, AppException
    {
        super();

        AssetManager assetManager = context.getAssets();
        Properties config = new Properties();
        config.load(assetManager.open("config.properties"));
        String urlHome = config.getProperty("car.home");
        url = new URL(urlHome);

        Log.d("LINKS", url.toString());
        getRootAPI();


    }

    public final static CarAPI getInstance(Context context) throws AppException
    {
        if (instance == null)
            try
            {
                instance = new CarAPI(context);
            } catch (IOException e)
            {
                throw new AppException("Can't load configuration file");
            }
        return instance;
    }





    public Posicion getPosicion() throws AppException
    {
        String urlposicion="http://147.83.7.155:8080/Car-api/posicion/last?username=yifei";
        Posicion posicion=null;
        HttpURLConnection urlConnection=null;
        try
        {
            URL url = new URL(urlposicion);

            Log.d("El link para GET","El link es :"+" "+url);

            urlConnection = (HttpURLConnection) url.openConnection();

            Log.d("El link para GET","El link seria :"+" "+urlConnection);

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            Log.d("Connectado","Estoy connectado al API");
        }
        catch (IOException e)
        {
            throw new AppException("Can't connect to Car API Web Service");
        }




        try
        {
            Log.d("Try","Estoy dentro del TRY");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Log.d("Leo datos","Ya he leido todo los datos");

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                Log.d("While","Estoy dentro del While");
                sb.append(line);
            }
            Log.d("Json","Estoy Cogiendo los datos");
            JSONObject jsonPosicion=new JSONObject(sb.toString());
            posicion.setDescripcion(jsonPosicion.getString("descripcion"));
            posicion.setFecha(jsonPosicion.getLong("fecha"));
            posicion.setIdposicion(jsonPosicion.getInt("idposicion"));
            posicion.setCoordenadaY(jsonPosicion.getDouble("coordenadaY"));
            posicion.setCoordenadaX(jsonPosicion.getDouble("coordenadaX"));
            JSONArray jsonLinks = jsonPosicion.getJSONArray("links");
            parseLinks(jsonLinks,posicion.getLinks());



        }
        catch (IOException e)
        {
            throw new AppException("Can't connect to Beeter API Web Service");
        }



        /*catch (MalformedURLException e)
        {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Bad sting url");
        }*/

        catch (JSONException e)
        {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception parsing response");
        }



        Log.d("Devolver","Voy a devolver los datos al Actividad que toca");
        return posicion;



    }





    private void getRootAPI() throws AppException
    {
        Log.d(TAG, "getRootAPI()");
        rootAPI = new CarRootAPI();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("visualizar el primer URL","El url es :"+" "+urlConnection);
            //http:localhost:8000/beeter-api  RootBeeterAPI
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        }
        catch (IOException e)
        {
            throw new AppException("Can't connect to Car API Web Service");
        }

        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, rootAPI.getLinks());
        } catch (IOException e)
        {
            throw new AppException( "Can't get response from Car API Web Service");
        } catch (JSONException e)
        {
            throw new AppException("Error parsing Car Root API");
        }

    }










    public Posicion CreatePosicion(String username,String coordenadaX,String coordenadaY,String descripcion) throws AppException
    {
        Posicion posicion =new Posicion();

        posicion.setUsername(username);
        posicion.setCoordenadaX(Double.parseDouble(coordenadaX));
        posicion.setCoordenadaY(Double.parseDouble(coordenadaY));
        posicion.setDescripcion(descripcion);

        HttpURLConnection urlConnection = null;

        try
        {
            JSONObject jsonPosicion = createJsonPosition(posicion);
            URL urlPostPosicion = new URL(rootAPI.getLinks().get("Post-posicion").getTarget());

            Log.d("El link para POST","El link es :"+" "+urlPostPosicion);
            urlConnection = (HttpURLConnection) urlPostPosicion.openConnection();
            Log.d("El link para POST","El link es :"+" "+urlConnection);


            String mediaType = rootAPI.getLinks().get("Post-posicion").getParameters().get("type");

            urlConnection.setRequestProperty("Accept",mediaType);
            urlConnection.setRequestProperty("Content-Type",mediaType);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            PrintWriter writer = new PrintWriter(urlConnection.getOutputStream());
            writer.println(jsonPosicion.toString());
            writer.close();



            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }

            jsonPosicion = new JSONObject(sb.toString());

            posicion.setUsername(jsonPosicion.getString("username"));
            posicion.setCoordenadaX(jsonPosicion.getDouble("coordenadaX"));
            posicion.setCoordenadaY(jsonPosicion.getDouble("coordenadaY"));
            posicion.setDescripcion(jsonPosicion.getString("descripcion"));

            JSONArray jsonLinks = jsonPosicion.getJSONArray("links");
            parseLinks(jsonLinks,posicion.getLinks());


        }
        catch (JSONException e)
        {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error parsing response");
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Error getting response");
        }
        finally
        {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return posicion;




    }





    private void parseLinks(JSONArray jsonLinks, Map<String, Link> map) throws AppException, JSONException
    {
        for (int i = 0; i < jsonLinks.length(); i++)
        {
            Link link = null;
            try
            {
                link = SimpleLinkHeaderParser.parseLink(jsonLinks.getString(i));
                Log.d("Link:","El link es:"+link);


            } catch (Exception e)
            {
                throw new AppException(e.getMessage());
            }
            String rel = link.getParameters().get("rel");
            String rels[] = rel.split("\\s");
            for (String s : rels)
                map.put(s, link);
        }
    }





    private JSONObject createJsonPosition(Posicion posicion)  throws JSONException
    {

        JSONObject jsonPosicion = new JSONObject();
        jsonPosicion.put("username",posicion.getUsername());
        jsonPosicion.put("coordenadaX",posicion.getCoordenadaX());
        jsonPosicion.put("coordenadaY",posicion.getCoordenadaY());
        jsonPosicion.put("descripcion",posicion.getDescripcion());

        return jsonPosicion;





    }


}

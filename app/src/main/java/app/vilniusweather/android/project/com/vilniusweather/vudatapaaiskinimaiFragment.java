package app.vilniusweather.android.project.com.vilniusweather;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Paulius on 2014-11-18.
 */
public class vudatapaaiskinimaiFragment extends Fragment {



    String v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v21, v24, v25, v26, v27, v29, v31, v33, v34 = "---";
    TextView t, a, b, c, d, e, f, g, h, j, k, l, m, n, o , p ,r, q, qq, s, u, vv, w;
    ImageView pav;

    final String LOG_TAG = vudatapaaiskinimaiFragment.class.getSimpleName();

    public vudatapaaiskinimaiFragment() {
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.vudatapaaiskinimai, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_fresh) {
            updateWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            String newline = System.getProperty("line.separator");
            //rasymas
            String[] array = new String[23];
            int index = 0;

            String filename = "VilniusWeatherVUEx.txt";
            File file = new File(getActivity().getFilesDir(), filename);

            // write data if file dont exists
            if (!file.exists())
            {
                String string = "11111111" + newline + "22222222" + newline + "33333333"
                        + newline + "44444444" + newline + "55555555" + newline + "66666666"
                        + newline + "77777777"+ newline + "11111111" + newline + "22222222" + newline + "33333333"
                        + newline + "44444444" + newline + "55555555" + newline + "66666666"
                        + newline + "77777777"+ newline + "11111111" + newline + "22222222" + newline + "33333333"
                        + newline + "44444444" + newline + "55555555" + newline + "66666666"
                        + newline + "77777777"+ newline + "11111111" + newline + "22222222" + newline;

                FileOutputStream outputStream;

                try
                {
                    outputStream = getActivity().openFileOutput(filename,
                            Context.MODE_WORLD_READABLE);
                    outputStream.write(string.getBytes());
                    outputStream.close();

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


            //skaitymas
            FileInputStream fileInputStream= null;
            try {
                fileInputStream = getActivity().openFileInput("VilniusWeatherVUEx.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder= new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    //stringBuilder.append(line);
                    //Log.v(LOG_TAG, "CHECK IF IT WORKED " + line);
                    array[index] = line;
                    index++;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            for (int i = 0; i <23; i++){
                Log.v(LOG_TAG, "CHECK IF IT WORKED " + array[i]);
            }

        View v = inflater.inflate(R.layout.fragment_vudatapaaiskinimai, container, false);


        t = (TextView)v.findViewById(R.id.reiksme1);
        a = (TextView)v.findViewById(R.id.reiksme2);
        b = (TextView)v.findViewById(R.id.reiksme3);
        c = (TextView)v.findViewById(R.id.reiksme4);
        d = (TextView)v.findViewById(R.id.reiksme5);
        e = (TextView)v.findViewById(R.id.reiksme6);
        f = (TextView)v.findViewById(R.id.reiksme7);
        g = (TextView)v.findViewById(R.id.reiksme8);
        h = (TextView)v.findViewById(R.id.reiksme9);
        j = (TextView)v.findViewById(R.id.reiksme10);
        k = (TextView)v.findViewById(R.id.reiksme11);
        l = (TextView)v.findViewById(R.id.reiksme12);
        m = (TextView)v.findViewById(R.id.reiksme13);
        n = (TextView)v.findViewById(R.id.reiksme14);
        o = (TextView)v.findViewById(R.id.reiksme15);
        p = (TextView)v.findViewById(R.id.reiksme16);
        r = (TextView)v.findViewById(R.id.reiksme17);
        q = (TextView)v.findViewById(R.id.reiksme18);
        qq = (TextView)v.findViewById(R.id.reiksme19);
        s = (TextView)v.findViewById(R.id.reiksme20);
        u = (TextView)v.findViewById(R.id.reiksme21);
        vv = (TextView)v.findViewById(R.id.reiksme22);
        w = (TextView)v.findViewById(R.id.reiksme23);

        pav = (ImageView)v.findViewById(R.id.imageView);

        //-----
        t.setText("OWM_timestamp " + array[0]);

        a.setText("OWM_zeno_BP1_5s_Mb " + array[1]);
        b.setText("Vėjo greitis: " + array[2]);
        c.setText("Kritulių kiekis per 30s: " + array[3]);
        d.setText("Krituliu kiekio suma per einamą parą: " + array[4]);
        e.setText("Momentinė temperatūra: " + array[5]);
        f.setText("OWM_zeno_BIT" + array[6]);
        g.setText("Vėjo gūsio stiprumas: " + array[7]);
        h.setText("Santykinis oro drėgnis: " + array[8]);
        j.setText("OWM_zeno_V_DC " + array[9]);
        k.setText("Atmosferos slėgis stoties lygyje: " + array[10]);
        l.setText("OWM_zeno_Dir_5s " + array[11]);
        m.setText("Vėjo kryptis: " + array[12]);
        n.setText("OWM_zeno_ID " + array[13]);
        o.setText("OWM_cl31_high_sig " + array[14]);
        p.setText("Vidurinio aukšto debesų pado aukštis: " + array[15]);
        r.setText("Žemutinio aukšto debesų pado aukštis: " + array[16]);
        q.setText("OWM_cl31_Range_Ft " + array[17]);
        qq.setText("OWM_cl31_Detect " + array[18]);
        s.setText("OWM_cl31_ClH3_30s_Ft " + array[19]);
        u.setText("OWM_sws200_PP_Mm " + array[20]);
        vv.setText("Esamų oro sąlygų kodas: " +array[21]);
        w.setText("Horizontalus matomumas per 1 minutę: " + array[22]);
        //------

        return v;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask();
        weatherTask.execute("94043");
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {
        public String[] resultStrs = new String[23];
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";

            final String OWM_timestamp = "timestamp";
            final String OWM_zeno_BP1_5s_Mb = "zeno_BP1_5s_Mb";
            final String OWM_zeno_Spd_5s_Kt = "zeno_Spd_5s_Kt";
            final String OWM_zeno_PP_30s_Mm = "zeno_PP_30s_Mm";
            final String OWM_zeno_PP_24h_Mm = "zeno_PP_24h_Mm";
            final String OWM_zeno_AT_5s_C = "zeno_AT_5s_C";
            final String OWM_zeno_BIT = "zeno_BIT";
            final String OWM_zeno_gust = "zeno_gust";
            final String OWM_zeno_RH_5s = "zeno_RH_5s";
            final String OWM_zeno_V_DC = "zeno_V_DC";
            final String OWM_zeno_BP2_5s_Mb = "zeno_BP2_5s_Mb";
            final String OWM_zeno_Dir_5s = "zeno_Dir_5s";
            final String OWM_zeno_WSonicBIT = "zeno_WSonicBIT";
            final String OWM_zeno_ID = "zeno_ID";
            final String OWM_cl31_high_sig = "cl31_high_sig";
            final String OWM_cl31_ClH2_30s_Ft = "cl31_ClH2_30s_Ft";
            final String OWM_cl31_ClH1_30s_Ft = "cl31_ClH1_30s_Ft";
            final String OWM_cl31_Range_Ft = "cl31_Range_Ft";
            final String OWM_cl31_Detect = "cl31_Detect";
            final String OWM_cl31_ClH3_30s_Ft = "cl31_ClH3_30s_Ft";
            final String OWM_sws200_PP_Mm = "sws200_PP_Mm";
            final String OWM_sws200_synop_code = "sws200_synop_code";
            final String OWM_sws200_Vis_1m_KM = "sws200_Vis_1m_KM";


            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);


            // Get the JSON object representing the day
            JSONObject dayForecast = weatherArray.getJSONObject(0);

            // description is in a child array called "weather", which is 1 element long.

            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v1 = weatherObject.getString(OWM_timestamp);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v2 = weatherObject.getString(OWM_zeno_BP1_5s_Mb);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v3 = weatherObject.getString (OWM_zeno_Spd_5s_Kt);
            Log.e(LOG_TAG, "reiksme inicializacija:" + v3);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v4 = weatherObject.getString(OWM_zeno_PP_30s_Mm);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v5 = weatherObject.getString (OWM_zeno_PP_24h_Mm);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v6 = weatherObject.getString(OWM_zeno_AT_5s_C);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v7 = weatherObject.getString (OWM_zeno_BIT);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v8 = weatherObject.getString(OWM_zeno_gust);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v9 = weatherObject.getString (OWM_zeno_RH_5s);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v10 = weatherObject.getString(OWM_zeno_V_DC);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v11 = weatherObject.getString (OWM_zeno_BP2_5s_Mb);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v12 = weatherObject.getString(OWM_zeno_Dir_5s);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v13 = weatherObject.getString (OWM_zeno_WSonicBIT);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v14 = weatherObject.getString(OWM_zeno_ID);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v21 = weatherObject.getString(OWM_cl31_high_sig);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v24 = weatherObject.getString (OWM_cl31_ClH2_30s_Ft);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v25 = weatherObject.getString(OWM_cl31_ClH1_30s_Ft);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v26 = weatherObject.getString (OWM_cl31_Range_Ft);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v27 = weatherObject.getString(OWM_cl31_Detect);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v29 = weatherObject.getString(OWM_cl31_ClH3_30s_Ft);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v31 = weatherObject.getString (OWM_sws200_PP_Mm);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v33 = weatherObject.getString (OWM_sws200_synop_code);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v34 = weatherObject.getString(OWM_sws200_Vis_1m_KM);

            for (String s : resultStrs) {
                Log.v(LOG_TAG, "Forecast entry: " + s);
            }
            Log.e(LOG_TAG, "KODAS:" + v33);

            //rasymas
            String[] array = new String[23];
            int index = 0;

            String newline = System.getProperty("line.separator");
            FileOutputStream fOut = null;
            try {
                fOut = getActivity().openFileOutput("VilniusWeatherVUEx.txt",Context.MODE_WORLD_READABLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String string = v1 + newline + v2 + newline +v3 + newline +v4 + newline + v5 +
                    newline +v6 + newline + v7 + newline + v8 + newline +v9 + newline +v10 +
                    newline + v11 + newline +v12 + newline + v13
                    + newline +v14 + newline +v21 + newline + v24 +
                    newline +v25 + newline + v26 + newline +v27 + newline + v29 +
                    newline + v31 + newline +v33 + newline +v34 + newline;

            try {
                fOut.write(string.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream fileInputStream= null;
            try {
                fileInputStream = getActivity().openFileInput("VilniusWeatherVUEx.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            //----------------------------



            return resultStrs;

        }


        @Override
        protected String[] doInBackground(String... params) {

            // If there's no zip code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            int numDays = 23;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                final String FORECAST_BASE_URL =
                        "http://msapp.vub.lt/weather.json";

                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI " + builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();

                Log.v(LOG_TAG, "Forecast string: " + forecastJsonStr);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }


        @Override
        protected void onPostExecute(String[] result) {
            //View v = inflater.inflate(R.layout.fragment_vudata, false);

            //TextView t = (TextView)v.findViewById(R.id.reiksme1);

            if (result != null) {

                t.setText("OWM_timestamp " + v1);

                a.setText("OWM_zeno_BP1_5s_Mb " + v2);
                b.setText("Vėjo greitis: " + v3);
                c.setText("Kritulių kiekis per 30s: " + v4);
                d.setText("Krituliu kiekio suma per einamą parą: " + v5);
                e.setText("Momentinė temperatūra: " + v6);
                f.setText("OWM_zeno_BIT" + v7);
                g.setText("Vėjo gūsio stiprumas: " + v8);
                h.setText("Santykinis oro drėgnis: " + v9);
                j.setText("OWM_zeno_V_DC " + v10);
                k.setText("Atmosferos slėgis stoties lygyje: " + v11);
                l.setText("OWM_zeno_Dir_5s " + v12);
                m.setText("Vėjo kryptis: " + v13);
                n.setText("OWM_zeno_ID " + v14);
                o.setText("OWM_cl31_high_sig " + v21);
                p.setText("Vidurinio aukšto debesų pado aukštis: " + v24);
                r.setText("Žemutinio aukšto debesų pado aukštis: " + v25);
                q.setText("OWM_cl31_Range_Ft " + v26);
                qq.setText("OWM_cl31_Detect " + v27);
                s.setText("OWM_cl31_ClH3_30s_Ft " + v29);
                u.setText("OWM_sws200_PP_Mm " + v31);
                vv.setText("Esamų oro sąlygų kodas: " +v33);
                w.setText("Horizontalus matomumas per 1 minutę: " + v34);

            }

        }





    }



}

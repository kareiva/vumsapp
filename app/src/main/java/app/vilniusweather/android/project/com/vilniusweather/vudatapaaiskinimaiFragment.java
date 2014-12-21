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
    TextView t, a, b, c, d, e, f, g, h, j, data;
    int deau, dre;

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
            String[] array = new String[15];
            int index = 0;

            String filename = "VilniusWeatherVUEx.txt";
            File file = new File(getActivity().getFilesDir(), filename);

            // write data if file dont exists
        if (!file.exists())
            {
                String string = "2014-03-29 16:00             " + newline + "22222222" + newline + "33333333"
                        + newline + "44444444" + newline + "55555555" + newline + "66666666"
                        + newline + "77777777"+ newline + "11111111" + newline + "22222222"
                        + newline + "33333333" + newline + "33333333333333333333" + newline;

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


            for (int i = 0; i <10; i++){
                Log.v(LOG_TAG, "CHECK IF IT WORKED " + array[i]);
            }

        View v = inflater.inflate(R.layout.fragment_vudatapaaiskinimai, container, false);

        data = (TextView)v.findViewById(R.id.reiksme0);
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

        //-----
        t.setText(getString(R.string.temperatura) + " " + array[0].substring(0, array[0].length() - 4) + "°C");
        a.setText(getString(R.string.santykinisdregnis) + ": " + array[1].substring(0, array[1].length() - 2));
        b.setText(getString(R.string.slegis) + " " + array[2]);
        c.setText(getString(R.string.krituliai) + " " + array[3]);
        d.setText(getString(R.string.vejogreitis) + " " + String.valueOf(Math.round(Double.parseDouble((array[4].substring(0, array[4].length() - 4))) * 0.514*100.0)/100.0) + " m/s");
        deau = Integer.parseInt(array[7].substring(0, array[7].length() - 4));
        dre = Integer.parseInt(array[1].substring(0, array[1].length() - 4));

        int K = Integer.parseInt(array[5].substring(0, array[5].length() - 4));
        String kr = "";
        if ((338 <=K) && (K<=360)) kr=getString(R.string.siaures);
        else if ((K <=22))  kr=getString(R.string.siaures);
        else if ((23 <= K) && (K<=67))  kr=getString(R.string.siauresrytu);
        else if ((68<=K) && (K<=112)) kr=getString(R.string.rytu);
        else if ((113<=K) && (K<=157)) kr=getString(R.string.pietryciu);
        else if ((158<= K )&& (K<=202)) kr=getString(R.string.pietu);
        else if ((203<=K) && (K<=247)) kr=getString(R.string.pietvakariu);
        else if ((248<=K) && (K<=292)) kr=getString(R.string.vakaru);
        else if ((293<=K) && (K<=337)) kr=getString(R.string.siauresvakaru);
        e.setText(getString(R.string.vejokryptisx) + ": " + kr + " (" + K + "°)");

        f.setText(getString(R.string.vejogusiai) + ": " + String.valueOf(Math.round(Double.parseDouble((array[6].substring(0, array[6].length() - 4))) * 0.514*100.0)/100.0) + " m/s");
        g.setText(getString(R.string.debesupadoaukstis) + ": " + Integer.parseInt(array[7].substring(0, array[7].length() - 3)) + "m");
        h.setText(getString(R.string.horizontalusmatomumas)+ ": " + Double.parseDouble(array[8].substring(0, array[8].length() - 3)) + " km");

        String kodas = "";
        if ((array[9].equals("00 WMO")) && (deau == 0)) {
            kodas=" ";
        }
        if ((array[9].equals("00 WMO")) && (deau > 0)) {
            kodas=(getString(R.string.giedra) );
        }
        if ((array[9].equals("04 WMO")) && (dre >= 80)) {
            kodas=(getString(R.string.rukana));
        }
        if ((array[9].equals("04 WMO")) && (dre < 80)) {
            kodas=(getString(R.string.migla));
        }
        if ((array[9].equals("05 WMO")) && (dre >= 80)) {
            kodas=(getString(R.string.Rūkas));
        }
        if ((array[9].equals("05 WMO")) && (dre < 80)){
            kodas=(getString(R.string.migla1km));
        }
        if (array[9].equals("30 WMO")) {
            kodas=(getString(R.string.Rūkas));
        }
        if (array[9].equals("40 WMO")) {
            kodas=(getString(R.string.krituliai));
        }
        if (array[9].equals("51 WMO")) {
            kodas=(getString(R.string.silpnadulksna));
        }
        if (array[9].equals("52 WMO")) {
            kodas=(getString(R.string.vidutinedulksna));
        }
        if (array[9].equals("53 WMO")) {
            kodas=(getString(R.string.stipridulksna));
        }
        if (array[9].equals("61 WMO")) {
            kodas=(getString(R.string.silpnaslietus));
        }
        if (array[9].equals("62 WMO")) {
            kodas=(getString(R.string.vidutinislietus));
        }
        if (array[9].equals("63 WMO")) {
            kodas=(getString(R.string.stipruslietus));
        }
        if (array[9].equals("71 WMO")) {
            kodas=(getString(R.string.silpnassnygis));
        }
        if (array[9].equals("72 WMO")) {
            kodas=getString(R.string.vidutinissnygis);
        }
        if (array[9].equals("73 WMO")) {
            kodas=(getString(R.string.stiprussnygis));
        }
        if (array[9].equals("80 WMO")) {
            kodas=(getString(R.string.liutiniaikrituliai));
        }
        j.setText(getString(R.string.orokodas) + ": " + array[9] + " " + kodas);
        //------

        data.setText(getString(R.string.meteoatnaujinta) + " " + array[10].substring(0, array[10].length() - 13) + " ");

        return v;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask();
        weatherTask.execute(" ");
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
            final String OWM_zeno_RH_5s = "zeno_RH_5s";                // Santykinis oro drėgnis
            final String OWM_zeno_V_DC = "zeno_V_DC";
            final String OWM_zeno_BP2_5s_Mb = "zeno_BP2_5s_Mb";
            final String OWM_zeno_Dir_5s = "zeno_Dir_5s";
            final String OWM_zeno_WSonicBIT = "zeno_WSonicBIT";
            final String OWM_zeno_ID = "zeno_ID";
            final String OWM_cl31_high_sig = "cl31_high_sig";
            final String OWM_cl31_ClH2_30s_Ft = "cl31_ClH2_30s_Ft";
            final String OWM_cl31_ClH1_30s_Ft = "cl31_ClH1_30s_Ft";     // Debesu aukstis, Apatinė debesų pado reikšmė
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
            String string = v6 + newline + v9 + newline +v11
                    + newline +v5 + newline + v3 +
                    newline +v12 + newline + v8 + newline + v25 + newline +v34
                    + newline +v33 + newline + v1 + newline;

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

            if (result != null) {

                t.setText(getString(R.string.temperatura) + " " + v6.substring(0, v6.length() - 4) + "°C");
                a.setText(getString(R.string.santykinisdregnis) + ": " + v9.substring(0, v9.length() - 2));
                b.setText(getString(R.string.slegis) + " " + v11);
                c.setText(getString(R.string.krituliai) + " " + v5);
                d.setText(getString(R.string.vejogreitis) + " " + String.valueOf(Math.round(Double.parseDouble((v3.substring(0, v3.length() - 4))) * 0.514*100.0)/100.0) + " m/s");

                int K = Integer.parseInt(v12.substring(0, v12.length() - 4));
                String kr = "";
                if ((338 <=K) && (K<=360)) kr=getString(R.string.siaures);
                else if ((K <=22))  kr=getString(R.string.siaures);
                else if ((23 <= K) && (K<=67))  kr=getString(R.string.siauresrytu);
                else if ((68<=K) && (K<=112)) kr=getString(R.string.rytu);
                else if ((113<=K) && (K<=157)) kr=getString(R.string.pietryciu);
                else if ((158<= K )&& (K<=202)) kr=getString(R.string.pietu);
                else if ((203<=K) && (K<=247)) kr=getString(R.string.pietvakariu);
                else if ((248<=K) && (K<=292)) kr=getString(R.string.vakaru);
                else if ((293<=K) && (K<=337)) kr=getString(R.string.siauresvakaru);
                e.setText(getString(R.string.vejokryptisx) + ": " + kr + " (" + K + "°)");

                f.setText(getString(R.string.vejogusiai) + ": " + String.valueOf(Math.round(Double.parseDouble((v8.substring(0, v8.length() - 4))) * 0.514*100.0)/100.0) + " m/s");
                g.setText(getString(R.string.debesupadoaukstis) + ": " + Integer.parseInt(v25.substring(0, v25.length() - 3)) + "m");
                h.setText(getString(R.string.horizontalusmatomumas)+ ": " + Double.parseDouble(v34.substring(0, v34.length() - 3)) + " km");

                String kodas = "";
                if ((v33.equals("00 WMO")) && (deau == 0)) {
                    kodas=(getString(R.string.giedra) );
                }
                if ((v33.equals("00 WMO")) && (deau > 0)) {
                    kodas="  ";
                }
                if ((v33.equals("04 WMO")) && (dre >= 80)) {
                    kodas=(getString(R.string.rukana));
                }
                if ((v33.equals("04 WMO")) && (dre < 80)) {
                    kodas=(getString(R.string.migla));
                }
                if ((v33.equals("05 WMO")) && (dre >= 80)) {
                    kodas=(getString(R.string.Rūkas));
                }
                if ((v33.equals("05 WMO")) && (dre < 80)){
                    kodas=(getString(R.string.migla1km));
                }
                if (v33.equals("30 WMO")) {
                    kodas=(getString(R.string.Rūkas));
                }
                if (v33.equals("40 WMO")) {
                    kodas=(getString(R.string.krituliaibe));
                }
                if (v33.equals("51 WMO")) {
                    kodas=(getString(R.string.silpnadulksna));
                }
                if (v33.equals("52 WMO")) {
                    kodas=(getString(R.string.vidutinedulksna));
                }
                if (v33.equals("53 WMO")) {
                    kodas=(getString(R.string.stipridulksna));
                }
                if (v33.equals("61 WMO")) {
                    kodas=(getString(R.string.silpnaslietus));
                }
                if (v33.equals("62 WMO")) {
                    kodas=(getString(R.string.vidutinislietus));
                }
                if (v33.equals("63 WMO")) {
                    kodas=(getString(R.string.stipruslietus));
                }
                if (v33.equals("71 WMO")) {
                    kodas=(getString(R.string.silpnassnygis));
                }
                if (v33.equals("72 WMO")) {
                    kodas=getString(R.string.vidutinissnygis);
                }
                if (v33.equals("73 WMO")) {
                    kodas=(getString(R.string.stiprussnygis));
                }
                if (v33.equals("80 WMO")) {
                    kodas=(getString(R.string.liutiniaikrituliai));
                }
                j.setText(getString(R.string.orokodas) + ": " + v33 + " " + kodas);

                data.setText(getString(R.string.meteoatnaujinta) + " " + v1.substring(0, v1.length() - 13) + " ");

            }

        }





    }



}

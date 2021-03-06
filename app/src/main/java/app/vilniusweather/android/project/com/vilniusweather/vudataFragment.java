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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Paulius on 2014-11-03.
 */

public class vudataFragment extends Fragment {

    String v1, v3, v6, v11, v12, v33, v9, v25;
    TextView t, a, b, d, data;
    ImageView pav, vejas;
    int dre, deau;

    final String LOG_TAG = vudataFragment.class.getSimpleName();

    public vudataFragment() {
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.vudatafragment, menu);
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
        String[] array = new String[10];
        int index = 0;

        String filename = "VilniusWeatherVU.txt";
        File file = new File(getActivity().getFilesDir(), filename);

        // write data if file dont exists
        if (!file.exists())
        {
            String string = "2014-03-29 16:002234567891234" + newline +
                    "11111111" + newline + "22222222" + newline + "111111"
                    + newline + "44444444" + newline + "55555555" + newline + "66666666"
                    + newline + "7777777777"+ newline + "90111111777" + newline + "901111" + newline;

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
            fileInputStream = getActivity().openFileInput("VilniusWeatherVU.txt");
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


        for (int i = 0; i <7; i++){
            Log.v(LOG_TAG, "CHECK IF IT WORKED " + array[i]);
        }

        View v = inflater.inflate(R.layout.fragment_vudata, container, false);


        t = (TextView)v.findViewById(R.id.reiksme1);
        a = (TextView)v.findViewById(R.id.reiksme2);
        b = (TextView)v.findViewById(R.id.reiksme3);
        d = (TextView)v.findViewById(R.id.reiksme5);
        data = (TextView)v.findViewById(R.id.textView);
        pav = (ImageView)v.findViewById(R.id.imageView);

        vejas = (ImageView)v.findViewById(R.id.imageView2);

        v1 = array[0];
        v6 = array[1];
        v11 = array[2];
        v3 = array[3];
        v12 = array[4];
        v33 = array[5];
        v9 = array [6];
        v25 = array [7];
//------------------------------------
        //data.setText(getString(R.string.tiesiogvums) + " " + v1.substring(0, v1.length() - 13) + " ");
        String datagal = v1.substring(0, v1.length() - 13);
        t.setText(v6.substring(0, v6.length() - 4) + "°C");

        a.setText(getString(R.string.slegis) + " " +  v11 );
        b.setText(getString(R.string.vejogreitis) + " " + String.valueOf(Math.round(Double.parseDouble((v3.substring(0, v3.length() - 4))) * 0.514*100.0)/100.0) + " m/s");

        dre = Integer.parseInt(v9.substring(0, v9.length() - 4));
        deau = Integer.parseInt(v25.substring(0, v25.length() - 3));
        int K = Integer.parseInt(v12.substring(0, v12.length() - 4));

        if ((338 <=K) && (K<=360)) vejas.setImageResource(R.drawable.p);
        else if ((K <=22))  vejas.setImageResource(R.drawable.p);
        else if ((23 <= K) && (K<=67))  vejas.setImageResource(R.drawable.pv);
        else if ((68<=K) && (K<=112)) vejas.setImageResource(R.drawable.v);
        else if ((113<=K) && (K<=157)) vejas.setImageResource(R.drawable.sv);
        else if ((158<= K )&& (K<=202)) vejas.setImageResource(R.drawable.s);
        else if ((203<=K) && (K<=247)) vejas.setImageResource(R.drawable.sr);
        else if ((248<=K) && (K<=292)) vejas.setImageResource(R.drawable.r);
        else if ((293<=K) && (K<=337)) vejas.setImageResource(R.drawable.pr);

        if ((v33.equals("00 WMO")) && (deau == 0)) {
            pav.setImageResource(R.drawable.diena_naktis);
            d.setText(getString(R.string.giedra) );
        }
        if ((v33.equals("00 WMO")) && (deau > 0)) {
            pav.setImageResource(R.drawable.debesuota_debesuota);
            d.setText("  ");
        }
        if ((v33.equals("04 WMO")) && (dre >= 80)) {
            pav.setImageResource(R.drawable.rukana_rukana);
            d.setText(getString(R.string.rukana));
        }
        if ((v33.equals("04 WMO")) && (dre < 80)) {
            pav.setImageResource(R.drawable.migla2_04);
            d.setText(getString(R.string.migla));
        }
        if ((v33.equals("05 WMO")) && (dre >= 80)) {
            pav.setImageResource(R.drawable.rukas2_30);
            d.setText(getString(R.string.Rūkas));
        }
        if ((v33.equals("05 WMO")) && (dre < 80)) {
            pav.setImageResource(R.drawable.migla_1km_05);
            d.setText(getString(R.string.migla1km));
        }
        if (v33.equals("30 WMO")) {
            pav.setImageResource(R.drawable.rukas2_30);
            d.setText(getString(R.string.Rūkas));
        }
        if (v33.equals("40 WMO")) {
            pav.setImageResource(R.drawable.krituliai2_40);
            d.setText(getString(R.string.krituliai));
        }
        if (v33.equals("51 WMO")) {
            pav.setImageResource(R.drawable.silpna_dulksna51);
            d.setText(getString(R.string.silpnadulksna));
        }
        if (v33.equals("52 WMO")) {
            pav.setImageResource(R.drawable.vid_dulksna52);
            d.setText(getString(R.string.vidutinedulksna));
        }
        if (v33.equals("53 WMO")) {
            pav.setImageResource(R.drawable.stipri_dulksna53);
            d.setText(getString(R.string.stipridulksna));
        }
        if (v33.equals("61 WMO")) {
            pav.setImageResource(R.drawable.silpnas_lietus61);
            d.setText(getString(R.string.silpnaslietus));
        }
        if (v33.equals("62 WMO")) {
            pav.setImageResource(R.drawable.vidutinis_lietus62);
            d.setText(getString(R.string.vidutinislietus));
        }
        if (v33.equals("63 WMO")) {
            pav.setImageResource(R.drawable.stiprus_lietus63);
            d.setText(getString(R.string.stipruslietus));
        }
        if (v33.equals("71 WMO")) {
            pav.setImageResource(R.drawable.silpnas_snygis71);
            d.setText(getString(R.string.silpnassnygis));
        }
        if (v33.equals("72 WMO")) {
            pav.setImageResource(R.drawable.vidutinis_snygis72);
            d.setText(getString(R.string.vidutinissnygis));
        }
        if (v33.equals("73 WMO")) {
            pav.setImageResource(R.drawable.stiprus_snygis73);
            d.setText(getString(R.string.stiprussnygis));
        }
        if (v33.equals("80 WMO")) {
            pav.setImageResource(R.drawable.liutiniai_krituliai80);
            d.setText(getString(R.string.liutiniaikrituliai));
        }

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dabar = new java.util.Date();
            Date atnaujinta = df.parse(datagal);
            long skirt = ((dabar.getTime() - atnaujinta.getTime()) / 1000 / 60) + 1;

            if (skirt < 60){                // Gauna minučių skaičų
                data.setText(getString(R.string.tiesiogvums)  + " " + skirt + " " + getString(R.string.tiesiogvumsmin)+ " ");
            }
            skirt = skirt / 60;             // Gauna valandų skaičių
            if ((skirt >= 1) && (skirt < 24)){
                data.setText(getString(R.string.tiesiogvums)  + " " + skirt + " " + getString(R.string.tiesiogvumsh)+ " ");
            }
            skirt = skirt / 24;             // Gauna dienų skaičių
            if (skirt == 1){
                data.setText(getString(R.string.tiesiogvumspa)  + " " + getString(R.string.tiesiogvumsday)+ " ");
            }
            if (skirt >= 2){
                data.setText(getString(R.string.tiesiogvums)  + " " + skirt + " " + getString(R.string.tiesiogvumsdays)+ " ");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //-------------------------------

        Log.e(LOG_TAG, "reiksme ONCREATEVIEW:" + v3);
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
            final String OWM_zeno_Spd_5s_Kt = "zeno_Spd_5s_Kt";
            final String OWM_zeno_AT_5s_C = "zeno_AT_5s_C";
            final String OWM_zeno_BP2_5s_Mb = "zeno_BP2_5s_Mb";
            final String OWM_zeno_Dir_5s = "zeno_Dir_5s";
            final String OWM_sws200_synop_code = "sws200_synop_code";
            final String OWM_cl31_ClH1_30s_Ft = "cl31_ClH1_30s_Ft";     // Debesu aukstis, Apatinė debesų pado reikšmė
            final String OWM_zeno_RH_5s = "zeno_RH_5s";                // Santykinis oro drėgnis

            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            JSONObject dayForecast = weatherArray.getJSONObject(0);

            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v1 = weatherObject.getString(OWM_timestamp);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v3 = weatherObject.getString (OWM_zeno_Spd_5s_Kt);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v6 = weatherObject.getString(OWM_zeno_AT_5s_C);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v11 = weatherObject.getString (OWM_zeno_BP2_5s_Mb);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v12 = weatherObject.getString(OWM_zeno_Dir_5s);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            v33 = weatherObject.getString (OWM_sws200_synop_code);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);         // Drėgnis
            v9 = weatherObject.getString (OWM_zeno_RH_5s);
            weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);         // Debesų aukštis
            v25 = weatherObject.getString(OWM_cl31_ClH1_30s_Ft);

            //rasymas
            String[] array = new String[10];
            int index = 0;

            String newline = System.getProperty("line.separator");
            FileOutputStream fOut = null;
            try {
                fOut = getActivity().openFileOutput("VilniusWeatherVU.txt",Context.MODE_WORLD_READABLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String string = v1 + newline + v6 + newline +v11 + newline +v3 + newline + v12 + newline +v33 + newline +v9 + newline + v25 + newline;

            try {
                fOut.write(string.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream fileInputStream= null;
            try {
                fileInputStream = getActivity().openFileInput("VilniusWeatherVU.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            //----------------------------

            Log.v(LOG_TAG, "FAAAAAAR NULL?: " + v33);


            for (String s : resultStrs) {
                Log.v(LOG_TAG, "Forecast entry: " + s);
            }
            Log.e(LOG_TAG, "KODAS:" + v33);

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

            int numDays = 6;

            try {
                final String FORECAST_BASE_URL =
                        "http://msapp.vub.lt/weather.json";
                //"https://gist.githubusercontent.com/Paul127/dc9751d05e73274f6365/raw/4f3bd4f48ff4671300c914737e9d6767cc1bc064/gistfile1.txt";

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

                //data.setText(getString(R.string.tiesiogvums)  + " " + v1.substring(0, v1.length() - 13) + " ");
                String datagal = v1.substring(0, v1.length() - 13);
                t.setText(v6.substring(0, v6.length() - 4) + "°C");

                a.setText(getString(R.string.slegis) + " " + v11);
                b.setText(getString(R.string.vejogreitis) + " " + String.valueOf(Math.round(Double.parseDouble((v3.substring(0, v3.length() - 4))) * 0.514*100.0)/100.0) + " m/s");

                dre = Integer.parseInt(v9.substring(0, v9.length() - 4));
                deau = Integer.parseInt(v25.substring(0, v25.length() - 3));

                int K = Integer.parseInt(v12.substring(0, v12.length() - 4));

                if ((338 <=K) && (K<=360)) vejas.setImageResource(R.drawable.p);
                else if ((K <=22))  vejas.setImageResource(R.drawable.p);
                else if ((23 <= K) && (K<=67))  vejas.setImageResource(R.drawable.pv);
                else if ((68<=K) && (K<=112)) vejas.setImageResource(R.drawable.v);
                else if ((113<=K) && (K<=157)) vejas.setImageResource(R.drawable.sv);
                else if ((158<= K )&& (K<=202)) vejas.setImageResource(R.drawable.s);
                else if ((203<=K) && (K<=247)) vejas.setImageResource(R.drawable.sr);
                else if ((248<=K) && (K<=292)) vejas.setImageResource(R.drawable.r);
                else if ((293<=K) && (K<=337)) vejas.setImageResource(R.drawable.pr);

//                00 = "";
//                04 = "Migla";
//                05 = "Migla, matomumas < 1 km";
//                30 = "Rūkas";
//                40 = "Krituliai";
//                51 = "Silpna dulksna";
//                52 = "Vidutinė dulksna";
//                53 = "Stipri dulksna";
//                61 = "Silpnas lietus";
//                62 = "Vidutinis lietus";
//                63 = "Stiprus lietus";
//                71 = "Silpnas snygis";
//                72 = "Vidutinis snygis";
//                73 = "Stiprus snygis";
//                80 = "Liūtiniai krituliai";


                if ((v33.equals("00 WMO")) && (deau == 0)) {
                    pav.setImageResource(R.drawable.diena_naktis);
                    d.setText(getString(R.string.giedra) );
                }
                if ((v33.equals("00 WMO")) && (deau > 0)) {
                    pav.setImageResource(R.drawable.debesuota_debesuota);
                    d.setText("  ");
                }
                if ((v33.equals("04 WMO")) && (dre >= 80)) {
                    pav.setImageResource(R.drawable.rukana_rukana);
                    d.setText(getString(R.string.rukana));
                }
                if ((v33.equals("04 WMO")) && (dre < 80)) {
                    pav.setImageResource(R.drawable.migla2_04);
                    d.setText(getString(R.string.migla));
                }
                if ((v33.equals("05 WMO")) && (dre >= 80)) {
                    pav.setImageResource(R.drawable.rukas2_30);
                    d.setText(getString(R.string.Rūkas));
                }
                if ((v33.equals("05 WMO")) && (dre < 80)) {
                    pav.setImageResource(R.drawable.migla_1km_05);
                    d.setText(getString(R.string.migla1km));
                }
                if (v33.equals("30 WMO")) {
                    pav.setImageResource(R.drawable.rukas2_30);
                    d.setText(getString(R.string.Rūkas));
                }
                if (v33.equals("40 WMO")) {
                    pav.setImageResource(R.drawable.krituliai2_40);
                    d.setText(getString(R.string.krituliai));
                }
                if (v33.equals("51 WMO")) {
                    pav.setImageResource(R.drawable.silpna_dulksna51);
                    d.setText(getString(R.string.silpnadulksna));
                }
                if (v33.equals("52 WMO")) {
                    pav.setImageResource(R.drawable.vid_dulksna52);
                    d.setText(getString(R.string.vidutinedulksna));
                }
                if (v33.equals("53 WMO")) {
                    pav.setImageResource(R.drawable.stipri_dulksna53);
                    d.setText(getString(R.string.stipridulksna));
                }
                if (v33.equals("61 WMO")) {
                    pav.setImageResource(R.drawable.silpnas_lietus61);
                    d.setText(getString(R.string.silpnaslietus));
                }
                if (v33.equals("62 WMO")) {
                    pav.setImageResource(R.drawable.vidutinis_lietus62);
                    d.setText(getString(R.string.vidutinislietus));
                }
                if (v33.equals("63 WMO")) {
                    pav.setImageResource(R.drawable.stiprus_lietus63);
                    d.setText(getString(R.string.stipruslietus));
                }
                if (v33.equals("71 WMO")) {
                    pav.setImageResource(R.drawable.silpnas_snygis71);
                    d.setText(getString(R.string.silpnassnygis));
                }
                if (v33.equals("72 WMO")) {
                    pav.setImageResource(R.drawable.vidutinis_snygis72);
                    d.setText(getString(R.string.vidutinissnygis));
                }
                if (v33.equals("73 WMO")) {
                    pav.setImageResource(R.drawable.stiprus_snygis73);
                    d.setText(getString(R.string.stiprussnygis));
                }
                if (v33.equals("80 WMO")) {
                    pav.setImageResource(R.drawable.liutiniai_krituliai80);
                    d.setText(getString(R.string.liutiniaikrituliai));
                }

                try {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date dabar = new java.util.Date();
                    Date atnaujinta = df.parse(datagal);
                    long skirt = ((dabar.getTime() - atnaujinta.getTime()) / 1000 / 60) + 1;

                    if (skirt < 60){                // Gauna minučių skaičų
                        data.setText(getString(R.string.tiesiogvums)  + " " + skirt + " " + getString(R.string.tiesiogvumsmin)+ " ");
                    }
                    skirt = skirt / 60;             // Gauna valandų skaičių
                    if ((skirt >= 1) && (skirt < 24)){
                        data.setText(getString(R.string.tiesiogvums)  + " " + skirt + " " + getString(R.string.tiesiogvumsh)+ " ");
                    }
                    skirt = skirt / 24;             // Gauna dienų skaičių
                    if (skirt == 1){
                        data.setText(getString(R.string.tiesiogvumspa)  + " " + getString(R.string.tiesiogvumsday)+ " ");
                    }
                    if (skirt >= 2){
                        data.setText(getString(R.string.tiesiogvums)  + " " + skirt + " " + getString(R.string.tiesiogvumsdays)+ " ");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }

    }

}
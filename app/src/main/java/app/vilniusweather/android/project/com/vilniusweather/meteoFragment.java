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


public class meteoFragment extends Fragment {

    String v1;
    String v2;
    String v3;
    String v4;
    String v5;
    String v6;
    String v7;
    String v11;
    String v12;
    String v13;
    String v14;
    String v15;
    String v16;
    String v17;
    String v18;
    TextView t, a, c, d, e, f, g, aa, cc, dd, ee, ff, tt;
    ImageView pav;
    ImageView pav2;
    ImageView vejas1, vejas2;

    final String LOG_TAG = vudataFragment.class.getSimpleName();

    public meteoFragment() {
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.meteo, menu);
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

        View v = inflater.inflate(R.layout.fragment_meteo_fragment, container, false);

        t = (TextView)v.findViewById(R.id.reiksme1);
        a = (TextView)v.findViewById(R.id.reiksme2);
        c = (TextView)v.findViewById(R.id.reiksme3);
        d = (TextView)v.findViewById(R.id.reiksme4);
        e = (TextView)v.findViewById(R.id.reiksme5);
        f = (TextView)v.findViewById(R.id.reiksme11);
        g = (TextView)v.findViewById(R.id.reiksme22);
        tt = (TextView)v.findViewById(R.id.reiksme33);
        aa = (TextView)v.findViewById(R.id.reiksme44);
        cc = (TextView)v.findViewById(R.id.reiksme55);
        dd = (TextView)v.findViewById(R.id.reiskinys1);
        ee = (TextView)v.findViewById(R.id.reiskinys2);

        ff = (TextView)v.findViewById(R.id.textView);


        pav = (ImageView)v.findViewById(R.id.imageView);
        pav2 = (ImageView)v.findViewById(R.id.imageView2);

        vejas1 = (ImageView)v.findViewById(R.id.vejas1);
        vejas2 = (ImageView)v.findViewById(R.id.vejas2);



        Log.e(LOG_TAG, "reiksme ONCREATEVIEW:" + v3);

        String newline = System.getProperty("line.separator");
        //rasymas
        String[] array = new String[16];
        int index = 0;

        String filename = "VilniusWeatherVUMeteo.txt";
        File file = new File(getActivity().getFilesDir(), filename);

        // write data if file dont exists
        if (!file.exists())
        {
            String string = "1111111111" + newline + "111111111"
                    + newline + "111111"
                    + newline + "111111111" +
                    newline + "111" + newline + "111111111" +
                    newline + "11111111" + newline + "111111111"
                    + newline + "111111111" + newline +
                    "1111111" + newline + "1111111111" + newline + "111111111" + newline + "1111111111"
                    + newline + "111" + newline +
                    "1111111111" + newline;

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
            fileInputStream = getActivity().openFileInput("VilniusWeatherVUMeteo.txt");
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


        for (int i = 0; i <14; i++){
            Log.v(LOG_TAG, "CHECK IF IT WORKED " + array[i]);
        }


        //diena1

        t.setText(getString(R.string.temperatura) + " " + array[0] + " °C");

        a.setText(getString(R.string.vejogreitis) + " " + array[1] + " m/s");

        int K = Integer.parseInt(array[2]);

        if ((338 <=K) && (K<=360)) vejas1.setImageResource(R.drawable.p);
        else if ((K <=22))  vejas1.setImageResource(R.drawable.p);
        else if ((23 <= K) && (K<=67))  vejas1.setImageResource(R.drawable.pv);
        else if ((68<=K) && (K<=112)) vejas1.setImageResource(R.drawable.v);
        else if ((113<=K) && (K<=157)) vejas1.setImageResource(R.drawable.sv);
        else if ((158<= K )&& (K<=202)) vejas1.setImageResource(R.drawable.s);
        else if ((203<=K) && (K<=247)) vejas1.setImageResource(R.drawable.sr);
        else if ((248<=K) && (K<=292)) vejas1.setImageResource(R.drawable.r);
        else if ((293<=K) && (K<=337)) vejas1.setImageResource(R.drawable.pr);

        c.setText(getString(R.string.debesuotumas) + " " + array[3] + " %");

        d.setText(getString(R.string.slegis) + " " + array[4] + " hPa");

        e.setText(getString(R.string.krituliai) + " " + array[5] + " mm");

        if (array[6].equals("giedra")) {
            pav.setImageResource(R.drawable.giedra_saule);
            dd.setText(getString(R.string.giedra));
        }
        if (array[6].equals("mazai_deb")) {
            pav.setImageResource(R.drawable.mazai_deb_mazai_debesuota);
            dd.setText(getString(R.string.mazaidebesuota));
        }
        if (array[6].equals("deb_prag")) {
            pav.setImageResource(R.drawable.deb_prag_debesuota_su_pragiedruliais);
            dd.setText(getString(R.string.debesuotasupragiedruliais));
        }
        if (array[6].equals("debesuota")) {
            pav.setImageResource(R.drawable.debesuota_debesuota);
            dd.setText(getString(R.string.debesuota));
        }
        if (array[6].equals("trump_lietus")) {
            pav.setImageResource(R.drawable.trump_lietus_trumpalaikis_lietus);
            dd.setText(getString(R.string.trumpalaikislietus));
        }
        if (array[6].equals("prot_lietus")) {
            pav.setImageResource(R.drawable.prot_lietus_protarpiais_lietus);
            dd.setText(getString(R.string.protarpiaislietus));
        }
        if (array[6].equals("ned_lietus")) {
            pav.setImageResource(R.drawable.prot_lietus_silpnas_lietus);
            dd.setText(getString(R.string.silpnaslietus));
        }
        if (array[6].equals("lietus")) {
            pav.setImageResource(R.drawable.lietus_lietus);
            dd.setText(getString(R.string.lietus));
        }
        if (array[6].equals("smark_lietus")) {
            pav.setImageResource(R.drawable.smark_lietus_smarkus_lietus);
            dd.setText(getString(R.string.smarkuslietus));
        }
        if (array[6].equals("krusa")) {
            pav.setImageResource(R.drawable.krusa_krusa);
            dd.setText(getString(R.string.krusa));
        }
        if (array[6].equals("slapdriba")) {
            pav.setImageResource(R.drawable.slapdriba_slapdriba);
            dd.setText(getString(R.string.slapdriba));
        }
        if (array[6].equals("perkunija")) {
            pav.setImageResource(R.drawable.perkunija_perkunija);
            dd.setText(getString(R.string.perkunija));
        }
        if (array[6].equals("trump_lietus_perk")) {
            pav.setImageResource(R.drawable.trump_lietus_perk_trumpalaikis_lietus_su_perkunija);
            dd.setText(getString(R.string.trumpaslietussuperkunija));
        }
        if (array[6].equals("lietus_perk")) {
            pav.setImageResource(R.drawable.lietus_perk_lietus_su_perkunija);
            dd.setText(getString(R.string.lietussuperkunija));
        }
        if (array[6].equals("trump_sniegas")) {
            pav.setImageResource(R.drawable.trump_sniegas_trumpalaikis_snygis);
            dd.setText(getString(R.string.trumpalaikissnygis));
        }
        if (array[6].equals("prot_sniegas")) {
            pav.setImageResource(R.drawable.prot_sniegas_protarpiais_sniegas);
            dd.setText(getString(R.string.protarpiaissniegas));
        }
        if (array[6].equals("ned_sniegas")) {
            pav.setImageResource(R.drawable.ned_sniegas_silpnas_snygis);
            dd.setText(getString(R.string.silpnassnygis));
        }
        if (array[6].equals("sniegas")) {
            pav.setImageResource(R.drawable.sniegas_snygis);
            dd.setText(getString(R.string.snygis));
        }
        if (array[6].equals("smark_sniegas")) {
            pav.setImageResource(R.drawable.smark_sniegas_smarkus_snygis);
            dd.setText(getString(R.string.smarkussnygis));
        }
        if (array[6].equals("rukas")) {
            pav.setImageResource(R.drawable.rukas_rukas);
            dd.setText(getString(R.string.Rūkas));
        }
        if (array[6].equals("lijundra")) {
            pav.setImageResource(R.drawable.lijundra_lijundra);
            dd.setText(getString(R.string.lijundra));
        }
        if (array[6].equals("plikledis")) {
            pav.setImageResource(R.drawable.plikledis_plikledis);
            dd.setText(getString(R.string.plikledis));
        }

//diena2

        f.setText(getString(R.string.temperatura) + " " + array[7] + " °C");

        g.setText(getString(R.string.vejogreitis) + " " + array[8] + " m/s");

        K = Integer.parseInt(array[9]);

        if ((338 <=K) && (K<=360)) vejas2.setImageResource(R.drawable.s);
        else if ((K <=22))  vejas2.setImageResource(R.drawable.s);
        else if ((23 <= K) && (K<=67))  vejas2.setImageResource(R.drawable.sr);
        else if ((68<=K) && (K<=112)) vejas2.setImageResource(R.drawable.r);
        else if ((113<=K) && (K<=157)) vejas2.setImageResource(R.drawable.pr);
        else if ((158<= K )&& (K<=202)) vejas2.setImageResource(R.drawable.p);
        else if ((203<=K) && (K<=247)) vejas2.setImageResource(R.drawable.pv);
        else if ((248<=K) && (K<=292)) vejas2.setImageResource(R.drawable.v);
        else if ((293<=K) && (K<=337)) vejas2.setImageResource(R.drawable.sv);

        tt.setText(getString(R.string.debesuotumas) + " " + array[10] + " %");

        aa.setText(getString(R.string.slegis) + " " + array[11] + " hPa");

        cc.setText(getString(R.string.krituliai) + " " + array[12] + " mm");

        if (array[13].equals("giedra")) {
            pav2.setImageResource(R.drawable.giedra_saule);
            ee.setText(getString(R.string.giedra));
        }
        if (array[13].equals("mazai_deb")) {
            pav2.setImageResource(R.drawable.mazai_deb_mazai_debesuota);
            ee.setText(getString(R.string.mazaidebesuota));
        }
        if (array[13].equals("deb_prag")) {
            pav2.setImageResource(R.drawable.deb_prag_debesuota_su_pragiedruliais);
            ee.setText(getString(R.string.debesuotasupragiedruliais));
        }
        if (array[13].equals("debesuota")) {
            pav2.setImageResource(R.drawable.debesuota_debesuota);
            ee.setText(getString(R.string.debesuota));
        }
        if (array[13].equals("trump_lietus")) {
            pav2.setImageResource(R.drawable.trump_lietus_trumpalaikis_lietus);
            ee.setText(getString(R.string.trumpalaikislietus));
        }
        if (array[13].equals("prot_lietus")) {
            pav2.setImageResource(R.drawable.prot_lietus_protarpiais_lietus);
            ee.setText(getString(R.string.protarpiaislietus));
        }
        if (array[13].equals("ned_lietus")) {
            pav2.setImageResource(R.drawable.prot_lietus_silpnas_lietus);
            ee.setText(getString(R.string.silpnaslietus));
        }
        if (array[13].equals("lietus")) {
            pav2.setImageResource(R.drawable.lietus_lietus);
            ee.setText(getString(R.string.lietus));
        }
        if (array[13].equals("smark_lietus")) {
            pav2.setImageResource(R.drawable.smark_lietus_smarkus_lietus);
            ee.setText(getString(R.string.smarkuslietus));
        }
        if (array[13].equals("krusa")) {
            pav2.setImageResource(R.drawable.krusa_krusa);
            ee.setText(getString(R.string.krusa));
        }
        if (array[13].equals("slapdriba")) {
            pav2.setImageResource(R.drawable.slapdriba_slapdriba);
            ee.setText(getString(R.string.slapdriba));
        }
        if (array[13].equals("perkunija")) {
            pav2.setImageResource(R.drawable.perkunija_perkunija);
            ee.setText(getString(R.string.perkunija));
        }
        if (array[13].equals("trump_lietus_perk")) {
            pav2.setImageResource(R.drawable.trump_lietus_perk_trumpalaikis_lietus_su_perkunija);
            ee.setText(getString(R.string.trumpaslietussuperkunija));
        }
        if (array[13].equals("lietus_perk")) {
            pav2.setImageResource(R.drawable.lietus_perk_lietus_su_perkunija);
            ee.setText(getString(R.string.lietussuperkunija));
        }
        if (array[13].equals("trump_sniegas")) {
            pav2.setImageResource(R.drawable.trump_sniegas_trumpalaikis_snygis);
            ee.setText(getString(R.string.trumpalaikissnygis));
        }
        if (array[13].equals("prot_sniegas")) {
            pav2.setImageResource(R.drawable.prot_sniegas_protarpiais_sniegas);
            ee.setText(getString(R.string.protarpiaissniegas));
        }
        if (array[13].equals("ned_sniegas")) {
            pav2.setImageResource(R.drawable.ned_sniegas_silpnas_snygis);
            ee.setText(getString(R.string.silpnassnygis));
        }
        if (array[13].equals("sniegas")) {
            pav2.setImageResource(R.drawable.sniegas_snygis);
            ee.setText(getString(R.string.snygis));
        }
        if (array[13].equals("smark_sniegas")) {
            pav2.setImageResource(R.drawable.smark_sniegas_smarkus_snygis);
            ee.setText(getString(R.string.smarkussnygis));
        }
        if (array[13].equals("rukas")) {
            pav2.setImageResource(R.drawable.rukas_rukas);
            ee.setText(getString(R.string.Rūkas));
        }
        if (array[13].equals("lijundra")) {
            pav2.setImageResource(R.drawable.lijundra_lijundra);
            ee.setText(getString(R.string.lijundra));
        }
        if (array[13].equals("plikledis")) {
            pav2.setImageResource(R.drawable.plikledis_plikledis);
            ee.setText(getString(R.string.plikledis));
        }

        ff.setText(getString(R.string.meteoatnaujinta) + array[14].substring(0, 10) + " ");

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
        public String[] resultStrs = new String[18];
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";

            final String OWM_duom_laikas_utc = "duom_laikas_utc";
            final String OWM_oro_temp = "oro_temp";
            final String OWM_vejo_greitis = "vejo_greitis";
            final String OWM_vejo_gusiai = "vejo_gusiai";
            final String OWM_vejo_kryptis = "vejo_kryptis";
            final String OWM_debesuotumas = "debesuotumas";
            final String OWM_slegis_juros_lyg = "slegis_juros_lyg";
            final String OWM_krituliai = "krituliai";
            final String OWM_reiskinys = "reiskinys";
            final String OWM_duom_laikas_utc2 = "duom_laikas_utc";
            final String OWM_oro_temp2 = "oro_temp";
            final String OWM_vejo_greitis2 = "vejo_greitis";
            final String OWM_vejo_gusiai2 = "vejo_gusiai";
            final String OWM_vejo_kryptis2 = "vejo_kryptis";
            final String OWM_debesuotumas2 = "debesuotumas";
            final String OWM_slegis_juros_lyg2 = "slegis_juros_lyg";
            final String OWM_krituliai2 = "krituliai";
            final String OWM_reiskinys2 = "reiskinys";


            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            int i = 0;

                // Get the JSON object representing the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);


                // Get the JSON object representing the day
                // JSONObject dayForecast = weatherArray.getJSONObject(0);

                // description is in a child array called "weather", which is 1 element long.

                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v18 = weatherObject.getString(OWM_duom_laikas_utc);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v1 = weatherObject.getString(OWM_oro_temp);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v2 = weatherObject.getString(OWM_vejo_greitis);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v3 = weatherObject.getString(OWM_vejo_kryptis);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v4 = weatherObject.getString(OWM_debesuotumas);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v5 = weatherObject.getString(OWM_slegis_juros_lyg);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v6 = weatherObject.getString(OWM_krituliai);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v7 = weatherObject.getString(OWM_reiskinys);

                //dayForecast = weatherArray.getJSONObject(1);
            i++;
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v11 = weatherObject.getString(OWM_oro_temp2);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v12 = weatherObject.getString(OWM_vejo_greitis2);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v13 = weatherObject.getString(OWM_vejo_kryptis2);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v14 = weatherObject.getString(OWM_debesuotumas2);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v15 = weatherObject.getString(OWM_slegis_juros_lyg2);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v16 = weatherObject.getString(OWM_krituliai2);
                weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(i);
                v17 = weatherObject.getString(OWM_reiskinys2);

            for (String s : resultStrs) {
                Log.v(LOG_TAG, "Forecast entry: " + s);
            }


            //rasymas
            String[] array = new String[15];
            int index = 0;

            String newline = System.getProperty("line.separator");
            FileOutputStream fOut = null;
            try {
                fOut = getActivity().openFileOutput("VilniusWeatherVUMeteo.txt",Context.MODE_WORLD_READABLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String string = v1 + newline + v2 + newline +v3 + newline +v4
                    + newline + v5 + newline +v6 + newline + v7 + newline + v11
                    + newline + v12 + newline +v13 + newline +v14 + newline + v15
                    + newline +v16 + newline +v17 + newline
                    + v18 + newline;

            try {
                fOut.write(string.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream fileInputStream= null;
            try {
                fileInputStream = getActivity().openFileInput("VilniusWeatherVUMeteo.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            //----------------------------

            return resultStrs;

        }


        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            int numDays = 15;

            try {
                final String FORECAST_BASE_URL ="http://msapp.vub.lt/meteo.json";

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

                //diena1

                t.setText(getString(R.string.temperatura) + " " + v1 + " °C");

                a.setText(getString(R.string.vejogreitis) + " " + v2 + " m/s");

                int K = Integer.parseInt(v3);

                if ((338 <=K) && (K<=360)) vejas1.setImageResource(R.drawable.s);
                else if ((K <=22))  vejas1.setImageResource(R.drawable.s);
                else if ((23 <= K) && (K<=67))  vejas1.setImageResource(R.drawable.sr);
                else if ((68<=K) && (K<=112)) vejas1.setImageResource(R.drawable.r);
                else if ((113<=K) && (K<=157)) vejas1.setImageResource(R.drawable.pr);
                else if ((158<= K )&& (K<=202)) vejas1.setImageResource(R.drawable.p);
                else if ((203<=K) && (K<=247)) vejas1.setImageResource(R.drawable.pv);
                else if ((248<=K) && (K<=292)) vejas1.setImageResource(R.drawable.v);
                else if ((293<=K) && (K<=337)) vejas1.setImageResource(R.drawable.sv);

                c.setText(getString(R.string.debesuotumas) + " " + v4 + " %");

                d.setText(getString(R.string.slegis) + " " + v5 + " hPa");

                e.setText(getString(R.string.krituliai) + " " + v6 + " mm");

                if (v7.equals("giedra")) {
                    pav.setImageResource(R.drawable.giedra_saule);
                    dd.setText(getString(R.string.giedra));
                }
                if (v7.equals("mazai_deb")) {
                    pav.setImageResource(R.drawable.mazai_deb_mazai_debesuota);
                    dd.setText(getString(R.string.mazaidebesuota));
                }
                if (v7.equals("deb_prag")) {
                    pav.setImageResource(R.drawable.deb_prag_debesuota_su_pragiedruliais);
                    dd.setText(getString(R.string.debesuotasupragiedruliais));
                }
                if (v7.equals("debesuota")) {
                    pav.setImageResource(R.drawable.debesuota_debesuota);
                    dd.setText(getString(R.string.debesuota));
                }
                if (v7.equals("trump_lietus")) {
                    pav.setImageResource(R.drawable.trump_lietus_trumpalaikis_lietus);
                    dd.setText(getString(R.string.trumpalaikislietus));
                }
                if (v7.equals("prot_lietus")) {
                    pav.setImageResource(R.drawable.prot_lietus_protarpiais_lietus);
                    dd.setText(getString(R.string.protarpiaislietus));
                }
                if (v7.equals("ned_lietus")) {
                    pav.setImageResource(R.drawable.prot_lietus_silpnas_lietus);
                    dd.setText(getString(R.string.silpnaslietus));
                }
                if (v7.equals("lietus")) {
                    pav.setImageResource(R.drawable.lietus_lietus);
                    dd.setText(getString(R.string.lietus));
                }
                if (v7.equals("smark_lietus")) {
                    pav.setImageResource(R.drawable.smark_lietus_smarkus_lietus);
                    dd.setText(getString(R.string.smarkuslietus));
                }
                if (v7.equals("krusa")) {
                    pav.setImageResource(R.drawable.krusa_krusa);
                    dd.setText(getString(R.string.krusa));
                }
                if (v7.equals("slapdriba")) {
                    pav.setImageResource(R.drawable.slapdriba_slapdriba);
                    dd.setText(getString(R.string.slapdriba));
                }
                if (v7.equals("perkunija")) {
                    pav.setImageResource(R.drawable.perkunija_perkunija);
                    dd.setText(getString(R.string.perkunija));
                }
                if (v7.equals("trump_lietus_perk")) {
                    pav.setImageResource(R.drawable.trump_lietus_perk_trumpalaikis_lietus_su_perkunija);
                    dd.setText(getString(R.string.trumpaslietussuperkunija));
                }
                if (v7.equals("lietus_perk")) {
                    pav.setImageResource(R.drawable.lietus_perk_lietus_su_perkunija);
                    dd.setText(getString(R.string.lietussuperkunija));
                }
                if (v7.equals("trump_sniegas")) {
                    pav.setImageResource(R.drawable.trump_sniegas_trumpalaikis_snygis);
                    dd.setText(getString(R.string.trumpalaikissnygis));
                }
                if (v7.equals("prot_sniegas")) {
                    pav.setImageResource(R.drawable.prot_sniegas_protarpiais_sniegas);
                    dd.setText(getString(R.string.protarpiaissniegas));
                }
                if (v7.equals("ned_sniegas")) {
                    pav.setImageResource(R.drawable.ned_sniegas_silpnas_snygis);
                    dd.setText(getString(R.string.silpnassnygis));
                }
                if (v7.equals("sniegas")) {
                    pav.setImageResource(R.drawable.sniegas_snygis);
                    dd.setText(getString(R.string.snygis));
                }
                if (v7.equals("smark_sniegas")) {
                    pav.setImageResource(R.drawable.smark_sniegas_smarkus_snygis);
                    dd.setText(getString(R.string.smarkussnygis));
                }
                if (v7.equals("rukas")) {
                    pav.setImageResource(R.drawable.rukas_rukas);
                    dd.setText(getString(R.string.Rūkas));
                }
                if (v7.equals("lijundra")) {
                    pav.setImageResource(R.drawable.lijundra_lijundra);
                    dd.setText(getString(R.string.lijundra));
                }
                if (v7.equals("plikledis")) {
                    pav.setImageResource(R.drawable.plikledis_plikledis);
                    dd.setText(getString(R.string.plikledis));
                }

//diena2

                f.setText(getString(R.string.temperatura) + " " + v11 + " °C");

                g.setText(getString(R.string.vejogreitis) + " " + v12 + " m/s");

                K = Integer.parseInt(v13);

                if ((338 <=K) && (K<=360)) vejas2.setImageResource(R.drawable.p);
                else if ((K <=22))  vejas2.setImageResource(R.drawable.p);
                else if ((23 <= K) && (K<=67))  vejas2.setImageResource(R.drawable.pv);
                else if ((68<=K) && (K<=112)) vejas2.setImageResource(R.drawable.v);
                else if ((113<=K) && (K<=157)) vejas2.setImageResource(R.drawable.sv);
                else if ((158<= K )&& (K<=202)) vejas2.setImageResource(R.drawable.s);
                else if ((203<=K) && (K<=247)) vejas2.setImageResource(R.drawable.sr);
                else if ((248<=K) && (K<=292)) vejas2.setImageResource(R.drawable.r);
                else if ((293<=K) && (K<=337)) vejas2.setImageResource(R.drawable.pr);

                tt.setText(getString(R.string.debesuotumas) + " " + v14 + " %");

                aa.setText(getString(R.string.slegis) + " " +v15 + " hPa");

                cc.setText(getString(R.string.krituliai)  + " " +v16 + " mm");

//                pav.setImageResource(R.drawable.ic_launcher);

                if (v17.equals("giedra")) {
                    pav2.setImageResource(R.drawable.giedra_saule);
                    ee.setText(getString(R.string.giedra));
                }
                if (v17.equals("mazai_deb")) {
                    pav2.setImageResource(R.drawable.mazai_deb_mazai_debesuota);
                    ee.setText(getString(R.string.mazaidebesuota));
                }
                if (v17.equals("deb_prag")) {
                    pav2.setImageResource(R.drawable.deb_prag_debesuota_su_pragiedruliais);
                    ee.setText(getString(R.string.debesuotasupragiedruliais));
                }
                if (v17.equals("debesuota")) {
                    pav2.setImageResource(R.drawable.debesuota_debesuota);
                    ee.setText(getString(R.string.debesuota));
                }
                if (v17.equals("trump_lietus")) {
                    pav2.setImageResource(R.drawable.trump_lietus_trumpalaikis_lietus);
                    ee.setText(getString(R.string.trumpalaikislietus));
                }
                if (v17.equals("prot_lietus")) {
                    pav2.setImageResource(R.drawable.prot_lietus_protarpiais_lietus);
                    ee.setText(getString(R.string.protarpiaislietus));
                }
                if (v17.equals("ned_lietus")) {
                    pav2.setImageResource(R.drawable.prot_lietus_silpnas_lietus);
                    ee.setText(getString(R.string.silpnaslietus));
                }
                if (v17.equals("lietus")) {
                    pav2.setImageResource(R.drawable.lietus_lietus);
                    ee.setText(getString(R.string.lietus));
                }
                if (v17.equals("smark_lietus")) {
                    pav2.setImageResource(R.drawable.smark_lietus_smarkus_lietus);
                    ee.setText(getString(R.string.smarkuslietus));
                }
                if (v17.equals("krusa")) {
                    pav2.setImageResource(R.drawable.krusa_krusa);
                    ee.setText(getString(R.string.krusa));
                }
                if (v17.equals("slapdriba")) {
                    pav2.setImageResource(R.drawable.slapdriba_slapdriba);
                    ee.setText(getString(R.string.slapdriba));
                }
                if (v17.equals("perkunija")) {
                    pav2.setImageResource(R.drawable.perkunija_perkunija);
                    ee.setText(getString(R.string.perkunija));
                }
                if (v17.equals("trump_lietus_perk")) {
                    pav2.setImageResource(R.drawable.trump_lietus_perk_trumpalaikis_lietus_su_perkunija);
                    ee.setText(getString(R.string.trumpaslietussuperkunija));
                }
                if (v17.equals("lietus_perk")) {
                    pav2.setImageResource(R.drawable.lietus_perk_lietus_su_perkunija);
                    ee.setText(getString(R.string.lietussuperkunija));
                }
                if (v17.equals("trump_sniegas")) {
                    pav2.setImageResource(R.drawable.trump_sniegas_trumpalaikis_snygis);
                    ee.setText(getString(R.string.trumpalaikissnygis));
                }
                if (v17.equals("prot_sniegas")) {
                    pav2.setImageResource(R.drawable.prot_sniegas_protarpiais_sniegas);
                    ee.setText(getString(R.string.protarpiaissniegas));
                }
                if (v17.equals("ned_sniegas")) {
                    pav2.setImageResource(R.drawable.ned_sniegas_silpnas_snygis);
                    ee.setText(getString(R.string.silpnassnygis));
                }
                if (v17.equals("sniegas")) {
                    pav2.setImageResource(R.drawable.sniegas_snygis);
                    ee.setText(getString(R.string.snygis));
                }
                if (v17.equals("smark_sniegas")) {
                    pav2.setImageResource(R.drawable.smark_sniegas_smarkus_snygis);
                    ee.setText(getString(R.string.smarkussnygis));
                }
                if (v17.equals("rukas")) {
                    pav2.setImageResource(R.drawable.rukas_rukas);
                    ee.setText(getString(R.string.Rūkas));
                }
                if (v17.equals("lijundra")) {
                    pav2.setImageResource(R.drawable.lijundra_lijundra);
                    ee.setText(getString(R.string.lijundra));
                }
                if (v17.equals("plikledis")) {
                    pav2.setImageResource(R.drawable.plikledis_plikledis);
                    ee.setText(getString(R.string.plikledis));
                }


                ff.setText(getString(R.string.meteoatnaujinta) + " " + v18.substring(0,10) + " ");

            }

        }


    }
}

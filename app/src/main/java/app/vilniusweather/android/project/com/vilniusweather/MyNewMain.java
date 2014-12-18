package app.vilniusweather.android.project.com.vilniusweather;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MyNewMain extends ActionBarActivity {

    private static final int NOTIFY_ME_ID=1337;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    private final String LOG_TAG = MyNewMain.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final NotificationManager mgr=
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Notification note=new Notification(R.drawable.logo,
                getString(R.string.app_name),
                System.currentTimeMillis());


        PendingIntent i = PendingIntent.getActivity(getBaseContext(), 0,
                new Intent(getBaseContext(), MyNewMain.class),
                0);

        note.setLatestEventInfo(getBaseContext(), getString(R.string.app_name),
                getString(R.string.notifikacija), i);

        //note.number=2;
        mgr.notify(NOTIFY_ME_ID, note);


        if (isOnline() == false){
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_new_main);



        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);




    }


    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){


            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.ispejimasinterneto));
            alertDialog.setMessage(getString(R.string.nerarysio));
          /*  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }*/
            alertDialog.setButton(getString(R.string.wireless), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    MyNewMain.this.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            });
            alertDialog.setIcon(R.drawable.logo);

            Toast.makeText(getApplicationContext(), getString(R.string.nerarysio), Toast.LENGTH_LONG).show();
            alertDialog.show();
            return false;
        }
        return true;
    }

    public void openNewActivity(View buttonExtendedd) {
// Do something in response to button
        Intent intent = new Intent(this, vudatapaaiskinimai.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_new_main, menu);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
//                | ActionBar.DISPLAY_SHOW_CUSTOM);
//        ImageView imageView = new ImageView(actionBar.getThemedContext());
//        imageView.setScaleType(ImageView.ScaleType.CENTER);
//        imageView.setImageResource(R.drawable.meteologo);
//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
//                | Gravity.CENTER_VERTICAL);
//        layoutParams.rightMargin = 40;
//        imageView.setLayoutParams(layoutParams);
//        actionBar.setCustomView(imageView);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }

        if (id == R.id.action_about){
            startActivity(new Intent(this, AboutActivity.class));
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefs.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));



        // Using the URI scheme for showing a location found on a map.  This super-handy
        // intent can is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps
       /* Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();*/
//        Uri geoLocation = Uri.parse("geo:0,0?q="+"M. "+"K. "+"Čiurlionio "+"gatvė "+"21"+","+"Vilniaus "+"Universitetas "+","+"Vilnius "+"03101");
        //  Uri geoLocation = Uri.parse("geo:0,0");

        Uri geoLocation = Uri.parse("geo:0,0?q=54.682886,25.260619");
        //Uri geoLocation = Uri.parse("geo:54.405839,25.153823");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);


        // jei success tada start activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new vudataFragment();
                case 1:
                    return new meteoFragment();
                case 2:
                    return new StotiesFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my_new_main, container, false);
            return rootView;
        }
    }

    @Override
    protected void onStart() {
        Log.v(LOG_TAG, "in onStart");
        super.onStart();
        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG, "in onResume");
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }

    @Override
    protected void onPause() {
        Log.v(LOG_TAG, "in onPause");
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        Log.v(LOG_TAG, "in onStop");
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "in onDestroy");
        super.onDestroy();
        // The activity is about to be destroyed.
    }

}

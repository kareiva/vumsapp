package app.vilniusweather.android.project.com.vilniusweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by Paulius on 2014-11-15.
 */
public class vudataActivity extends ActionBarActivity {
    private final String LOG_TAG = vudataActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "KVIECIA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vudata);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new vudataFragment())
                    .commit();
        }
    }



}

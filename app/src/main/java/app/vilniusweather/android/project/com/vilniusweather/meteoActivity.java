package app.vilniusweather.android.project.com.vilniusweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class meteoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new meteoFragment())
                    .commit();
        }

    }
}

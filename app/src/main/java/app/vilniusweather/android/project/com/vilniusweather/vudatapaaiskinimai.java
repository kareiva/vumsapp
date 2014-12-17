package app.vilniusweather.android.project.com.vilniusweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class vudatapaaiskinimai extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vudatapaaiskinimai);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new vudatapaaiskinimaiFragment())
                    .commit();
        }

    }
}

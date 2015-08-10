package com.example.irenachernyak.fragmentslayout;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by irenachernyak on 8/10/15.
 */
public class DetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if in landscape mode we just kill this activity
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        if(savedInstanceState == null){

            DetailsFragment df = new DetailsFragment();
            df.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, df).commit();
        }
    }
}

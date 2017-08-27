package com.luxola.sephora.happyshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.luxola.sephora.happyshop.R;
import com.luxola.sephora.happyshop.logger.CommonLogger;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Pradeep on 8/21/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
            .Builder(this)
			.build();
        ImageLoader.getInstance().init(config);

        CommonLogger.i(TAG, "onCreate");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startMenuActivity();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommonLogger.i(TAG, "onResume");
    }

    private void startMenuActivity() {
        Intent menuIntent = new Intent(this, MainMenuDrawerActivity.class);
        startActivity(menuIntent);
        finish();
    }

    private AppCompatActivity getActivity() {
        return this;
    }
}


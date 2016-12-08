package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.provider.MediaStore;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by jefhammond on 12/5/16.
 */


public class ApplyActivity extends Activity {
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    @Bind(R.id.btnCapture2) Button _loginButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        final Intent intent = new Intent(this, DecisionActivity.class);
        //final Intent intent = new Intent(this, TestActivity.class);
        Log.d("Apply", "jhammond testing apply");
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                login(intent);
            }
        });

        Button capture = (Button) findViewById(R.id.btnCapture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("CameraDemo", "jhammond 121212121212");
            }
        });
    }

    public void login(Intent intent) {
        Log.d("CameraDemo", "ttttttesssttt");
        Log.d("CameraDemo", "jhammond gggggggggggggggg");
        startActivity(intent);
    }

}
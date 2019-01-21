package com.example.willl.hockeydemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.hockeyapp.android.metrics.MetricsManager;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MetricsManager.trackEvent("second activity start");
        click();
    }

    public void click(){
        Button button = (Button) findViewById(R.id.second_btn);
        MetricsManager.trackEvent("second activity click");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MetricsManager.trackEvent("second activity error test");

                throw new IllegalArgumentException("crash test");

            }
        });
    }
}

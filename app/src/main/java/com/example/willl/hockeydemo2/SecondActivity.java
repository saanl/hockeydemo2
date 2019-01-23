package com.example.willl.hockeydemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.hockeyapp.android.metrics.MetricsManager;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MetricsManager.trackEvent("second activity start");
        click();
    }
    public void click2(View view){
        Toast.makeText(this,"click2",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        //设置广播的名字（设置Action）
        intent.setAction("net.hockeyapp.android.SCREENSHOT");
        //intent.putExtra("data","ss");
        //发送广播（无序广播）
        sendBroadcast(intent);
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

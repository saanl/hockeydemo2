package com.example.willl.hockeydemo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentById = fm.findFragmentById(R.id.my_fra_1);
    }
}

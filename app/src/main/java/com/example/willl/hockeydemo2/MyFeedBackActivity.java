package com.example.willl.hockeydemo2;


import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.UpdateFragment;

public class MyFeedBackActivity extends FeedbackActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feed_back);
        MyUpdateFragment m =new MyUpdateFragment();
        Log.e("*****************",m.getClass().getCanonicalName());
        UpdateFragment updateFragment = new UpdateFragment();
        Toast.makeText(this,m.getClass().getCanonicalName()+"\n"+updateFragment.getClass().getCanonicalName(),Toast.LENGTH_LONG).show();

        //  ActionBar actionBar = getActionBar();
      //  Log.e("Test action bar",actionBar.getTitle().toString());
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);

    }


}

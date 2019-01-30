package com.example.willl.hockeydemo2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hockeyapp.android.UpdateFragment;
import net.hockeyapp.android.utils.VersionHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyUpdateFragment extends UpdateFragment {

    public MyUpdateFragment() {
        // Required empty public constructor
        String canonicalName = this.getClass().getCanonicalName();
        String canonicalName1 = super.getClass().getCanonicalName();
        Log.e("***********son****************",canonicalName);
        Log.e("************father***************",canonicalName1);
    }

    public static MyUpdateFragment newInstance(String param1, String param2) {
        MyUpdateFragment fragment = new MyUpdateFragment();
        return fragment;
    }
    String url;
    String info;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        url = arguments.getString(FRAGMENT_URL);
        info = arguments.getString(FRAGMENT_VERSION_INFO);
//        Log.e("***********","url: "+url);
  //    Log.e("***********","info: "+info);
        VersionHelper versionHelper = new VersionHelper(getActivity(), info, this);
        Log.e("*******",versionHelper.getFileDateString());
        Log.e("*******",versionHelper.getReleaseNotes(true));
        Log.e("*******",versionHelper.getReleaseNotes(false));
        Log.e("*******",versionHelper.getVersionString());
        Log.e("*******",versionHelper.getFileSizeBytes()+"");
    }

    

    static public UpdateFragment newInstance(String versionInfo, String urlString, boolean dialog) {
        Bundle arguments = new Bundle();
        arguments.putString(FRAGMENT_URL, urlString);
        arguments.putString(FRAGMENT_VERSION_INFO, versionInfo);
        arguments.putBoolean(FRAGMENT_DIALOG, dialog);
        MyUpdateFragment fragment = new MyUpdateFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_my_update, container, false);
        TextView viewById = (TextView) view.findViewById(R.id.myfrag);


        viewById.setText(info);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

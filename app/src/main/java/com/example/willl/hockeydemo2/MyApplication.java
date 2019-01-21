package com.example.willl.hockeydemo2;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyLifeCallBack());
    }

    private class MyLifeCallBack implements  Application.ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.e("onActivityCreated",activity.getLocalClassName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.e("onActivityStarted",activity.getLocalClassName());
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.e("onActivityResumed",activity.getLocalClassName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.e("onActivityPaused",activity.getLocalClassName());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.e("onActivityStopped",activity.getLocalClassName());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.e("onActivitySaveInstanceState",activity.getLocalClassName());
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.e("onActivityDestroyed",activity.getLocalClassName());
        }
    }
}

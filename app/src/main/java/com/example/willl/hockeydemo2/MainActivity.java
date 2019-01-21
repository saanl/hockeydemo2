package com.example.willl.hockeydemo2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.push.Push;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.FeedbackManagerListener;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.LoginManagerListener;
import net.hockeyapp.android.UpdateActivity;
import net.hockeyapp.android.UpdateFragment;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.UpdateManagerListener;
import net.hockeyapp.android.metrics.MetricsManager;
import net.hockeyapp.android.objects.CrashManagerUserInput;
import net.hockeyapp.android.objects.CrashMetaData;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.utils.HockeyLog;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HockeyLog.setLogLevel(Log.VERBOSE);

        //getSupportActionBar().hide();

        appcenter();

        registerHockey();
        checkVersion();
        login();
        metricinit();
        feedbackinit();


    }




    public void appcenter(){
        AppCenter.start(getApplication(), "5b175709-3882-4cf8-a62e-a28f5b151b3f",
                Analytics.class, Crashes.class, Push.class);
    }


    public void metricinit(){
        MetricsManager.register(getApplication());
        MetricsManager.trackEvent("start");
    }

    public void feedbackinit(){
        FeedbackManager.register(this, new FeedbackManagerListener() {
//            @Override
//            public Class<? extends FeedbackActivity> getFeedbackActivityClass() {
//                return MyFeedBackActivity.class;
//            }

            @Override
            public boolean feedbackAnswered(FeedbackMessage latestMessage) {
                Log.e("FeedbackManager:getName",latestMessage.getName());
                Log.e("FeedbackManager:getAppId",latestMessage.getAppId());
                Log.e("FeedbackManager:getOem",latestMessage.getOem());
                Log.e("FeedbackManager:getText",latestMessage.getText());
                Log.e("FeedbackManager:getToken",latestMessage.getToken());
                return false;
            }
        });
        // FeedbackManager.setActivityForScreenshot(this);
    }

    public void click5(View view) throws IOException {
        FileInputStream fileInputStream = null;
        File dir = new File(getApplicationContext().getFilesDir(), "/net.hockeyapp.android/telemetry/");
        File[] files = dir.listFiles();

        if(files.length>0){
            Log.e("MyMetrics: "," 一共文件数量："+files.length );
            int num=0;
            for (File ff : files){
                Log.e("MyMetrics: ","第"+(++num)+"文件路径："+ ff.getAbsolutePath());
//                if(ff.isFile()){
//                    try{
//                        StringBuilder sb = new StringBuilder();
//                        fileInputStream = new FileInputStream(ff);
//                        int len =0;
//                        byte[] b = new byte[1024];
//                        while((len = fileInputStream.read(b))!=-1){
//                            sb.append(new String(b,0,len));
//                        }
//                        Log.e("******** Metrics/"+ff.getName(),sb.toString());
//                    }finally {
//                        fileInputStream.close();
//                    }

//                }
            }
        }
    }


    public void click4(View view){
        Log.e("*********","click4");
        MetricsManager.trackEvent("main activity click4");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void click3(View view){

        FeedbackManager.showFeedbackActivity(MainActivity.this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    public void login(){
        LoginManager.register(this, "8c8dcd1d3ce8ba342ad8faeb468209e5",
                LoginManager.LOGIN_MODE_EMAIL_PASSWORD,
                new LoginManagerListener(){
                    @Override
                    public void onSuccess() {
                        super.onSuccess();
                        Log.e("LoginManager","onSuccess");
                    }

                    @Override
                    public void onBack() {
                        super.onBack();
                        Log.e("LoginManager","onBack");
                    }
                });
        LoginManager.verifyLogin(this, getIntent());
    }

    public void debug(){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("net.hockeyapp.android.login", 0);
        int mode = prefs.getInt("mode", -1);
        String auid = prefs.getString("auid", "-1");
        String iuid = prefs.getString("iuid", "-1");
        //Toast.makeText(this, "mode:"+mode+" auid:"+auid+" iuid:"+iuid, Toast.LENGTH_SHORT).show();
        Log.e("debug","mode:"+mode+" auid:"+auid+" iuid:"+iuid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        debug();
    }

    public void click1(View view) throws PackageManager.NameNotFoundException {
        HashMap<String, String> m1 = new HashMap<>();
        HashMap<String, Double> m2 = new HashMap<>();
        m1.put("click1","property");
        m2.put("clicl1 mesurement",15.63);
        MetricsManager.trackEvent("click1");
        MetricsManager.trackEvent("click1 more",m1,m2);

        PackageInfo packageInfo = this.getApplicationContext()
                .getPackageManager()
                .getPackageInfo(this.getPackageName(), 0);
        int versionCode = packageInfo.versionCode;
        String versionName = packageInfo.versionName;
        Toast.makeText(this,"版本号为："+versionCode+" 版本名称："+versionName,Toast.LENGTH_SHORT).show();
    }

    public void click2(View view){
       JSONArray j = null;
        //throw new IllegalArgumentException("just test ");
        Object o = j.length();
        Log.e("",o.toString());
    }

    private static final FilenameFilter STACK_TRACES_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String filename) {
            return filename.endsWith(".stacktrace");
        }
    };
    public void registerHockey(){
        CrashManager.register(this, new CrashManagerListener() {
            @Override
            public String getDescription() {
                String description = "";
//                try {
//                    Process process = Runtime.getRuntime().exec("logcat -d HockeyApp:D *:S");
//                    BufferedReader bufferedReader =
//                            new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//                    StringBuilder log = new StringBuilder();
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        log.append(line);
//                        log.append(System.getProperty("line.separator"));
//                    }
//                    bufferedReader.close();
//                    description = log.toString();
//                }
//                catch (IOException e) {
//                }
                return "ListnerDesc";
            }

            @Override
            public String getUserID() {
                return "ListnerUser";
            }

            @Override
            public String getContact() {
                return "ListnerContact";
            }

            @Override
            public boolean shouldAutoUploadCrashes() {
                return false;
            }

            @Override
            public void onNewCrashesFound() {
                Log.e("onNewCrashesFound:","crash已发现");
                Toast.makeText(getApplicationContext(),"crash已发现",Toast.LENGTH_SHORT).show();
                File dir = getApplicationContext().getFilesDir();
                String absolutePath = getApplicationContext().getFilesDir().getAbsolutePath();
                Log.e("文件路径:",absolutePath);
                final File f = new File(absolutePath+"/1793f05a-373e-4704-a4b2-5c6c7611eea6.stacktrace");
                //final File f = new File(absolutePath+"/1793f05a-373e-4704-a4b2-5c6c7611eea6.user");
                if(f.isFile()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder sb = new StringBuilder();
                            try {
                                FileInputStream fi = new FileInputStream(f);
                                int len =0;
                                byte[] b = new byte[1024];
                                while((len = fi.read(b))!=-1){
                                    sb.append(new String(b,0,len));
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                Log.e("StackTrace:",sb.toString());
                            }

                        }
                    });
                }
                String[] list = dir.list(STACK_TRACES_FILTER);
                for (String s : list){
                    Log.e("HockeySDK记录的stacktrace_list: ","::"+s);
                }

            }

            @Override
            public void onCrashesNotSent() {
                super.onCrashesNotSent();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"crash未发送",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCrashesSent() {
                Log.e("onCrashesSent:","crash发送");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"crash发送",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onConfirmedCrashesFound() {
                super.onConfirmedCrashesFound();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(),"该发送crash已经就绪:",Toast.LENGTH_SHORT).show();

                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("HockeySDK", Context.MODE_PRIVATE);
                        List<String> list = Arrays.asList(preferences.getString("ConfirmedFilenames", "").split("\\|"));
                        Log.e("&*&*&*&*&*&*&*","list:"+list);

                    }
                });
            }

            @Override
            public boolean onHandleAlertView() {
                return false;
            }
        });


    }
    public void checkVersion(){
        UpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public boolean useUpdateDialog(Context context) {
                return true;
            }


//            @Override
//            public Class<? extends UpdateFragment> getUpdateFragmentClass() {
//                return MyUpdateFragment.class;
//            }

            @Override
            public Date getExpiryDate() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parse = null;
                try {
                    parse = sdf.parse("2019-02-22 14:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return parse;
            }

            @Override
            public boolean onBuildExpired() {
                return super.onBuildExpired();
            }

            @Override
            public void onNoUpdateAvailable() {
                super.onNoUpdateAvailable();
                Log.e("Update","onNoUpdateAvailable");
                Toast.makeText(getApplicationContext(),"onNoUpdateAvailable",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpdateAvailable() {
                super.onUpdateAvailable();
                Log.e("Update","onUpdateAvailable");
                Toast.makeText(getApplicationContext(),"onUpdateAvailable",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpdateAvailable(JSONArray data, String url) {
                super.onUpdateAvailable(data, url);
                Log.e("Update JSONArray ",data.toString());
                Log.e("Update url ",url);

            }
        });
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }
}

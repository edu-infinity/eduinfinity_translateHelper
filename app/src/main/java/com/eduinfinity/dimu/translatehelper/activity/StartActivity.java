package com.eduinfinity.dimu.translatehelper.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.eduinfinity.dimu.translatehelper.R;
import com.eduinfinity.dimu.translatehelper.adapter.Center;
import com.eduinfinity.dimu.translatehelper.adapter.model.Model;
import com.eduinfinity.dimu.translatehelper.adapter.model.Project;
import com.eduinfinity.dimu.translatehelper.http.TXRestClientUsage;
import com.eduinfinity.dimu.translatehelper.utils.FileUtils;
import com.eduinfinity.dimu.translatehelper.utils.JsonUtils;
import com.loopj.android.http.AsyncHttpClient;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

public class StartActivity extends Activity {
    final static String[] mottoes = {"勿忘初心，方得始终", "打破教育边界", "为了星灵"};
    private TextView tv;
    private Center center = Center.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        tv = (TextView) findViewById(R.id.textView_motto);
        //从assert中获取有资源，获得app的assert，采用getAserts()，通过给出在assert/下面的相对路径。在实际使用中，字体库可能存在于SD卡上，可以采用createFromFile()来替代createFromAsset。
//        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/wawasc.otf");
//        tv.setTypeface(face);
        int i = (int) Math.floor(Math.random() * mottoes.length);
        tv.setText(mottoes[i]);


    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
//            intent.setClass(StartActivity.this, ClassMenuActivity.class);
            intent.setClass(StartActivity.this, ClassMenuActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        List<Model> projectList = center.getProjectList();
        projectList = JsonUtils.parseProject("", FileUtils.ProjectConfig, projectList, this);


        AsyncHttpClient.allowRetryExceptionClass(IOException.class);
        AsyncHttpClient.allowRetryExceptionClass(SocketTimeoutException.class);
        AsyncHttpClient.allowRetryExceptionClass(ConnectTimeoutException.class);

        // The following exceptions will be blacklisted, i.e.: When an exception
        // of this type is raised, the request will not be retried and it will
        // fail immediately.
        AsyncHttpClient.blockRetryExceptionClass(UnknownHostException.class);
        AsyncHttpClient.blockRetryExceptionClass(ConnectionPoolTimeoutException.class);

        new Handler().postDelayed(r, 2000);// 2秒后关闭，并跳转到主页面
    }
}

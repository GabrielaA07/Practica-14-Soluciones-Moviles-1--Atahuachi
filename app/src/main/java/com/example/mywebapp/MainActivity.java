package com.example.mywebapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //private static String DIRECTION = "https://www.youtube.com/watch?v=djV11Xbc914";
    private static String DIRECTION =
            "http://192.168.0.5:8080/ejemplo/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(DIRECTION);
        simpleSnackbar(findViewById(R.id.coordinatorLayout));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView webView = (WebView) findViewById(R.id.webView);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public void simpleSnackbar (View view) {
        Snackbar mySnack = Snackbar.make(findViewById(R.id.coordinatorLayout), DIRECTION, Snackbar.LENGTH_SHORT);
        mySnack.show();
    }
}
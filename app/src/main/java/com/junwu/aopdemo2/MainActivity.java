package com.junwu.aopdemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.junwu.aoplibrary.async.Async;
import com.junwu.aoplibrary.method.MethodLog;
import com.junwu.aoplibrary.singleclick.SingleClick;
import com.junwu.aoplibrary.trace.Trace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.wvView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setLoadWithOverviewMode(true);

        webView.addJavascriptInterface(new MyClass(),"ss");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);
            }
        });
        webView.loadUrl("https://testingmobile.caihang.com/user/risk?loginKey=022368d0-a62e-488c-9bbc-310c57c4932b");
    }

    @SingleClick(value = 500)
    public void onclickListener(View view) {
        Toast.makeText(this, ToastDD(5), Toast.LENGTH_SHORT).show();
    }

    @Trace
    public String ToastDD(int count) {
        try {
            Thread.sleep(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "暂停" + count + "毫秒";
    }

    @Async
    public void onclickListener1(View view) {
        System.out.println("当前执行的线程：" + Thread.currentThread().getName());
    }

    @MethodLog
    public String onclickListener2(View view) {
        resultFun(10, "1");
        resultFun(10, "1", "123");
        return "返回的内容";
    }

    @MethodLog(params = false)
    public int resultFun(int i, String d) {
        return i;
    }

    @MethodLog(result = false)
    public void resultFun(int i, String d, String s) {

    }

    private class MyClass {
        @JavascriptInterface
        public void adddd(){}
    }

}

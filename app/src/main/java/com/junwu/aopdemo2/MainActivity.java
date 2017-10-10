package com.junwu.aopdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.junwu.aoplibrary.async.Async;
import com.junwu.aoplibrary.method.MethodLog;
import com.junwu.aoplibrary.trace.Trace;
import com.junwu.aoplibrary.singleclick.SingleClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}

package com.junwu.aoplibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * ===============================
 * 描    述：配置设置
 * 作    者：pjw
 * 创建日期：2017/10/9 16:49
 * ===============================
 */
public class App {

    private App() {
    }

    private Application application;
    public boolean d = true;

    private static class AppInstance {
        private static final App UTIL = new App();
    }

    public static App INSTANCE() {
        return AppInstance.UTIL;
    }

    /**
     * 获取
     */
    public Application getApplication() {
        return application;
    }

    /**
     * 设置
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    public Context getContext(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof android.app.Fragment) {
            android.app.Fragment fragment = (android.app.Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof Context) {
            return (Context) object;
        } else if (getApplication() != null) {
            return getApplication();
        }
        return null;
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public Context getContext() {
        return getApplication();
    }

    /**
     * 设置是否是调试模式
     */
    public void setDebug(boolean debug) {
        d = debug;
    }
}

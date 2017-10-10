package com.junwu.aoplibrary.utils;

import android.text.TextUtils;

/**
 * ===============================
 * 描    述：log工具类
 * 作    者：pjw
 * 创建日期：2017/9/30 10:47
 * ===============================
 */
public class D extends Exception {

    StackTraceElement[] trace;

    public D() {
        init();
    }

    public D(String message) {
        super(message);
        init();
    }

    public D(String message, Throwable cause) {
        super(message, cause);
        init();
    }

    public D(Throwable cause) {
        super(cause);
        init();
    }

    private void init() {
        trace = getStackTrace();
    }

    /**
     * 根据类名获取对应的下标
     *
     * @param className 类名或者路径
     * @param defIndex  如果没有获取到就返回默认值
     * @return 下标
     */
    public int getMethodIndex(String className, int defIndex) {
        if (trace == null || TextUtils.isEmpty(className))
            return defIndex; //没有获取到
        for (int i = 0, size = trace.length; i < size; i++) {
            if (trace[i].getClassName().contains(className)) {
                return i;
            }
        }
        return defIndex; //没有获取到
    }

    public String getMethod(int ser) {
        if (trace == null)
            return ""; //没有获取到
        if (ser >= trace.length) {
            ser = trace.length - 1;
        }
        return trace[ser].getMethodName();
    }

    public String funLog(int ser) {
        return funLog(ser, 1);
    }

    public String funLog(int ser, int min) {
        if (trace == null)
            return "";
        if (ser >= trace.length) {
            ser = trace.length - 1;
        }
        StringBuilder sb = new StringBuilder();
        StackTraceElement element;
        for (int i = ser; i >= min; i--) {
            sb.append("\n ");
            element = trace[i];
            sb.append(element.getClassName());
            sb.append(".");
            sb.append(element.getMethodName());
            sb.append("()");
            sb.append(":(");
            sb.append(element.getFileName());
            sb.append(":");
            sb.append(element.getLineNumber());
            sb.append(")");
        }
        return sb.toString();
    }

}

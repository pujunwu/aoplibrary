package com.junwu.aoplibrary.utils;

import android.util.Log;

import com.junwu.aoplibrary.App;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * ===============================
 * 描    述：工具类
 * 作    者：pjw
 * 创建日期：2017/10/9 17:01
 * ===============================
 */
public class U {

    /**********************************工具方法***********************************/
    /**
     * 毫秒转换为分 秒 毫秒
     *
     * @param ms 毫秒
     * @return 分 秒 毫秒
     */
    public static String timerMsToStr(long ms) {
        if (ms < 1000) {
            return ms + "ms";
        }
        if (ms < 1000 * 60) {
            long s = ms / 1000;
            long ms1 = ms - s * 1000;
            if (ms1 > 0) {
                return s + "s " + ms1 + "ms";
            }
            return ms / 1000 + "s";
        }
        long min = ms / 60000;
        if (min > 0) {
            long s = (ms - min * 60000) / 1000;
            long ms1 = ms - min * 60000 - s * 1000;
            if (s > 0 && ms1 > 0) {
                return min + "min " + s + "s " + ms1 + "ms";
            }
            if (s > 0 && ms1 == 0) {
                return min + "min " + s + "s";
            }
            if (s == 0 && ms1 > 0) {
                return min + "min " + ms1 + "ms";
            }
        }
        return min + "min";
    }

    /**
     * 获取触发Aspect监听的类
     *
     * @param joinPoint ProceedingJoinPoint
     * @param tag       默认值
     * @return 获取到类名返回类，否则返回默认值
     */
    public static String getJoinPointName(ProceedingJoinPoint joinPoint, String tag) {
        if (joinPoint == null) {
            return tag;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (methodSignature != null) {
            tag = methodSignature.getDeclaringType().getSimpleName();
        }
        return tag;
    }

    /**
     * 获取触发类名
     *
     * @param joinPoint ProceedingJoinPoint
     * @return 触发类名
     */
    public static String getJoinpointClassName(ProceedingJoinPoint joinPoint) {
        if (joinPoint == null) {
            return "";
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (methodSignature != null) {
            return methodSignature.getDeclaringTypeName();
        }
        return "";
    }


    /**********************************log方法***********************************/

    public static void logI(ProceedingJoinPoint joinPoint, String tag, String log) {
        logI(joinPoint, null, tag, log);
    }

    public static void logI(ProceedingJoinPoint joinPoint, D di, String tag, String log) {
        if (!App.INSTANCE().d) {
            return;
        }
        tag = getJoinPointName(joinPoint, tag);
        if (di == null) {
            di = new D();
        }
        //获取触发方法的下标
        int index = di.getMethodIndex(getJoinpointClassName(joinPoint), 2);
        Log.d(tag, di.getMethod(index) + "()" + log + di.funLog(index, index));
    }


    public static void main(String[] arg) {
        System.out.println(timerMsToStr(50));
        System.out.println(timerMsToStr(5000));
        System.out.println(timerMsToStr(1000 * 3 + 23));
        System.out.println(timerMsToStr(1000 * 60 + 1002));
        System.out.println(timerMsToStr(1000 * 60 + 6000));
        System.out.println(timerMsToStr(1000 * 60 + 2));
        System.out.println(timerMsToStr(5000 * 60));
    }

}

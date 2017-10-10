package com.junwu.aoplibrary.trace;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
class LogHugo {

    private static final String pag = "com.example.aoplib.trace.DeBugLogin";

    //带有DebugLog注解的所有类
    @Pointcut("within(@" + pag + " *)")
    public void withinDeBugLogin() {
    }

    //在带有DebugLog注解的所有类，除去synthetic修饰的方法
    @Pointcut("execution(!synthetic * *(..)) && withinDeBugLogin()")
    public void executionDeBugLogin() {
    }

    //在带有DebugLog注解的所有类，除去synthetic修饰的构造方法
    @Pointcut("execution(!synthetic *.new(..)) && withinDeBugLogin()")
    public void syntheticDeBugLogin() {
    }

    //在带有DebugLog注解的方法
    @Pointcut("execution(@" + pag + " * *(..)) || withinDeBugLogin()")
    public void methodDeBugLogin() {
    }

    //在带有DebugLog注解的构造方法
    @Pointcut("execution(@" + pag + " *.new(..)) || syntheticDeBugLogin()")
    public void constructorDeBugLogin() {
    }

    @Around("methodDeBugLogin() || constructorDeBugLogin()")
    public Object aopMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d("aopMethod", "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111：");
        long startT = System.currentTimeMillis();

        Object object = joinPoint.proceed();

        long stopT = System.currentTimeMillis();
        Log.d("aopMethod", "运行时间运行时间运行时间运行时间：" + (stopT - startT));
        return object;
    }


}

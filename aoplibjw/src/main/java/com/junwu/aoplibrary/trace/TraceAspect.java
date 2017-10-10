package com.junwu.aoplibrary.trace;


import com.junwu.aoplibrary.App;
import com.junwu.aoplibrary.utils.U;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 监听方法运行时间切入类
 */
@Aspect
public class TraceAspect {

    private static final String PAG = "com.junwu.aoplibrary.trace.Trace";

    //在带有DebugLog注解的方法
    @Pointcut("execution(@" + PAG + " * *(..))")
    public void methodAnnotatedWithTrace() {
    }

    //在带有DebugLog注解的构造方法
    @Pointcut("execution(@" + PAG + " *.new(..))")
    public void constructorAnnotatedTrace() {
    }

    @Around("methodAnnotatedWithTrace() || constructorAnnotatedTrace()")
    public Object aopLogMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (App.INSTANCE().d) {
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long stop = System.currentTimeMillis();
            //打印log
            U.logI(joinPoint, "Trace", "执行时长：" + U.timerMsToStr(stop - start));
            return result;
        }
        return joinPoint.proceed();
    }

}

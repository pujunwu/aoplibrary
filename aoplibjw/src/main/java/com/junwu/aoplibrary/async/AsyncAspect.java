package com.junwu.aoplibrary.async;

import com.junwu.aoplibrary.utils.D;
import com.junwu.aoplibrary.utils.U;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * ===============================
 * 描    述：异步执行方法切入类
 * 作    者：pjw
 * 创建日期：2017/10/10 9:48
 * ===============================
 */
@Aspect
public class AsyncAspect {

    //在带有Async注解的方法
    @Pointcut("@within(com.junwu.aoplibrary.async.Async)||@annotation(com.junwu.aoplibrary.async.Async)")
    public void executionSCA() {
    }

    @Around("execution(!synthetic * *(..)) && executionSCA()")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        final D di = new D();
        new Thread() {
            @Override
            public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                } finally {
                    U.logI(joinPoint, di, "Async", "运行在" + Thread.currentThread().getName() + "线程中");
                }
            }
        }.start();
    }

}

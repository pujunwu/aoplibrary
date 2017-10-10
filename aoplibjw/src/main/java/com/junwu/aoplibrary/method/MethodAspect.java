package com.junwu.aoplibrary.method;

import com.junwu.aoplibrary.App;
import com.junwu.aoplibrary.utils.U;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ===============================
 * 描    述：打印方法的参数和返回值切入类
 * 作    者：pjw
 * 创建日期：2017/10/10 11:03
 * ===============================
 */
@Aspect
public class MethodAspect {

    @Pointcut("@within(com.junwu.aoplibrary.method.MethodLog)||@annotation(com.junwu.aoplibrary.method.MethodLog)")
    public void executionSCA() {
    }

    @Around("execution(!synthetic * *(..)) && executionSCA()")
    public Object aroundJoinPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取方法上的MethodLog注解类
        MethodLog methodLog = method.getAnnotation(MethodLog.class);
        if (!App.INSTANCE().d || methodLog == null) {
            return joinPoint.proceed();
        }
        StringBuilder sb = new StringBuilder(":");
        if (methodLog.result()) {
            Object[] objects = joinPoint.getArgs();
            sb.append("params");
            int size = objects != null ? objects.length : 0;
            if (size > 0) {
                //参数
                sb.append(Arrays.deepToString(objects));
            }
        }
        //执行方法
        Object result = joinPoint.proceed();
        //返回值
        if (methodLog.params()) {
            if (methodLog.result()) {
                sb.append(",");
            }
            sb.append("result[");
            //返回值
            String type = signature.getReturnType().toString();
            sb.append("void".equalsIgnoreCase(type) ? "void" : result);
            sb.append("]");
        }
        U.logI(joinPoint, "Method", sb.toString());
        return result;
    }


}

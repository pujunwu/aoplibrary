package com.junwu.aoplibrary.method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ===============================
 * 描    述：打印方法参数和返回值
 * 作    者：pjw
 * 创建日期：2017/10/10 11:02
 * ===============================
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MethodLog {
    /**
     * 是否打印返回值
     */
    boolean result() default true;

    /**
     * 是否打印参数
     */
    boolean params() default true;
}

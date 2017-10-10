package com.junwu.aoplibrary.async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ===============================
 * 描    述：
 * 作    者：pjw
 * 创建日期：2017/10/10 9:49
 * ===============================
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Async {

}

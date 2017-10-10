package com.junwu.aoplibrary.singleclick;

import android.view.View;

import com.junwu.aoplibrary.R;
import com.junwu.aoplibrary.utils.U;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * 防止重复点击切入类
 */
@Aspect
public class SingleClickAspect {

    private static final int SINGLETAG = R.id.singleClickId;
    private static long oldTimer = 0;

    //    用SingleClick注解过的方法，所有返回值，所有虑参数，所有虑方法名都切入
    @Pointcut("execution(@com.junwu.aoplibrary.singleclick.SingleClick * *(..)) && @annotation(singleClick)")
    public void executionSCA(SingleClick singleClick) {
    }

    //    用在返回为void的方法上，包括private, public , static等修饰的方法
    @Around("executionSCA(singleClick)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final SingleClick singleClick) throws Throwable {
        if (singleClick == null) {
            joinPoint.proceed();
            return;
        }
        long single = singleClick.value();
        if (single <= 0) {
            joinPoint.proceed();
            return;
        }
        Object[] objects = joinPoint.getArgs();
        if (objects == null || objects.length <= 0) {
            joinPoint.proceed();
            return;
        }
        View view = null;
        for (Object object : objects) {
            if (object == null || !(object instanceof View)) {
                continue;
            }
            view = (View) object;
        }
        if (isExecutable(view, single)) {
            U.logI(joinPoint, "SingleClick", "正常执行");
            joinPoint.proceed();
        } else {
            U.logI(joinPoint, "SingleClick", "，两次点击间隔时长不足，拦截成功");
        }
    }

    /**
     * 判断是否执行proceed()
     *
     * @param view   点击控件
     * @param single 最小间隔时间
     * @return true可执行proceed()false不可以执行
     */
    private boolean isExecutable(View view, long single) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (view != null) {
            long tag = 0;
            try {
                tag = (long) view.getTag(SINGLETAG);
            } catch (NumberFormatException e) {
            } catch (NullPointerException e) {
            }
            if (tag >= 0 && currentTime - tag > single) {
                view.setTag(SINGLETAG, currentTime);
                return true;
            }
            return false;
        }
        if (currentTime - oldTimer > single) {
            oldTimer = currentTime;
            return true;
        }
        return false;
    }

}

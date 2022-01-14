package com.syy.study;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syy.study.utils.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LogAspect {
    private static final String TAG = "LogAspect_";
    private static Application mApplicationContext;


    @Pointcut("execution(* android.app.Application+.onCreate(..))")
    public void onApplicationCreate() {
    }

    @Pointcut("execution(* android.app.Activity+.onCreate(..))")
    public void onActivityCreate() {
        Log.d(TAG, "onActivityCreate: ");
    }

    @Around("onApplicationCreate()")
    public void weaveApplicationCreateJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getThis().getClass().getSimpleName();
        String methodName = methodSignature.getName();
        LogUtils.log(TAG + className, methodName);

        mApplicationContext = (Application) joinPoint.getThis();
        mApplicationContext.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                LogUtils.log(TAG + activity.getClass().getSimpleName(), "onActivityCreated");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                LogUtils.log(TAG + activity.getClass().getSimpleName(), "onActivityStarted");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                LogUtils.log(TAG + activity.getClass().getSimpleName(), "onActivityResumed");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                LogUtils.log(TAG + activity.getClass().getSimpleName(), "onActivityPaused");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LogUtils.log(TAG + activity.getClass().getSimpleName(), "onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                LogUtils.log(TAG + activity.getClass().getSimpleName(), "onActivityDestroyed");
            }
        });
    }

    @Around("onActivityCreate()")
    public void weaveActivityOnCreateJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
    }
    
}

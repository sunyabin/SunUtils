
package com.sunyabin.sunapputils;

import android.app.Activity;
import java.util.Stack;

/**
*ActivityStack
*created at 2017-06-27 10:44 by SUN
 * 管理Activities任务栈 , 方便管理所有Activity
 * 建议将addActivity()  与  finishActivity() 添加到项目的BaseActivity中
*/

public class ActivityStack {
    private static Stack<Activity> activityStack;
    private static ActivityStack instance;

    private ActivityStack() {

    }

    /**
     * 获取栈
     * @return ActivityStack
     */
    public static ActivityStack getAppManager() {
        if (instance == null) {
            instance = new ActivityStack();
        }
        return instance;
    }

    /**
     * 添加Activity 到栈
     * ---------建议写到BaseActivity的onCreate() 中---------
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前的Activity（堆栈中最后一个压入的)
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
    }

    /**
     *
     * 结束指定的Activity
     *---------建议写到BaseActivity的onDestroy() 中---------
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity();
            }
        }
    }

    /**
     * 结束所有的Activity、
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (activityStack.get(i) != null) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出所有Activity
     */
    public void AppExit() {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {

        }
    }
}

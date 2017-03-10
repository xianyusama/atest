package com.test;

import android.app.Activity;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiao on 2017/1/16.
 */

public class ActivityManager {
    private final static String TAG = "MTW";
    /**
     * 运用list来保存们每一个activity是关键
     */
    private List<Activity> mList = new LinkedList<Activity>();
    /**
     * 为了实现每次使用该类时不创建新的对象而创建的静态对象
     */
    private static ActivityManager activityManager;


    /**
     * 单例模式(懒汉式)
     */
    private ActivityManager() {
    }

    /**
     * 单例模式(懒汉式)
     */
    public synchronized static ActivityManager getInstance() {
        if (activityManager == null) {
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    /**
     * @param activity 添加Activity
     */
    public void addActivity(Activity activity) {
        Log.i(TAG, "添加 " + activity.getLocalClassName());
        mList.add(activity);
        out();
    }

    /**
     * @param activity 移除Activity
     */
    public void removeActivity(Activity activity) {
        Log.i(TAG, "移除 " + activity.getLocalClassName());
        if (mList.contains(activity)) {
            mList.remove(activity);
        }
        out();
    }

    /**
     * 关闭每一个list内的activity
     */

    public void exit() {
        out();
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            Log.i(TAG, "系统已退出。");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    public void out() {
        Log.i(TAG, "开始输出ActivityList");
        for (Activity activity : mList) {
            Log.i(TAG, activity.getLocalClassName());
        }
        Log.i(TAG, "mList的size = " + mList.size());
        Log.i(TAG, "结束输出ActivityList");
    }
}

package com.test.exceptiondemo;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.test.ActivityManager;

/**
 * Created by xiao on 2017/2/18.
 */

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类
    private static CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
    private Context mContext;

    private CustomExceptionHandler() {}

    public static CustomExceptionHandler getInstance() {
        return customExceptionHandler;
    }
    public void init(Context context){
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器

    }
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            // 退出程序
            try {
                Thread.sleep(2000);// 如果处理了，让程序继续运行3秒再退出，保证文件保存并上传到服务器
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            UUApp.getInstance().exitCrash();
            ActivityManager.getInstance().exit();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param throwable 异常信息
     * @return true 如果处理了该异常信息;否则返回false.
     */
    public boolean handleException(Throwable throwable) {
        if (throwable == null)
            return true;
//        L.e("error:" + ex.getMessage());
        new LogExceptionThread(mContext,throwable).start();

        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        return true;
    }
}

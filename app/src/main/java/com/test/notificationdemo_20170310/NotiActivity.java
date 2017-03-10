package com.test.notificationdemo_20170310;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.test.R;

/**
 * Created by xiao on 2017/3/10.
 */

public class NotiActivity extends Activity implements View.OnClickListener {
    //定义notification实用的ID
    private static final int NO_3 = 0x3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_01);

    }

    @Override
    public void onClick(View v) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("下载");
        builder.setContentText("正在下载");
        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NO_3, builder.build());
        builder.setProgress(100, 0, false);
        //下载以及安装线程模拟
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    builder.setProgress(100, i, false);
                    manager.notify(NO_3, builder.build());
                    //下载进度提示
                    builder.setContentText("下载" + i + "%");
                    try {
                        Thread.sleep(50);//演示休眠50毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //下载完成后更改标题以及提示信息
                builder.setContentTitle("开始安装");
                builder.setContentText("安装中...");
                //设置进度为不确定，用于模拟安装
                builder.setProgress(0, 0, true);
                manager.notify(NO_3, builder.build());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                manager.cancel(NO_3);//设置关闭通知栏
            }
        }).start();
    }
    
}

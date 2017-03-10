package com.test.exceptiondemo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xiao on 2017/2/18.
 * 将异常发送到指定位置
 */

public class LogExceptionThread extends Thread {
    private Context mContext;
    private Throwable throwable;

    public LogExceptionThread(Context context,Throwable throwable) {
        this.mContext=context;
        this.throwable = throwable;
    }

    @Override
    public void run() {
        super.run();
        String message = throwable.getMessage();
        String localizedMessage = throwable.getLocalizedMessage();
        String dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        Log.d("Error",dcim);
        FileWriter fw = null;
        File fileDir = new File(dcim+File.separator+"error");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File f = new File(dcim+File.separator+"error" + File.separator + System.currentTimeMillis()+".txt");
        Log.d("Error",f.getPath());
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(localizedMessage, 0, localizedMessage.length() - 1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");

// TODO: 2017/3/10  将异常的log发送到服务器
        Log.e("Error",throwable.toString());
        Log.e("Error",localizedMessage);
    }
}

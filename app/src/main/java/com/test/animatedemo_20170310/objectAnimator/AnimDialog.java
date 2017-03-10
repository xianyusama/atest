package com.test.animatedemo_20170310.objectAnimator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

import com.test.R;

/**
 * Created by xiao on 2017/3/10.
 */

public class AnimDialog extends AlertDialog {
    protected AnimDialog(@NonNull Context context) {
        super(context);
    }

    protected AnimDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected AnimDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_anim_02);
    }
}

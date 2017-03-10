package com.test.animatedemo_20170310.objectAnimator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.test.R;

/**
 * Created by xiao on 2017/3/10.
 */

public class AnimActivity02 extends Activity implements View.OnClickListener {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_02);
        Button btnShow = (Button) findViewById(R.id.btn_show_dialog);
        iv = (ImageView) findViewById(R.id.iv_anim);
        btnShow.setOnClickListener(this);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.disappear_bottom_right_out);
        iv.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

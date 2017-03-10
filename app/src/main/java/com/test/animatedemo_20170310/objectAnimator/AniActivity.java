package com.test.animatedemo_20170310.objectAnimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.R;

/**
 * Created by xiao on 2017/3/10.
 */

public class AniActivity extends Activity implements View.OnClickListener {
    private ImageView ivAnim;
    private Button btnTouming;
    private Button btnTouming2;
    private Button btnRotation;
    private Button btnMove;
    private Button btnScale;
    private Button btnXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_obj);
        ivAnim = (ImageView) findViewById(R.id.iv_anim);
        btnTouming = (Button) findViewById(R.id.btn_touming);
        btnTouming2 = (Button) findViewById(R.id.btn_touming2);
        btnRotation = (Button) findViewById(R.id.btn_rotation);
        btnMove = (Button) findViewById(R.id.btn_move);
        btnScale = (Button) findViewById(R.id.btn_scale);
        btnXml = (Button) findViewById(R.id.btn_xml);
        btnTouming.setOnClickListener(this);
        btnTouming2.setOnClickListener(this);
        btnRotation.setOnClickListener(this);
        btnMove.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnXml.setOnClickListener(this);

    }

    private void touming() {
        //        ofFloat中的参数：
//        imageView：执行动画的View;
//        "alpha"：表示透明动画；
//        1f：起始透明度；
//        0f：动画结束后的透明度；
//        还可以省略1f，写成下面这样
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivAnim, "alpha", 1f, 0f);
        animator.setDuration(1000);//时间1s
        animator.start();
    }

    private void touming2() {
        //        ofFloat中的参数：
//        imageView：执行动画的View;
//        "alpha"：表示透明动画；
//        1f：起始透明度；
//        0f：动画结束后的透明度；
//        还可以省略1f，写成下面这样
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivAnim, "alpha", 1f, 0f, 1f);
        animator.setDuration(3000);//时间1s
        animator.setRepeatCount(-1);//表示一直重复
        animator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_touming:
                touming();
                break;
            case R.id.btn_touming2:
                touming2();
                break;
            case R.id.btn_rotation:
                rototion();
                break;
            case R.id.btn_move:
                move();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_xml:
                xml();
                break;
            default:
                break;
        }
    }

    private void xml() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.alpha);
        animator.setTarget(ivAnim);
        animator.start();
    }

    private void scale() {
//        缩放和移动相似，也分为沿x、y轴来放缩。沿x轴缩放使用scaleX，沿y轴缩放使用scaleY。
//        注：
//        后面的参数表示倍数，1f表示原来的大小，以此推类：2f表示两倍、3f表示三倍
//        如：1f-> 2f，放大成原来的两倍；2f-> 1f，从两倍变为原样。
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivAnim, "scaleY", 1f, 2f, 1f);
        animator.setDuration(2000);
        animator.start();

    }

    private void move() {
        //这里的移动分为沿x、y轴移动，沿x轴时使用translationX，沿y轴移动使用translationY。
//        注：
//        translationX：下个位置大于上个上个位置时，向右移动，反之向左移动；
//        translationY：下个位置大于上个上个位置时，向下移动，反之向上移动。
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivAnim, "translationX", 0f, -300f);
        animator.setDuration(2000);
        animator.start();
    }

    private void rototion() {
        //注：
//        下个度数大于上个度数，顺时针旋转；下个度数小于上个度数，逆时针旋转。
//        如：0f -> 360f ，顺时针； 360f -> 0f，逆时针。
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivAnim, "rotation", 0f, 360f);
        AnimatorSet set = new AnimatorSet();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                Toast.makeText(AniActivity.this, "onAnimationCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(AniActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                Toast.makeText(AniActivity.this, "onAnimationRepeat", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Toast.makeText(AniActivity.this, "onAnimationStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
        set.play(animator);
        set.setDuration(2000);//两秒转一次
        animator.setRepeatCount(-1);//一直重复
        set.start();
    }
}

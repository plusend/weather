package com.plusend.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.plusend.weather.activity.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private ImageView ivWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        tvWelcome = (TextView) findViewById(R.id.tv_welcome);
        ivWelcome = (ImageView) findViewById(R.id.iv_welcome);
        initImage();

    }

    private void initImage() {
        ivWelcome.setImageResource(R.mipmap.welcome);
        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivWelcome.startAnimation(scaleAnim);
    }

    private void startMainActivity() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        WelcomeActivity.this.finish();
    }


}

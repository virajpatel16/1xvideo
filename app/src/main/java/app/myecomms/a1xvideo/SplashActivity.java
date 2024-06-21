package app.myecomms.a1xvideo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {

        handler = new Handler();
        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.playAnimation();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
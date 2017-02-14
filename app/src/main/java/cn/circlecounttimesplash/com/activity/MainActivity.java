package cn.circlecounttimesplash.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import cn.circlecounttimesplash.com.R;
import cn.circlecounttimesplash.com.view.CountTimeProgressView;

public class MainActivity extends AppCompatActivity {

    private CountTimeProgressView countTimeProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        countTimeProgressView = (CountTimeProgressView) findViewById(R.id.countTimeProgressView);
        countTimeProgressView.setTextStyle(CountTimeProgressView.TextStyle.SECOND);
        countTimeProgressView.setCountTime(5000);
        countTimeProgressView.setMarkBallFlag(true);
        countTimeProgressView.setMarkBallColor(getResources().getColor(R.color.colorPrimary));
        countTimeProgressView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        countTimeProgressView.setBorderBottomColor(getResources().getColor(R.color.colorGrey));
        countTimeProgressView.setBorderDrawColor(getResources().getColor(R.color.colorPrimary));
        countTimeProgressView.setBackgroundColor(Color.TRANSPARENT);
        countTimeProgressView.setTitleCenter("%s s");
        countTimeProgressView.startCountTimeAnimation();
        countTimeProgressView.addOnEndListener(new CountTimeProgressView.OnEndListener() {
            @Override
            public void onAnimationEnd() {

                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                finish();
            }
            //            gradlew bintrayUpload

            @Override
            public void onClick(long overageTime) {
                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                finish();
            }

        });
        countTimeProgressView.startCountTimeAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimeProgressView != null && countTimeProgressView.isRunning()) {
            countTimeProgressView.cancelCountTimeAnimation();
        }
    }
}

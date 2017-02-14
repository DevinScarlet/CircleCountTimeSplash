package cn.circlecounttimesplash.com.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.circlecounttimesplash.com.R;
import cn.circlecounttimesplash.com.view.CountTimeProgressView;


/**
 * Author :leilei on 2016/12/20 0115.
 */
public class SimpleActivity extends AppCompatActivity {

    private CountTimeProgressView countTimeProgressView;
    private int checkedItem = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        countTimeProgressView = (CountTimeProgressView) findViewById(R.id.countTimeProgressView);
        findViewById(R.id.tv_countTime_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(SimpleActivity.this).setTitle("CountTime Style").setSingleChoiceItems(
                        new String[]{"JUMP", "SECOND", "CLOCK", "NONE"}, checkedItem,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                checkedItem = which;
                                switch (which) {
                                    case 0:
                                        countTimeProgressView.setTextStyle(CountTimeProgressView.TextStyle.JUMP);
                                        countTimeProgressView.setTitleCenter("跳过");
                                        countTimeProgressView.startCountTimeAnimation();
                                        dialog.dismiss();
                                        break;
                                    case 1:
                                        countTimeProgressView.setTextStyle(CountTimeProgressView.TextStyle.SECOND);
                                        countTimeProgressView.setTitleCenter("跳过（%s）s");
                                        countTimeProgressView.startCountTimeAnimation();
                                        dialog.dismiss();
                                        break;
                                    case 2:
                                        countTimeProgressView.setTextStyle(CountTimeProgressView.TextStyle.CLOCK);
                                        countTimeProgressView.startCountTimeAnimation();
                                        dialog.dismiss();
                                        break;
                                    case 3:
                                        countTimeProgressView.setTextStyle(CountTimeProgressView.TextStyle.NONE);
                                        countTimeProgressView.startCountTimeAnimation();
                                        dialog.dismiss();
                                        break;
                                    default:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        }).show();
            }
        });
        countTimeProgressView.setStartAngle(0);
        countTimeProgressView.setCountTime(6000);
        countTimeProgressView.setTextStyle(CountTimeProgressView.TextStyle.SECOND);
        countTimeProgressView.setBorderWidth(8);
        countTimeProgressView.setBorderBottomColor(Color.GRAY);
        countTimeProgressView.setBorderDrawColor(Color.RED);
        countTimeProgressView.setBackgroundColor(Color.WHITE);
        countTimeProgressView.setMarkBallFlag(true);
        countTimeProgressView.setMarkBallWidth(12);
        countTimeProgressView.setMarkBallColor(Color.GREEN);
        countTimeProgressView.setTitleCenter("跳过（%s）s");

        countTimeProgressView.addOnEndListener(new CountTimeProgressView.OnEndListener() {
            @Override
            public void onAnimationEnd() {
                Toast.makeText(SimpleActivity.this, "时间到", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(long overageTime) {
                if (countTimeProgressView.isRunning()) {
                    countTimeProgressView.cancelCountTimeAnimation();
                    Toast.makeText(SimpleActivity.this, "时间到" + overageTime, Toast.LENGTH_SHORT).show();
                    Log.e("overageTime", "overageTime = " + overageTime);
                } else {
                    countTimeProgressView.startCountTimeAnimation();
                }
            }
        });
        //countTimeProgressView.startCountTimeAnimation();
    }
}
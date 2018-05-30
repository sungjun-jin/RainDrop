package com.example.jinsungjun.raindrop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Switch onOff;
    Stage stage;

    float deviceWidth = 0;
    float deviceHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);
        onOff = findViewById(R.id.onOff);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceHeight = metrics.heightPixels;
        deviceWidth = metrics.widthPixels;

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    start();
                } else {
                    stop();
                }
            }
        });
        stage = new Stage(this);
        frameLayout.addView(stage);
    }


    private void stop() {
        //RainDrop의 Thread를 잠깐 멈춰준다
        stage.pause();
    }

    public void runStage() {

        new Thread(stage).start(); //스테이지는 초당 한번씩 onDraw를 호출하고 있는 중

    }

    //비오는거 시작
    public void start() {

        runStage();

        new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    //빗방울 생성
                    RainDrop rainDrop = new RainDrop(deviceWidth,deviceHeight);
                    //스테이지에 빗방울 담기
                    stage.addRainDrop(rainDrop);
                    //빗방울 움직이기
                    rainDrop.start();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}

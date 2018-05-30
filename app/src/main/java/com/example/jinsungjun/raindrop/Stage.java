package com.example.jinsungjun.raindrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Stage extends View implements Runnable {

    //CustomView
    Paint paint;
    List<RainDrop> rainDrops;
    public static boolean pause = false;


    public Stage(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLUE);

        rainDrops = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //현재 빗방울이 있으면 그려준다

        for (int i = 0; i < rainDrops.size(); i++) {
            //List의 빗방울들을 하나씩 담아서
            RainDrop drops = rainDrops.get(i);
            //레이아웃에 그려준다
            canvas.drawCircle(drops.x, drops.y, drops.radius, paint);
        }
    }

    public void addRainDrop(RainDrop rainDrop) {
        //List에 빗방울들을 추가시켜준다
        rainDrops.add(rainDrop);
    }

    @Override
    public void run() {

        while (true) {
            //0.01초동안
            sleep(10);
            //Sub Thread에서 레이아웃을 Invalidate시킬때 사용하는 함수
            postInvalidate();
        }
    }

    public void sleep(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        //메인 액티비티에서 stop 스위치를 동작시켰을때 boolean pause를 = true로 바꿔준다.
        pause = true;
    }
}

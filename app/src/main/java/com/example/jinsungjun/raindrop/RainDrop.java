package com.example.jinsungjun.raindrop;

import java.util.Random;

public class RainDrop extends Thread {

    float radius; //반지름
    float x, y; //x,y좌표
    float speed = 0; //빗방울이 떨어지는 속도
    float heightPixel;


    public RainDrop(float widthPixel,float heightPixel) {
        //랜덤 클래스
        Random random = new Random();

        y = -radius - 40;
        x = random.nextInt((int) widthPixel); //0 ~ 화면의 가로 사이즈 사이
        radius = random.nextInt(35) + 5; // 0   10 ~ 70사이의 임의 반지름을 가진다
        speed = random.nextInt(10) + 5; //0.1초당 y축으로 움직이는거리 (5 ~ 15픽셀)
        this.heightPixel = heightPixel;

    }

    public void run() {

        while (y < heightPixel) {
            // 0.01초에 한번씩 임의로 속도만큼 빗방울이 떨어진다
            if(!Stage.pause)
            y = y + speed;
            sleep(10);
        }
    }

    public void sleep(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.example.ronal.gradiusproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;


public class Player
{
    public static Player instance;

    public float x, y, width, height, speedX, speedY;
    private boolean isMoving, isMovingUp;
    static Context c;
    Bitmap bm;
    private Player(Context ctx)
    {
        c = ctx;

        width = MainGameView.screenW * 0.05f;
        height = MainGameView.screenH * 0.2f;

        x = 2 * width;
        y = (MainGameView.screenH / 2) - (height/2);
        speedX = 7;
        speedY = 7;
        isMoving = isMovingUp = false;

        bm = BitmapFactory.decodeResource(c.getResources(), R.mipmap.SpaceshipImage);
        bm = Bitmap.createScaledBitmap(bm, (int) width, (int) height, false);
    }

    public static Player getInstance(Context ctx)
    {
        if(instance == null) instance = new Player(ctx);
        return instance;
    }

    public void Draw()
    {

    }

    public void preUpdate(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
        {
            isMoving = true;
            isMovingUp = y > event.getRawY();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) isMoving = false;
    }
}

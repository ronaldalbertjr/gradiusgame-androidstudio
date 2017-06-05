package com.example.ronal.gradiusproject;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class MainGameView extends View implements Runnable
{
    public static int screenW, screenH;
    public static boolean isDead, isPaused, isUpdating;

    private Context c;
    private Handler handler;

    private Player player;
    private List<Bullet> playerBullets;
    private List<Bullet> enemyBullets;
    private Enemy enemy;
    private float timer;


    public MainGameView(Context ctx)
    {
        super(ctx);
        Start(ctx);
    }
    protected void Start(Context ctx)
    {
        c = ctx;
        setBackgroundColor(Color.GRAY);

        isDead = isPaused = false;
        isUpdating = true;

        playerBullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<Bullet>();
        player = Player.getInstance(ctx);
        enemy = Enemy.getInstance(ctx);
        handler = new Handler();
        handler.post(this);
    }

    public boolean onTouchEvent(MotionEvent e)
    {
        if(!isDead && !isPaused) player.preUpdate(e);
        else if(isDead) RestartGame();
        else if(isPaused) isPaused = false;
        return true;
    }

    protected void onDraw(Canvas canvas)
    {

    }

    protected void Update()
    {
        if(!isDead && !isPaused)
        {
            timer += 0.03f;
            if(timer >= 3)
            {

            }
            enemy.update();
        }

    }

    private void RestartGame()
    {
        isDead = false;
    }
    public void run()
    {
        if(isUpdating)
        {
            handler.postDelayed(this, 30);

            Update();
            invalidate();
        }
    }
}

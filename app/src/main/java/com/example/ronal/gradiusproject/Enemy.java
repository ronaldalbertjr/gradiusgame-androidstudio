package com.example.ronal.gradiusproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Enemy
{
    float x, y, height, width, speedX, speedY;
    static Enemy instance;
    EnemyTypes type;
    Player player;
    Bitmap bm;
    Context c;

    private Enemy(Context ctx)
    {
        c = ctx;
        type = EnemyTypes.FollowEnemy;
        player = Player.getInstance(ctx);

        x = MainGameView.screenW - (2 * width);
        y = (MainGameView.screenW/2) - (height/2);
        height = MainGameView.screenH * 0.05f;
        width = MainGameView.screenW * 0.2f;
        speedX = 7;
        speedY = 7;

        bm = BitmapFactory.decodeResource(c.getResources(), R.mipmap.FollowEnemy);
        bm = Bitmap.createScaledBitmap(bm, (int) width, (int) height, false);
    }

    public static Enemy getInstance(Context ctx)
    {
        if(instance == null) instance = new Enemy(ctx);
        return instance;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bm, x, y, new Paint());
    }

    public void update()
    {
        if(type == EnemyTypes.FollowEnemy)
        {
            y = player.y;
            x += speedX;
        }
        else if(type == EnemyTypes.StraightLineEnemy)
        {
            x += speedX;
        }
        else if(type == EnemyTypes.RicocheteEnemy)
        {
            x += speedX;
            y += speedY;
        }
        CollisionWithScreen();
    }

    public void RestartEnemy(EnemyTypes enemyTypes)
    {
        type = enemyTypes;
        switch(type)
        {
            case FollowEnemy:
                bm = BitmapFactory.decodeResource(c.getResources(), R.mipmap.FollowEnemy);
                break;
            case RicocheteEnemy:
                bm = BitmapFactory.decodeResource(c.getResources(), R.mipmap.RicocheteEnemy);
                break;
            case StraightLineEnemy:
                bm = BitmapFactory.decodeResource(c.getResources(), R.mipmap.StraightLineEnemy);
                break;
        }
        bm = Bitmap.createScaledBitmap(bm, (int) width, (int) height, false);
        x = MainGameView.screenW - (2 * width);
    }

    private void CollisionWithScreen()
    {
        EnemyTypes[] enemyTypes = new EnemyTypes[] {EnemyTypes.FollowEnemy, EnemyTypes.RicocheteEnemy, EnemyTypes.RicocheteEnemy};
        if(x < 0)
        {
            RestartEnemy(enemyTypes[new Random().nextInt(3)]);
        }
        if(y < 0)
        {
            speedY = 7;
        }
        else if(y + height > MainGameView.screenH)
        {
            speedY = -7;
        }
    }

}

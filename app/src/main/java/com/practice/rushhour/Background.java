package com.practice.rushhour;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Syed on 2015-12-19.
 */
public class Background {

    Bitmap bgBitmap;
    int x,y,dy; //used to be int
    int screenHeight;
    int countBackground;
    GamePanel root_gamePanel;

    public Background(Bitmap bmp, int screen_Height, GamePanel game_Panel){
        this.bgBitmap = bmp;
        this.x=0;
        this.y=0;
        this.dy=-20;
        this.screenHeight = screen_Height;

        //ratio to find how many images can fit on a screen
        this.countBackground=(screenHeight/bgBitmap.getHeight())+1;

        root_gamePanel = game_Panel;
    }

    public void draw(Canvas canvas){

       /* for(int i = 0; i < countBackground +1; i++){
            if(canvas != null){
                canvas.drawBitmap(bgBitmap, x, y, null);
            }
        }

        if(Math.abs(x)>bgBitmap.getWidth()){

            //reset x to appropriate location
            x = x - bgBitmap.getWidth();
        }*/

        if(y <= 0)
        {
            canvas.drawBitmap(bgBitmap, x, y + (root_gamePanel.getHeight())/2, null);
        }
    }

    public void update(float delta_Time){
       /* dx = (int)(root_gamePanel.playerSpeed*delta_Time);
        x = x - dx;*/

        y+=dy;

        //reset image position
        if(y < -(root_gamePanel.getHeight()))
        {
            y+=root_gamePanel.getHeight();
        }

    }
}

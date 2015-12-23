package com.practice.rushhour;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Syed on 2015-12-19.
 */
public class MainThread extends Thread {

    private SurfaceHolder _surfaceHolder;
    private  GamePanel _gamePanel;
    private boolean _running;
    float deltaTime;

    public MainThread(SurfaceHolder holder, GamePanel gamePanel) {
        this._surfaceHolder = holder;
        this._gamePanel = gamePanel;
        deltaTime = 0f;
    }

    void setRunning(boolean running){
        this._running = running;
    }

    @Override
    public void run() {
        Canvas canvas;

        //infinite loop
        while (_running){

            if(!(_gamePanel.Pause_game)){

                long StartDraw = System.currentTimeMillis();

                canvas = null;
                    try{
                        canvas=this._surfaceHolder.lockCanvas();
                        synchronized (_surfaceHolder){
                            _gamePanel.Update(deltaTime);
                            _gamePanel.Draw(canvas);

                        }
                    }finally {
                        if(canvas != null)
                        {
                            _surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }



                long EndDraw = System.currentTimeMillis();
                deltaTime = (float) EndDraw - StartDraw;
            }

        }
    }
}

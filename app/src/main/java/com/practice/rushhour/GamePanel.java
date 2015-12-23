package com.practice.rushhour;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Syed on 2015-12-19.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public MainThread thread;
    public boolean Pause_game;
    public Background background;
    public float playerSpeed;


    public GamePanel(Context applicationContext, Context context, int screenWidth, int screenHeight) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);


        //Scale and initialize appropriate background
        background = new Background(scaleBackground(screenWidth, screenHeight), screenHeight, this);
        playerSpeed = (float) screenHeight/2;
    }

/* New Function for scaling background
 * source: http://stackoverflow.com/questions/17657605/how-to-scale-background-image-on-canvas-according-to-device-size-in-android
*/
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public Bitmap scaleBackground(int screenWidth, int screenHeight){

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.platform, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, screenWidth, screenHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), R.drawable.platform, options);
    }
/* End of new background scaling */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void Draw(Canvas canvas){
        if(!Pause_game)
        {
            if(canvas!=null)
            {
                background.draw(canvas);
            }
        }
    }

    public void Update(float deltaTime){
        background.update(deltaTime);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while(retry){

                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }


}

package com.practice.rushhour;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Syed on 2015-12-13.
 */
public class Game extends Activity {

    View PauseButton;
    View PauseMenu;
    GamePanel gamePanel;

    //RelativeLayout rel_main_game;
    FrameLayout gameScreen;

    //Set Listeners to handle pause menu button events

    View.OnClickListener clickContinue_lstnr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onResume();

        }
    };

    View.OnClickListener clickMainMenu_lstnr = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //End game and go to Main Menu
            Game.this.finish();

        }

    };

    View.OnClickListener clickPause_lstnr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            onPause();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameScreen = new FrameLayout(this); //(FrameLayout)findViewById(R.id.main_game_rl);
        //rel_main_game = (LinearLayout)findViewById(R.id.main_game_ll);


        setContentView(gameScreen);


        //rel_main_game = (RelativeLayout) findViewById(R.id.main_game_rl);


        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //Screen Size
        final int widthSize = metrics.widthPixels;
        final int heightSize = metrics.heightPixels;

       /* Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int widthSize = size.x;
        final int heightSize = size.y; */

        gamePanel = new GamePanel(getApplicationContext(), this, widthSize - 100, heightSize);
        gamePanel.Pause_game=true;

        //layoutinflater to refer to View in XML
        LayoutInflater layoutinflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        PauseButton = layoutinflater.inflate(R.layout.pause, null, false);
        PauseMenu = layoutinflater.inflate(R.layout.pause_menu, null, false);

        //initialize pausebutton dimensions
        PauseButton.setX(widthSize - 200);
        PauseButton.setY(0);


        //add different views to relative layout
        /*rel_main_game.addView(PauseButton);
        rel_main_game.addView(PauseMenu);
        rel_main_game.addView(gamePanel);*/


        gameScreen.addView(gamePanel);
        gameScreen.addView(PauseMenu);
        gameScreen.addView(PauseButton);

        //Access pause buttonlayout settings and apply width and height
        PauseButton.getLayoutParams().height=200;
        PauseButton.getLayoutParams().width=250;

        //Hide Pause Menu
        PauseMenu.setVisibility(View.GONE);


        //Temporarily removed
        gamePanel.setVisibility(View.INVISIBLE);

        //show PauseButton
        PauseButton.setVisibility(View.VISIBLE);



        //Accessing Pause Menu Buttons from layout
        ImageView buttonContinue = (ImageView) findViewById(R.id.btnContinue);
        ImageView buttonMainMenu = (ImageView) findViewById(R.id.btnMainMenu);

        //Assign listeners to Pause Menu and in-game Buttons
        buttonContinue.setOnClickListener(clickContinue_lstnr);
        buttonMainMenu.setOnClickListener(clickMainMenu_lstnr);
        PauseButton.setOnClickListener(clickPause_lstnr);


    }

    @Override
    protected void onResume(){
        super.onResume();

        //hide pause menu and show pause button
        PauseMenu.setVisibility(View.GONE);
        PauseButton.setVisibility(View.VISIBLE);

        gamePanel.Pause_game = false;
        gamePanel.setVisibility(View.VISIBLE);

        //PauseButton.setVisibility(View.GONE);
        //rel_main_game.setVisibility(View.VISIBLE);
    }

    @Override
    protected  void onPause(){
        super.onPause();



        PauseButton.setVisibility(View.GONE);

        //PauseButton.getParent().requestTransparentRegion(gamePanel);
        gamePanel.Pause_game=true;
        gamePanel.setVisibility(View.INVISIBLE);

        //show PauseMenu and hide PauseButton
        PauseMenu.setVisibility(View.VISIBLE);

        //PauseMenu.setVisibility(View.VISIBLE);
        //rel_main_game.setVisibility(View.GONE);
    }
}

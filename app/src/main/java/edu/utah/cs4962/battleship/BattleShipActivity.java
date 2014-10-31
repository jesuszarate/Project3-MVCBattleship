package edu.utah.cs4962.battleship;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;


public class BattleShipActivity extends Activity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout firstLayout = new LinearLayout(this);
        firstLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        firstLayout.addView(buttonLayout, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        Button newGame = new Button(this);
        newGame.setText("New Game");
        buttonLayout.addView(newGame, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        // Second Layout
        LinearLayout secondLayout = new LinearLayout(this);
        secondLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout gameListLayout = new LinearLayout(this);
        gameListLayout.setId(11);
        secondLayout.addView(gameListLayout, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 20
        ));

        FrameLayout gameLayout = new FrameLayout(this);
        gameLayout.setId(10);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 80);
        secondLayout.addView(gameLayout, params);


        FragmentTransaction addTransaction = getFragmentManager().beginTransaction();
        GameFragment gameFragment = new GameFragment();
        GameListFragment gameListFragment = new GameListFragment();
        addTransaction.add(10, gameFragment);
        addTransaction.add(11, gameListFragment);
        addTransaction.commit();

        rootLayout.addView(firstLayout, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        rootLayout.addView(secondLayout, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 90
        ));
        setContentView(rootLayout);
    }


}

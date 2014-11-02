package edu.utah.cs4962.battleship;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class BattleShipActivity extends Activity
{
    public final static String PLAYERS_TURN = "players_turn";
    public FragmentManager fragmentManager;
    public GameFragment _gameFragment;
    GameListFragment _gameListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        if (savedInstanceState != null){
            savedInstanceState = null;
        }
        super.onCreate(savedInstanceState);
        {

            LinearLayout rootLayout = new LinearLayout(this);
            rootLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout firstLayout = new LinearLayout(this);
            firstLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout buttonLayout = new LinearLayout(this);
            buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
            firstLayout.addView(buttonLayout, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            _gameFragment = new GameFragment();
            _gameListFragment = new GameListFragment();

//region NewGameButton

            Button newGameButton = new Button(this);
            newGameButton.setText("New Game");
            buttonLayout.addView(newGameButton, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            newGameButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Game newGame = new Game();

                    // Add the game to the Fragment List.
                    _gameListFragment._gameList.add(newGame);

                }
            });

            _gameListFragment.setOnGameSelectedListener(new GameListFragment.OnGameSelectedListener()
            {
                @Override
                public void onGameSelected(GameListFragment gameListFragment, Game g)
                {
                    Game game = g;

                    _gameFragment.setGame(game);

                    int n = 0;
                    int k = n + 1;
                }
            });

//endregion NewGameButton

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


            fragmentManager = getFragmentManager();
            FragmentTransaction addTransaction = fragmentManager.beginTransaction();

            addTransaction.add(10, _gameFragment);//.addToBackStack("Game");

            addTransaction.add(11, _gameListFragment);//.addToBackStack("GameList");

            addTransaction.commit();

//        _gameFragment.setOnSwitchPlayerListener(new GameFragment.OnSwitchPlayerListener()
//        {
//            @Override
//            public void OnSwitchPlayer(GameFragment gf, String playersTurn)
//            {
//                // TODO: Start a new activity
//                startTrasitionScreenActivity(playersTurn);
//            }
//
//        });

            //gameFragment.InitializeGame(new Game());

//        gameListFragment.set_onGameSelectedListener(new GameListFragment.OnGameSelectedListener()
//        {
//            @Override
//            public void onGameSelected(GameListFragment gameListFragment)
//            {
//              gameFragment.StartGame();
//            }
//        });


            rootLayout.addView(firstLayout, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            rootLayout.addView(secondLayout, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0, 90
            ));
            setContentView(rootLayout);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    protected void onPause()
    {
        super.onPause();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public void startTrasitionScreenActivity(String playersTurn)
    {
//        Intent intent = new Intent(BattleShipActivity.this, TransitionScreen.class);
//
//        intent.putExtra(PLAYERS_TURN, playersTurn);
//        this.startActivity(intent);
    }


}

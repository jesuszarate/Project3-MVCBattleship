package edu.utah.cs4962.battleship;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Jesus Zarate on 10/24/14.
 */
public class GameFragment extends Fragment
{
    final public int _player1ID = 1;
    final public int _player2ID = 2;
    Game _game;

    final String PlayerOne = "PLAYER 1";
    final String PlayerTwo = "PLAYER 2";

    final static public int PLAYER1 = 1;
    final static public int PLAYER2 = 2;
    public Player player1;
    public Player player2;
    int _playersTurn = 1;

    public final static String ALLOWED_TO_TOUCH = "allowed_to_touch";
    public boolean _allowedToTouch = true;

    Timer timer = new Timer();



    //region Listeners

    public interface OnSwitchPlayerListener
    {
        public void OnSwitchPlayer(GameFragment gf, String PlayersTurn);
    }

    OnSwitchPlayerListener _onSwitchPlayerListener = null;

    public void setOnSwitchPlayerListener(OnSwitchPlayerListener _onSwitchPlayerListener)
    {
        this._onSwitchPlayerListener = _onSwitchPlayerListener;
    }

    BattleGridView battleGridView = null;

    //region UpdateListViewsListener

    public interface OnUpdateGameListListener
    {
        public void OnUpdateGameList(GameFragment gameFragment);
    }
    OnUpdateGameListListener _onUpdateGameListListener = null;

    public void setOnUpdateGameListListener(OnUpdateGameListListener onUpdateGameListListener)
    {
        this._onUpdateGameListListener = onUpdateGameListListener;
    }
    //endregion UpdateListViewsListener

    //endregion

    public void InitializeGame(Game game)
    {
        _game = game;
        player1 = _game.getPlayer(PLAYER1);
        player2 = _game.getPlayer(PLAYER2);
        _playersTurn = _game.getPlayersTurn();
        _allowedToTouch = true;

        _game.setOnBattleWonListener(new Game.OnBattleWonListener()
        {
            @Override
            public void OnBattleWon(Game game, String Winner)
            {
                Toast.makeText(getActivity(), Winner, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        battleGridView = new BattleGridView(getActivity(), _player1ID);

//        int cellPostion = 0;
//        for (int position = 0; position < 200; position++)
//        {
//            CellView cellView = new CellView(getActivity());
//
//            // If position % 11 equals 0 then the first row every cell in that row will be equal to a negative number
//            // If position / 11 equals 0 then the first col every cell in that col will be equal to a negative number
//            // if ((position-121)/11 == 0 && position-121 > 0) ensures that the first column in the second grid's cell
//            // are all set equal to a negative number.
////            if (position % 11 == 0 || position / 11 == 0 || ((position - 121) / 11 == 0 && position - 121 > 0))
////            {
////                cellView.setGridPosition(-1);
////            } else
//
//            cellView.setGridPosition(cellPostion);
//
//            battleGridView._childrenIndex.put(cellPostion, position);
//
//            cellPostion++;
//
//            battleGridView.addView(cellView, 55, 55);
//            cellView.setOnCellTouchListener(new CellView.OnCellTouchListener()
//            {
//                @Override
//                public void onCellTouched(CellView c)
//                {
//
//                    if (c.getGridPosition() <= 100)
//                    {
//                        // TODO: let the oponent player know that it has been shot at.
//                        // TODO: MAKE SURE TO NOTE WHICH PLAYER IS WHICH
//                        if (_playersTurn == PLAYER1 && _allowedToTouch)
//                        {
//                            int p = c.getGridPosition();
//                            //if (p > 0)
//                            {
//                                _allowedToTouch = false;
//                                _game.getPlayer(PLAYER1).LaunchMissile(c.getGridPosition());
//
//                            }
//                        } else if (_playersTurn == PLAYER2 && _allowedToTouch)
//                        {
//                            int p = c.getGridPosition();
//                            //if (p > 0)
//                            {
//                                _allowedToTouch = false;
//                                _game.getPlayer(PLAYER2).LaunchMissile(c.getGridPosition());
//                            }
//                        }
//                    }
//                }
//            });
//        }
//        battleGridView.switchPlayersBattleGrid(player1, player2);
//
//        //region <Fire Missiles>
//        player1.setOnMissileFiredListener(new Player.OnMissileFiredListener()
//        {
//            @Override
//            public void OnMissileFired(Player p, int cell)
//            {
//                //player1.addMyAttackToOponentGrid(cell);
//                player2.OponentFiredMissile(cell);
//            }
//        });
//
//
//        player2.setOnMissileFiredListener(new Player.OnMissileFiredListener()
//        {
//            @Override
//            public void OnMissileFired(Player p, int cell)
//            {
//                player1.OponentFiredMissile(cell);
//            }
//        });
//
//        //endregion <Fire Missiles>
//
//
//        //region Missile Attack Result
//        player1.setOnMissileAttackResultListener(new Player.OnMissileAttackResultListener()
//        {
//            @Override
//            public void OnMissileAttackResult(Player p, int cell, String hitMiss)
//            {
//                if (hitMiss.equals(Player.HIT))
//                {
//                    battleGridView.cellHit(cell);
//                    player2.updateAttackResult(cell, GameModel.HIT);
//
//                    _allowedToTouch = true;
//                } else //if (hitMiss.equals(Player.MISS))
//                {
//                    battleGridView.cellMiss(cell);
//                    player2.updateAttackResult(cell, GameModel.MISS);
//
//                    timer.schedule(new SwitchPlayerTask(), 2000);
//                }
//            }
//        });
//        player2.setOnMissileAttackResultListener(new Player.OnMissileAttackResultListener()
//        {
//            @Override
//            public void OnMissileAttackResult(Player p, int cell, String hitMiss)
//            {
//                if (hitMiss.equals(Player.HIT))
//                {
//                    battleGridView.cellHit(cell);
//                    player1.updateAttackResult(cell, GameModel.HIT);
//                    _allowedToTouch = true;
//                } else //if (hitMiss.equals(Player.MISS))
//                {
//                    battleGridView.cellMiss(cell);
//                    player1.updateAttackResult(cell, GameModel.MISS);
//
//                    timer.schedule(new SwitchPlayerTask(), 2000);
//                }
//            }
//        });
//        //endregion Missile Attack Result
        return battleGridView;
    }

    public void setGame(Game game)
    {
        InitializeGame(game);
        battleGridView.setUpGridBackground();
        int cellPostion = 0;
        for (int position = 0; position < 200; position++)
        {
            CellView cellView = new CellView(getActivity());

            cellView.setGridPosition(cellPostion);

            battleGridView._childrenIndex.put(cellPostion, position);

            cellPostion++;

            battleGridView.addView(cellView, 55, 55);
            cellView.setOnCellTouchListener(new CellView.OnCellTouchListener()
            {
                @Override
                public void onCellTouched(CellView c)
                {
                        if (c.getGridPosition() <= 100 && !_game.GameOver)
                    {
                        if (_game.getPlayersTurn() == PLAYER1 && _allowedToTouch)
                        {
                            _allowedToTouch = false;
                            _game.getPlayer(PLAYER1).LaunchMissile(c.getGridPosition());

                        } else if (_game.getPlayersTurn() == PLAYER2 && _allowedToTouch)
                        {
                            _allowedToTouch = false;
                            _game.getPlayer(PLAYER2).LaunchMissile(c.getGridPosition());

                        }
                    }
                }
            });
        }
        battleGridView.switchPlayersBattleGrid(player1, player2);

        //region <Fire Missiles>
        player1.setOnMissileFiredListener(new Player.OnMissileFiredListener()
        {
            @Override
            public void OnMissileFired(Player p, int cell)
            {
                //player1.addMyAttackToOponentGrid(cell);
                player2.OponentFiredMissile(cell);
            }
        });


        player2.setOnMissileFiredListener(new Player.OnMissileFiredListener()
        {
            @Override
            public void OnMissileFired(Player p, int cell)
            {
                player1.OponentFiredMissile(cell);
            }
        });

        //endregion <Fire Missiles>


        //region Missile Attack Result
        player1.setOnMissileAttackResultListener(new Player.OnMissileAttackResultListener()
        {
            @Override
            public void OnMissileAttackResult(Player p, int cell, String hitMiss)
            {
                if (hitMiss.equals(Player.HIT))
                {
                    battleGridView.cellHit(cell);
                    player2.updateAttackResult(cell, GameModel.HIT);
                    _onUpdateGameListListener.OnUpdateGameList(GameFragment.this);

                    _allowedToTouch = true;
                } else //if (hitMiss.equals(Player.MISS))
                {
                    battleGridView.cellMiss(cell);
                    player2.updateAttackResult(cell, GameModel.MISS);
                    _onUpdateGameListListener.OnUpdateGameList(GameFragment.this);

                    timer.schedule(new SwitchPlayerTask(), 2000);
                }

            }
        });
        player2.setOnMissileAttackResultListener(new Player.OnMissileAttackResultListener()
        {
            @Override
            public void OnMissileAttackResult(Player p, int cell, String hitMiss)
            {
                if (hitMiss.equals(Player.HIT))
                {
                    battleGridView.cellHit(cell);
                    player1.updateAttackResult(cell, GameModel.HIT);
                    _allowedToTouch = true;
                    _onUpdateGameListListener.OnUpdateGameList(GameFragment.this);
                } else //if (hitMiss.equals(Player.MISS))
                {
                    battleGridView.cellMiss(cell);
                    player1.updateAttackResult(cell, GameModel.MISS);
                    _onUpdateGameListListener.OnUpdateGameList(GameFragment.this);
                    timer.schedule(new SwitchPlayerTask(), 2000);
                }

            }
        });
        //endregion Missile Attack Result

    }


    public Player getPlayersTurn(int playerID)
    {
        return playerID == 1 ? player1 : player2;
    }

    public Player getOponent(int currentPlayerID)
    {
        return currentPlayerID == 1 ? player2 : player1;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            _allowedToTouch = data.getBooleanExtra(ALLOWED_TO_TOUCH, false);

            _playersTurn = _playersTurn == PLAYER1 ? PLAYER2 : PLAYER1;
            _game.setPlayersTurn(_playersTurn);
            Player currentPlayer = getPlayersTurn(_playersTurn);
            Player oponentPlayer = getOponent(_playersTurn);
            battleGridView.switchPlayersBattleGrid(currentPlayer, oponentPlayer);
        }
    }

    public static String PLAYERS_TURN = "players_turn";

    class SwitchPlayerTask extends TimerTask
    {
        public void run()
        {
            //_onSwitchPlayerListener.OnSwitchPlayer(GameFragment.this, PlayerOne);
            Intent intent = new Intent(getActivity(), TransitionScreen.class);

            if (_game.getPlayersTurn() == Player.Player1ID)
            {
                intent.putExtra(PLAYERS_TURN, PlayerOne);

            } else
            {
                intent.putExtra(PLAYERS_TURN, PlayerTwo);
            }
            startActivityForResult(intent, 6);
        }
    }

}

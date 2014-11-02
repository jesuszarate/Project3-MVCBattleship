package edu.utah.cs4962.battleship;

/**
 * Created by jesuszarate on 10/28/14.
 */
public class Game
{

    Player player1;
    Player player2;

    String Winner;

    //region Listeners

    //endregion Listeners

    public Game()
    {
        player1 = new Player(1);
        player2 = new Player(2);

        player1.setOnPlayerWonListener(new Player.OnPlayerWonListener()
        {
            @Override
            public void OnPlayerWon(Player p)
            {

            }
        });
    }

    public Player getPlayer(int playerID)
    {
        if (playerID == GameFragment.PLAYER1)
        {
            return player1;
        } else //if (playerID == GameFragment.PLAYER2)
        {
            return player2;
        }
    }

    public int getPlayer1Score()
    {
        return player1.PlayersScore;
    }

    public int getPlayer2Score()
    {
        return player2.PlayersScore;
    }

    public int getPlayer1MissilesFired()
    {
        return player1.PlayersMissilesFired;
    }

    public int getPlayer2MissilesFired()
    {
        return player2.PlayersMissilesFired;
    }
}

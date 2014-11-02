package edu.utah.cs4962.battleship;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jesuszarate on 10/31/14.
 */
public class GameCollection
{
    Map<Integer, GameInfo> _gameInfoMap = new HashMap<Integer, GameInfo>();

    static GameCollection _instance = null;

    static GameCollection getInstance()
    {
        if (_instance == null)
        {
            _instance = new GameCollection();
        }
        return _instance;
    }

    private GameCollection()
    {
    }

    public GameInfo getGameInfo(int identifier)
    {
        return _gameInfoMap.get(identifier);
    }

    public void addGameInfo(int identifier, GameInfo gameInfo)
    {
        _gameInfoMap.put(identifier, gameInfo);
    }

    public void removeGameInfo(int identifier)
    {
        _gameInfoMap.remove(identifier);
    }

    public class GameInfo
    {
        public String Winner;
        public String Player1MissilesLaunched;
        public String Player2MissilesLaunched;
        public boolean inProgress;
    }
}

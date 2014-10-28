package edu.utah.cs4962.battleship;

import java.util.HashMap;

/**
 * Created by jesuszarate on 10/26/14.
 */
public class Ships
{
    public MyShips myShips;
    public OponentsShips oponentsShips;

    public Ships()
    {
        myShips = new MyShips();
        oponentsShips = new OponentsShips();
    }

    public class MyShips
    {
        public HashMap<String, int[]> ships;
        public MyShips()
        {
            ships = new HashMap<String, int[]>();
            ships.put("AirCraftCarrer", new int[5]);
            ships.put("Battleship", new int[4]);
            ships.put("Submarine", new int[3]);
            ships.put("Cruiser", new int[3]);
            ships.put("Destroyer", new int[2]);
        }

    }

    public class OponentsShips
    {
        public HashMap<String, int[]> ships;
        public OponentsShips()
        {
            ships = new HashMap<String, int[]>();
            ships.put("AirCraftCarrer", new int[5]);
            ships.put("Battleship", new int[4]);
            ships.put("Submarine", new int[3]);
            ships.put("Cruiser", new int[3]);
            ships.put("Destroyer", new int[2]);
        }
    }
}

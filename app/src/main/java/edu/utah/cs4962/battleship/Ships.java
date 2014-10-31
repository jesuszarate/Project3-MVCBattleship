package edu.utah.cs4962.battleship;

import android.graphics.Path;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jesuszarate on 10/26/14.
 */
public class Ships
{
    public MyShips myShips;
    public OponentsShips oponentsShips;
    final private int LEFT = 0;
    final private int UP = 1;
    final private int RIGHT = 2;
    final private int DOWN = 3;


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
            int[] AirCraftCarrier = {-1, -2, -3, -4, -5};
            int[] Battleship = {-1, -2, -3, -4};
            int[] Submarine = {-1, -2, -3};
            int[] Cruiser = {-1, -2, -3};
            int[] Destroyer = {-1, -2};

            ships.put("AirCraftCarrier", AirCraftCarrier);
            ships.put("Battleship", Battleship);
            ships.put("Submarine", Submarine);
            ships.put("Cruiser", Cruiser);
            ships.put("Destroyer", Destroyer);
        }

    }

    public class OponentsShips
    {
        public HashMap<String, int[]> ships;

        public OponentsShips()
        {
            ships = new HashMap<String, int[]>();
            ships.put("AirCraftCarrier", new int[5]);
            ships.put("Battleship", new int[4]);
            ships.put("Submarine", new int[3]);
            ships.put("Cruiser", new int[3]);
            ships.put("Destroyer", new int[2]);
        }
    }

    public void setMyShipsRandomly(int playerId)
    {

        setAirCraftCarrier(playerId);
        setBattleship(playerId);
        setSubmarine(playerId);
        setCruiser(playerId);
        setDestroyer(playerId);

    }

    private void setDestroyer(int playerId)
    {
        int position = randomPositionOfShip();

        // Direction =≥ left = 0, up = 1, right = 2, down = 3
        int direction = randomShipDirection();

        int arrayIndex = 0;
        if (playerId == 1)
        {
            switch (direction)
            {
                case LEFT:
                    getLeftCell("Destroyer", 2, position);
                    break;
                case UP:
                    getTopCell("Destroyer", 2, position, arrayIndex);
                    break;
                case RIGHT:
                    getRightCell("Destroyer", 2, position, arrayIndex);
                    break;
                case DOWN:
                    getBottomCell("Destroyer", 2, position, arrayIndex);
                    break;
            }
        }
//        else
//        {
//            for (; position < range; position++)
//            {
//                myShips.ships.get("Destroyer")[arrayIndex] = position;
//                arrayIndex++;
//            }
//        }
    }

    private void setCruiser(int playerId)
    {

    }

    private void setSubmarine(int playerId)
    {

    }

    private void setBattleship(int playerId)
    {

    }

    private void setAirCraftCarrier(int playerId)
    {
        int position = randomPositionOfShip();

        // Direction =≥ left = 0, up = 1, right = 2, down = 3
        int direction = randomShipDirection();

        int arrayIndex = 0;
        if (playerId == 1)
        {
            switch (direction)
            {
                case LEFT:
                    getLeftCell("AirCraftCarrier", 4, position);
                    break;
                case UP:
                    getTopCell("AirCraftCarrier", 4, position, arrayIndex);
                    break;
                case RIGHT:
                    getRightCell("AirCraftCarrier", 4, position, arrayIndex);
                    break;
                case DOWN:
                    getBottomCell("AirCraftCarrier", 4, position, arrayIndex);
                    break;
            }
        }
    }

    // TODO: Keep the ships in bounds.
    private void getLeftCell(String shipType, int shipSize, int position)
    {
        position = checkForCorrectPosition(position, shipSize, LEFT);
        int range = position - shipSize;
        int pos = position;
        int arrayIndex = 0;
        for (; position > range; position--)
        {
            myShips.ships.get(shipType)[arrayIndex] = pos - (arrayIndex * 10);
            arrayIndex++;
        }
    }

    private int checkForCorrectPosition(int position, int shipSize, int direction)
    {
        switch (direction)
        {
            case LEFT:
                if ((position - (shipSize * 10)) < 0)
                {
                    return (position + shipSize) * 10;
                }
                break;
            case UP:
                break;
            case RIGHT:
                break;
            case DOWN:
                break;
        }
        return position;
    }

    private void getTopCell(String shipType, int shipSize, int position, int arrayIndex)
    {
        int range = position - shipSize;
        for (; position > range; position--)
        {
            myShips.ships.get(shipType)[arrayIndex] = position;
            arrayIndex++;
        }
    }

    private void getRightCell(String shipType, int shipSize, int position, int arrayIndex)
    {
        int range = position + shipSize;
        int pos = position;
        for (; position < range; position++)
        {
            myShips.ships.get(shipType)[arrayIndex] = pos + (arrayIndex * 10);
            arrayIndex++;
        }
    }

    private void getBottomCell(String shipType, int shipSize, int position, int arrayIndex)
    {
        int range = position + shipSize;
        for (; position < range; position++)
        {
            myShips.ships.get(shipType)[arrayIndex] = position;
            arrayIndex++;
        }
    }

    private Random randomGenerator = new Random(System.currentTimeMillis());

    public int randomPositionOfShip()
    {
        int randomInt = randomGenerator.nextInt(99);
        return randomInt;
    }

    public int randomShipDirection()
    {
        int randomInt = randomGenerator.nextInt(4);
        return randomInt;
    }
}



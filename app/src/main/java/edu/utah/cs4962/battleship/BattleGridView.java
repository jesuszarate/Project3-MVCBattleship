package edu.utah.cs4962.battleship;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by jesuszarate on 10/25/14.
 */
public class BattleGridView extends ViewGroup
{
    public HashMap<Integer, Integer> _childrenIndex = new HashMap<Integer, Integer>();

    RectF _paletteRect;
    public static Ships _ships = new Ships();
    int _playerID;

    public BattleGridView(Context context, int PlayerID)
    {
        super(context);

        this._playerID = PlayerID;

    }

    public void InitializeBattleGrid()
    {
        _ships.setMyShipsRandomly();
    }

    public void cellHit(int cell)
    {
        int cellPosition = _childrenIndex.get(cell);
        getChildAt(cellPosition).setBackgroundColor(Color.RED);
    }

    public void cellMiss(int cell)
    {
        int cellPosition = _childrenIndex.get(cell);
        getChildAt(cellPosition).setBackgroundColor(Color.WHITE);
    }

    public void switchPlayersBattleGrid(Player player1, Player player2)
    {
        resetBattleGrid();
        setUpMyGrid(player1);
        setUpOponentsGrid(player1);
//        for (int cellIndex = 101; cellIndex < getChildCount(); cellIndex++)
//        {
//            if(player._myGameGrid.containsKey(cellIndex))
//            {
//                int hitMissWater = player._myGameGrid.get(cellIndex);
//                CellView cellView = (CellView)getChildAt(cellIndex);
//
//                if(hitMissWater == GameModel.WATER)
//                {
//                    cellView.setBackgroundColor(Color.BLUE);
//                }
//                else if (hitMissWater == GameModel.MISS)
//                {
//                    cellView.setBackgroundColor(Color.WHITE);
//                }
//                else{
//                    cellView.setBackgroundColor(Color.RED);
//                }
//            }
//        }
    }

    public void setUpOponentsGrid(Player player)
    {
        for (int cellIndex = 0; cellIndex < getChildCount(); cellIndex++)
        {
            if (player._oponentGameGrid.containsKey(cellIndex))
            {
                int hitMissWater = player._oponentGameGrid.get(cellIndex);
                CellView cellView = (CellView) getChildAt(cellIndex);

                if (hitMissWater == GameModel.WATER)
                {
                    cellView.setBackgroundColor(Color.BLUE);
                } else if (hitMissWater == GameModel.MISS)
                {
                    cellView.setBackgroundColor(Color.WHITE);
                } else
                {
                    cellView.setBackgroundColor(Color.RED);
                }
            }
        }
    }


    public void setUpMyGrid(Player player)
    {
        try
        {
            for (int[] ship : player._ships.myShips.ships.values())
            {
                for (int shipPos = 0; shipPos < ship.length; shipPos++)
                {
                    if (ship[shipPos] > -1)
                        getChildAt(ship[shipPos] + 99).setBackgroundColor(Color.GRAY);
                }
            }

            Iterator it = player._oponentsAttacks.entrySet().iterator();

            while (it.hasNext())
            {
                Map.Entry pairs = (Map.Entry) it.next();
                try
                {
                    if ((Integer) pairs.getValue() == GameModel.MISS)
                    {
                        getChildAt((Integer) pairs.getKey() + 100).setBackgroundColor(Color.WHITE);
                    } else if ((Integer) pairs.getValue() == GameModel.WATER)
                    {
                        getChildAt((Integer) pairs.getKey() + 100).setBackgroundColor(Color.BLUE);
                    } else
                    {
                        getChildAt((Integer) pairs.getKey() + 100).setBackgroundColor(Color.RED);
                    }

                } catch (Exception e)
                {
                }
            }
        }
        catch (Exception e){
            String exp = e.toString();
        }
//        for (int attack : player._oponentsAttacks)
//        {
//            if (attack == GameModel.HIT)
//            {
//                getChildAt(attack);
//            }
//        }
    }

    public void resetBattleGrid()
    {

        //for (int[] ship : player._ships.myShips.ships.values())
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            getChildAt(childIndex).setBackgroundColor(Color.BLUE);
        }
    }

    //region Overwritten Methods

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);

        setBackground(new drawable());

        for (int shipPositions : _childrenIndex.values())
        {

            // TODO: UNCOMMENT ME
            //getChildAt(shipPositions).setBackgroundColor(Color.DKGRAY);

        }

//        for (int[] shipPositions : _ships.myShips.ships.values())
//        {
//            for (int position = 0; position < shipPositions.length; position++)
//            {
//                if (shipPositions[position] > 0)
//                {
//                    int pos = _childrenIndex.get(shipPositions[position]);
//                    //getChildAt(_childrenIndex.get(position)).setBackgroundColor(Color.DKGRAY);
////                    getChildAt(shipPositions[position]).setBackgroundColor(Color.DKGRAY);
//                }
//            }
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpec = MeasureSpec.getSize(heightMeasureSpec);
            int width = Math.max(widthSpec, getSuggestedMinimumWidth());
            int height = Math.max(heightSpec, getSuggestedMinimumHeight());

            int childState = 0;
            for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
            {

                View child = getChildAt(childIndex);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }

            setMeasuredDimension(resolveSizeAndState(width / 2, widthMeasureSpec, childState),
                    resolveSizeAndState(height / 2, heightMeasureSpec, childState));
        } else
        {
            int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpec = MeasureSpec.getSize(heightMeasureSpec);
            int width = Math.max(widthSpec, getSuggestedMinimumWidth());
            int height = Math.max(heightSpec, getSuggestedMinimumHeight());

            int childState = 0;
            for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
            {

                View child = getChildAt(childIndex);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }

            setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, childState),
                    resolveSizeAndState(height, heightMeasureSpec, childState));
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4)
    {
//        int childWidthMax = 0;
//        int childHeightMax = 0;
//        //_childrenNotGone = 0;
//        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
//        {
//            CellView child = (CellView) getChildAt(childIndex);
//
//            if (child.getVisibility() == GONE)
//            {
//                continue;
//            }
//            //_children.add(child);
//
//            childHeightMax = child.getMeasuredHeight();
//            childWidthMax = child.getMeasuredWidth();
//
//            //_childrenNotGone++;
//        }
//
//        _layoutRect = new Rect();
//        _layoutRect.left = getPaddingLeft() + childWidthMax;
//        _layoutRect.top = getPaddingTop() + childHeightMax;
//        _layoutRect.right = getWidth() - getPaddingRight() - childWidthMax;
//        _layoutRect.bottom = getHeight() - getPaddingBottom() - childHeightMax;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            int cc = getChildCount();
            View child = getChildAt(childIndex);
            Rect childLayout = new Rect();

//            int actualWidth = (getMeasuredWidth() / (22));
//            int acutalHeight = (getMeasuredHeight() / (11));
//
//            childLayout.left = (childIndex / 11) * (actualWidth);
//            childLayout.top = (childIndex % 11) * acutalHeight;
//            childLayout.right = ((childIndex / 11) * actualWidth) + actualWidth;
//            childLayout.bottom = ((childIndex % 11) * acutalHeight) + acutalHeight;

            int actualWidth = (getMeasuredWidth() / (20));
            int acutalHeight = (getMeasuredHeight() / (10));

            childLayout.left = (childIndex / 10) * (actualWidth);
            childLayout.top = (childIndex % 10) * acutalHeight;
            childLayout.right = ((childIndex / 10) * actualWidth) + actualWidth;
            childLayout.bottom = ((childIndex % 10) * acutalHeight) + acutalHeight;

            //           ______________________________________________________________________________________
            // Params -> | First => Row Start | Second => Column Start | Third => Row End| Fourth => Column End|
            //           --------------------------------------------------------------------------------------
            child.layout(childLayout.left, childLayout.top, childLayout.right - 5, childLayout.bottom - 5);
        }
    }

    private class drawable extends Drawable
    {

        public void draw(Canvas canvas)
        {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);

            _paletteRect = new RectF();
            _paletteRect.left = ((getWidth() - getPaddingLeft()) - 10) / 2;
            _paletteRect.top = getPaddingTop();
            _paletteRect.right = (getWidth() - getPaddingRight());
            _paletteRect.bottom = getHeight() - getPaddingBottom();

            canvas.drawRect(_paletteRect.left, _paletteRect.top,
                    _paletteRect.right, _paletteRect.bottom, paint);

        }

        @Override
        public void setAlpha(int i)
        {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter)
        {

        }

        @Override
        public int getOpacity()
        {
            return 0;
        }

    }

    //endregion
}






package edu.utah.cs4962.battleship;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by jesuszarate on 10/25/14.
 */
public class BattleGridView extends ViewGroup
{

    //public static ArrayList<View> _children = new ArrayList<View>();

    private Rect _layoutRect;
    public HashMap<Integer, Integer> _childrenIndex = new HashMap<Integer, Integer>();
    //private int _childrenNotGone = 0;

    RectF _paletteRect;
    public static Ships _ships = new Ships();
    int _playerID;

    public BattleGridView(Context context, int PlayerID)
    {
        super(context);

        this._playerID = PlayerID;
        _ships.setMyShipsRandomly(_playerID);
    }


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

        for (int[] shipPositions : _ships.myShips.ships.values())
        {
            for (int position = 0; position < shipPositions.length; position++)
            {
                if (shipPositions[position] > 0)
                {
                    int pos = _childrenIndex.get(shipPositions[position]);
                    getChildAt(pos).setBackgroundColor(Color.DKGRAY);
//                    getChildAt(shipPositions[position]).setBackgroundColor(Color.DKGRAY);
                }
            }
        }
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
        int childWidthMax = 0;
        int childHeightMax = 0;
        //_childrenNotGone = 0;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            CellView child = (CellView) getChildAt(childIndex);

            if (child.getVisibility() == GONE)
            {
                continue;
            }
            //_children.add(child);

            childHeightMax = child.getMeasuredHeight();
            childWidthMax = child.getMeasuredWidth();

            //_childrenNotGone++;
        }

        _layoutRect = new Rect();
        _layoutRect.left = getPaddingLeft() + childWidthMax;
        _layoutRect.top = getPaddingTop() + childHeightMax;
        _layoutRect.right = getWidth() - getPaddingRight() - childWidthMax;
        _layoutRect.bottom = getHeight() - getPaddingBottom() - childHeightMax;


        for (int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {
            View child = getChildAt(childIndex);
            Rect childLayout = new Rect();

            int actualWidth = (getMeasuredWidth() / (22));
            int acutalHeight = (getMeasuredHeight() / (11));

            childLayout.left = (childIndex / 11) * (actualWidth);
            childLayout.top = (childIndex % 11) * acutalHeight;
            childLayout.right = ((childIndex / 11) * actualWidth) + actualWidth;
            childLayout.bottom = ((childIndex % 11) * acutalHeight) + acutalHeight;

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

}






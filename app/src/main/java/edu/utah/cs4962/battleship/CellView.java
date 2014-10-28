package edu.utah.cs4962.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jesus Zarate on 10/24/14.
 */
public class CellView extends View
{
    private int _gridPosition = 0;
    public Ships _ships;

    public CellView(Context context)
    {
        super(context);

        _ships = new Ships();
        _ships.myShips.ships.get("Destroyer")[0] = 0;
        _ships.myShips.ships.get("Destroyer")[1] = 1;

        setBackgroundColor(Color.DKGRAY);
    }

    public int getGridPosition()
    {
        return _gridPosition;
    }

    public void setGridPosition(int _gridPosition)
    {
        this._gridPosition = _gridPosition;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        _onCellTouchListener.onCellTouched(this);
        //Toast.makeText(getContext(), "" + _gridPosition, Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }

    public void FireMissile(int _playerID)
    {
        if (_playerID == 1 && _gridPosition % 22 < 11)
        {
            for (int i = 0; i < _ships.myShips.ships.get("Destroyer").length; i++)
            {
                if (_ships.myShips.ships.get("Destroyer")[i] == _gridPosition)
                {
                    setBackgroundColor(Color.RED);
                    return;
                } else setBackgroundColor(Color.WHITE);
            }
        }
        else if (_playerID == 2 && _gridPosition % 22 > 10)
        {
            for (int i = 0; i < _ships.myShips.ships.get("Destroyer").length; i++)
            {
                if (_ships.myShips.ships.get("Destroyer")[i] == _gridPosition)
                {
                    setBackgroundColor(Color.RED);
                } else setBackgroundColor(Color.WHITE);
            }
        }
    }

    public interface OnCellTouchListener
    {
        public void onCellTouched(CellView cellView);
    }

    OnCellTouchListener _onCellTouchListener = null;

    public OnCellTouchListener getOnCellTouchListener()
    {
        return _onCellTouchListener;
    }

    public void setOnCellTouchListener(OnCellTouchListener _onCellTouchListener)
    {
        this._onCellTouchListener = _onCellTouchListener;
    }

    public void shipHit()
    {
        setBackgroundColor(Color.RED);
        invalidate();
    }

    //    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        // First four lines are extract the bit mask
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//
//        // Pull the information associated with the Mode.
//        // -> Unspecified - widthSpec, heightSpec contain no value, usually 0.
//        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSpec = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width = getSuggestedMinimumWidth();
//        int height = getSuggestedMinimumHeight();
//
//        if (widthMode == MeasureSpec.AT_MOST){
//            width = widthSpec;
//        }
//        if (heightMode == MeasureSpec.AT_MOST){
//            height = heightSpec;
//        }
//
//        if (widthMode ==  MeasureSpec.EXACTLY){
//            width = widthSpec;
//            height = width;
//        }
//        if (heightMode == MeasureSpec.EXACTLY){
//            height = heightSpec;
//            width = height;
//        }
//
//        // TODO; RESPECT THE PADDING!
//        if (width > height && widthMode != MeasureSpec.EXACTLY){
//            width = height;
//        }
//        if (height > width && heightMode != MeasureSpec.EXACTLY){
//            height = width;
//        }
//
//        // resolveSizeAndState(int size, int measureSpec, int childMeasuredState)
//        // -> childMeasuredState - boolean asks if you are happy with the size or not.
//        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec,
//                        width < getSuggestedMinimumWidth() ? MEASURED_STATE_TOO_SMALL: 0),
//                resolveSizeAndState(height, heightMeasureSpec,
//                        height < getSuggestedMinimumHeight() ? MEASURED_STATE_TOO_SMALL: 0));
//    }

}

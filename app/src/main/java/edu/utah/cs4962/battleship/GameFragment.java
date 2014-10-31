package edu.utah.cs4962.battleship;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Jesus Zarate on 10/24/14.
 */
public class GameFragment extends Fragment
{
    public int _playerID = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        final BattleGridView battleGridView = new BattleGridView(getActivity(), _playerID);

        int cellPostion = 1;
        for (int position = 0; position < (121*2); position++)
        {
            CellView cellView = new CellView(getActivity());

            // If position % 11 equals 0 then the first row every cell in that row will be equal to a negative number
            // If position / 11 equals 0 then the first col every cell in that col will be equal to a negative number
            // if ((position-121)/11 == 0 && position-121 > 0) ensures that the first column in the second grid's cell
            // are all set equal to a negative number.
            if(position % 11 == 0 || position/11 == 0 || ((position-121)/11 == 0 && position-121 > 0))
            {
                cellView.setGridPosition(-1);
            }
            else
            {
                cellView.setGridPosition(cellPostion);
                battleGridView._childrenIndex.put(cellPostion, position);
                cellPostion++;
            }

            //if(cellPosition % 22 > 10)
            {
                cellView.setBackgroundColor(Color.BLUE);
                cellView.invalidate();
            }


            battleGridView.addView(cellView, 55, 55);
            cellView.setOnCellTouchListener(new CellView.OnCellTouchListener()
            {
                @Override
                public void onCellTouched(CellView c)
                {
                    c.FireMissile(_playerID);
                    //c.shipHit();
                }
            });

        }
        return battleGridView;
    }

}

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

        for (int cellPosition = 0; cellPosition < (121*2); cellPosition++)
        {
            CellView cellView = new CellView(getActivity());
            cellView.setGridPosition(cellPosition);

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

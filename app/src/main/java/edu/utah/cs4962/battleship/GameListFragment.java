package edu.utah.cs4962.battleship;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Region;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by jesuszarate on 10/24/14.
 */
public class GameListFragment extends Fragment implements ListAdapter
{

    ArrayList<Game> _gameList = new ArrayList<Game>();

    //region Listeners
    public interface OnGameSelectedListener
    {
        public void onGameSelected(GameListFragment gameListFragment, Game game);
    }

    OnGameSelectedListener _onGameSelectedListener = null;

    public OnGameSelectedListener getOnGameSelectedListener()
    {
        return _onGameSelectedListener;
    }

    public void setOnGameSelectedListener(OnGameSelectedListener _onGameSelectedListener)
    {
        this._onGameSelectedListener = _onGameSelectedListener;
    }

    //endregion Listeners

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ListView gameList = new ListView(getActivity());
        gameList.setAdapter(this);
        gameList.setBackgroundColor(Color.LTGRAY);

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(_onGameSelectedListener != null)
                {
                    _onGameSelectedListener.onGameSelected(GameListFragment.this, _gameList.get(i));
                }
            }
        });

        if (_gameList == null)
        {
            _gameList = new ArrayList<Game>();
        }
        _gameList.add(new Game());
        _gameList.add(new Game());
        _gameList.add(new Game());

        return gameList;

//        View view = new View(getActivity());
//        view.setBackgroundColor(Color.GREEN);
//
//        return view;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int i)
    {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver)
    {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver)
    {

    }

    @Override
    public int getCount()
    {
        if (_gameList == null)
        {
            _gameList = new ArrayList<Game>();
        }
        return _gameList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return _gameList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        TextView gameInfo = new TextView(getActivity());
        gameInfo.setText("Game " + i + " Player1 Score: " + _gameList.get(i).getPlayer1Score()
        + " Player2 Score: " + _gameList.get(i).getPlayer2Score());

        return gameInfo;
    }

    @Override
    public int getItemViewType(int i)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return _gameList.size() <= 0;
    }
}

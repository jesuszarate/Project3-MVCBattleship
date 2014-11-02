package edu.utah.cs4962.battleship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jesus Zarate on 10/29/14.
 */
public class TransitionScreen extends Activity
{
    private String _playersTurn = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(BattleShipActivity.PLAYERS_TURN)) {
            _playersTurn = getIntent().getExtras().getString(BattleShipActivity.PLAYERS_TURN);
        }

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        TextView playerTurnText = new TextView(this);
        playerTurnText.setText(_playersTurn);
        playerTurnText.setGravity(View.TEXT_ALIGNMENT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER_HORIZONTAL;
        rootLayout.addView(playerTurnText, params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 10);
        params.gravity = Gravity.BOTTOM;

        Button okButton = new Button(this);
        okButton.setText("Ok");
        rootLayout.addView(okButton, params1);
        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Include the color the use picked so that it can also be updated in the
                // button preview of the Create Mode.
                Intent resultIntent = new Intent();
                resultIntent.putExtra(GameFragment.ALLOWED_TO_TOUCH, true);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        setContentView(rootLayout);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    protected void onPause()
    {
        super.onPause();

    }
}

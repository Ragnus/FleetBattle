package de.hypoport.community.mobile.fleetbattle.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import de.hypoport.community.mobile.fleetbattle.R;

public class GameActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // gameEngine.callShipPatterns(this);
    }


}

package de.hypoport.community.mobile.fleetbattle.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import java.util.Collection;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.ui.fragments.PlacementFragment;
import de.hypoport.community.mobile.fleetbattle.ui.layouts.HarborLayout;

import static java.util.Arrays.asList;

public class GameActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // gameEngine.callShipPatterns(this);
    }


}

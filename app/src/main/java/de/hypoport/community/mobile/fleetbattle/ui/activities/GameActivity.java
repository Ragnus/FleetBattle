package de.hypoport.community.mobile.fleetbattle.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.ui.fragments.PlacementFragment;

public class GameActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


}

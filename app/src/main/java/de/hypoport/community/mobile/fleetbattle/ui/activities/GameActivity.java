package de.hypoport.community.mobile.fleetbattle.ui.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.ui.fragments.MainMatrixFragment;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//        if (savedInstanceState == null) {
            MainMatrixFragment matrixFragment = MainMatrixFragment.getInstance();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, matrixFragment, "matrix")
                    .commit();
//        }
    }

}

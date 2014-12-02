package de.hypoport.community.mobile.fleetbattle.ui.utilities;

import android.util.Log;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType;

/**
 * Created by bozan on 02.12.2014.
 */
public class ShipResourceResolver {
    private static final String TAG = ShipResourceResolver.class.getSimpleName();

    public static int getResource(ShipType type) {
        switch (type) {
            case BATTLESHIP:
                return R.drawable.battleship;
            case DESTROYER:
                return R.drawable.destroyer;
            case CRUISER:
                return R.drawable.cruiser;
            case SUBMARINE:
                return R.drawable.submarine;
        }
        Log.e(TAG, "No View for type " + type);
        return -1;
    }
}

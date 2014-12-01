package de.hypoport.community.mobile.fleetbattle.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.ui.views.harbor.HarborView;
import de.hypoport.community.mobile.fleetbattle.ui.views.MainMatrixView;

import static java.util.Arrays.asList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlacementFragment extends Fragment {

    public static final String TAG = "placement";

    Collection<ShipPattern> shipPatterns = asList(
            ShipPattern.battleship(1),
            ShipPattern.destroyer(2),
            ShipPattern.cruiser(3),
            ShipPattern.submarine(4)
    );

    MainMatrixView matrixView;

    HarborView harborView;

    public PlacementFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View placementView = inflater.inflate(R.layout.fragment_placement, container, false);
        matrixView = (MainMatrixView) placementView.findViewById(R.id.main_matrix);
        harborView = (HarborView) placementView.findViewById(R.id.harbor);
        harborView.setShipPatterns(shipPatterns);

        return placementView;
    }

    @Override
    public void onResume() {
        super.onResume();



    }


}

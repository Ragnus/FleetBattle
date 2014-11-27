package de.hypoport.community.mobile.fleetbattle.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.ui.layouts.HarborLayout;
import de.hypoport.community.mobile.fleetbattle.ui.views.MainMatrixView;

import static java.util.Arrays.asList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlacementFragment extends Fragment {

    public static final String TAG = "placement";

    Collection<ShipPattern> shipPatterns = asList(new ShipPattern(3, Orientation.HORIZONTAL, 3));

    MainMatrixView matrixView;

    HarborLayout  harborLayout;

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
        harborLayout = (HarborLayout) placementView.findViewById(R.id.harbor);

        harborLayout.setShipPatterns(shipPatterns);

        return placementView;
    }

    @Override
    public void onResume() {
        super.onResume();



    }


}

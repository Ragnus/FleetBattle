package de.hypoport.community.mobile.fleetbattle.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.ui.views.MainMatrixView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMatrixFragment extends Fragment {

    MainMatrixView matrixView;

    public MainMatrixFragment() {
    }

    /**
     * Returns Instance of MainMatrixFragment
     *
     * @return Instance of MainMatrixFragment
     */
    public static MainMatrixFragment getInstance() {
        MainMatrixFragment fragment = new MainMatrixFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_game, container, false);
        matrixView = (MainMatrixView) _view.findViewById(R.id.main_matrix);
        return _view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

package de.hypoport.community.mobile.fleetbattle.ui.views.harbor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType;

/**
 * Created by Bozan on 27.11.2014.
 */
public class ShipInHarborView extends LinearLayout {
    private ShipPattern shipPattern;
    private View layoutView;


    public ShipInHarborView(Context context) {
        super(context);
        init();
    }

    public ShipInHarborView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShipInHarborView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutView = inflater.inflate(R.layout.ship_in_harbor_view, this, true);
    }

    public void setShipPattern(ShipPattern shipPattern) {
        this.shipPattern = shipPattern;

        TextView tvShipCount = (TextView) layoutView.findViewById(R.id.tvShipCount);
        tvShipCount.setText("[" + shipPattern.numberOfShips + "]");

        ImageView shipView = getViewForShip();
        shipView.setVisibility(VISIBLE);
    }

    private ImageView getViewForShip() {
        ImageView view;
        switch (shipPattern.type) {
            case BATTLESHIP:
                view = (ImageView) layoutView.findViewById(R.id.battleshipView);
                break;
            case DESTROYER:
                view = (ImageView) layoutView.findViewById(R.id.destroyerView);
                break;
            case CRUISER:
                view = (ImageView) layoutView.findViewById(R.id.cruiserView);
                break;
            default:
                // TODO Weitere Views basteln
                view = new ImageView(getContext());
        }
        return view;
    }
}

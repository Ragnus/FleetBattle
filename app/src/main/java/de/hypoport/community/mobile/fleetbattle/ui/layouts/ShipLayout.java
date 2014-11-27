package de.hypoport.community.mobile.fleetbattle.ui.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;

/**
 * Created by Bozan on 27.11.2014.
 */
public class ShipLayout extends LinearLayout {
    private ShipPattern shipPattern;
    private View layoutView;


    public ShipLayout(Context context) {
        super(context);
        init(context);
    }

    public ShipLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShipLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutView = inflater.inflate(R.layout.view_harbor_ship_layout, this, true);
    }

    public void setShipPattern(ShipPattern shipPattern) {
        this.shipPattern = shipPattern;

        TextView tvShipCount = (TextView) layoutView.findViewById(R.id.tvShipCount);
        tvShipCount.setText(shipPattern.numberOfShips);

        TextView tvShape = (TextView) layoutView.findViewById(R.id.tvShape);
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < shipPattern.size; i++) {
            text.append("x\n");
        }
        tvShape.setText(text.toString());
    }
}

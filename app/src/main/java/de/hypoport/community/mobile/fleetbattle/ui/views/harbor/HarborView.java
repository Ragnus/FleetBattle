package de.hypoport.community.mobile.fleetbattle.ui.views.harbor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Collection;

import de.hypoport.community.mobile.fleetbattle.engine.GameEngine;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;

/**
 * The harbor for all new ships
 */
public class HarborView extends LinearLayout {

    public HarborView(Context context) {
        super(context);
    }

    public HarborView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HarborView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setShipPatterns(Collection<ShipPattern> shipPatterns) {

        for (ShipPattern shipPattern : shipPatterns) {
            ShipInHarborView shipInHarborView = new ShipInHarborView(getContext());
            shipInHarborView.setShipPattern(shipPattern);
            addView(shipInHarborView);
        }
    }
}

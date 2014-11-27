package de.hypoport.community.mobile.fleetbattle.ui.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.Collection;

import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;

/**
 * The harbor for all new ships
 */
public class HarborLayout extends LinearLayout {

    public HarborLayout(Context context) {
        super(context);
        init(context);
    }

    public HarborLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HarborLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setShipPatterns(Collection<ShipPattern> shipPatterns) {
        for (ShipPattern shipPattern : shipPatterns) {
            ShipLayout shipLayout = new ShipLayout(getContext());
            shipLayout.setShipPattern(shipPattern);
            addView(shipLayout);
        }
    }
}

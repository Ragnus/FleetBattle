package de.hypoport.community.mobile.fleetbattle.engine.rules;

import java.util.ArrayList;
import java.util.Collection;

import de.hypoport.community.mobile.fleetbattle.engine.Ship;

/**
 * Created by Ragnus on 20.11.2014.
 */
public interface PlacementRules {

    public int getMinX();

    public int getMinY();

    public int getMaxX();

    public int getMaxY();

    public int getMinShipSize();

    public int getMaxShipSize();

    public Collection<ShipPattern> getShipPatterns();

    public boolean isPositionGoodForPlacing(Ship ship);

    public boolean isShipFittingToFleet(Ship ship);
}

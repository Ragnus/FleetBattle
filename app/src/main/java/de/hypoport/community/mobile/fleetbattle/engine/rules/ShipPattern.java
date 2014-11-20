package de.hypoport.community.mobile.fleetbattle.engine.rules;

import de.hypoport.community.mobile.fleetbattle.engine.Orientation;

/**
 * Created by Ragnus on 20.11.2014.
 */
public class ShipPattern {
    public int size;
    public Orientation orientation;
    public int numberOfShips;

    public ShipPattern(int size, Orientation orientation, int numberOfShips) {
        this.size = size;
        this.numberOfShips = numberOfShips;
        this.orientation = orientation;
    }
}

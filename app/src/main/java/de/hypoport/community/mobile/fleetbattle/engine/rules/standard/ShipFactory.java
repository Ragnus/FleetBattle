package de.hypoport.community.mobile.fleetbattle.engine.rules.standard;

import java.util.ArrayList;

import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.engine.rules.Shipyard;

/**
 * Created by Ragnus on 19.11.2014.
 */
public class ShipFactory implements Shipyard {
    public static final int MIN_SIZE = 2;
    public static final int MAX_SIZE = 5;

    public Ship buildShip(int size, Orientation orientation) {
        if (size < MIN_SIZE || size > MAX_SIZE)
            throw new RuntimeException(String.format("Ein Schiff darf nur die Größe %i - %i haben.", MIN_SIZE, MAX_SIZE));
        return new Ship(size, orientation);
    }

    @Override
    public ArrayList<Ship> buildShips(ArrayList<ShipPattern> shipPatterns) {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        for (ShipPattern shipPattern : shipPatterns) {
            for (int i = 0; i < shipPattern.numberOfShips; i++) {
                ships.add(buildShip(shipPattern.size, shipPattern.orientation));
            }
        }
        return ships;
    }
}

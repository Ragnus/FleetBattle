package de.hypoport.community.mobile.fleetbattle.engine.rules.standard;

import com.google.common.base.Optional;

import java.util.ArrayList;

import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType;
import de.hypoport.community.mobile.fleetbattle.engine.rules.Shipyard;

/**
 * Created by Ragnus on 19.11.2014.
 */
public class ShipFactory implements Shipyard {

    @Override
    public Ship buildShip(ShipType type, Optional<Orientation> orientation) {
        return new Ship(type, orientation);
    }

    @Override
    public ArrayList<Ship> buildShips(ArrayList<ShipPattern> shipPatterns) {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        for (ShipPattern shipPattern : shipPatterns) {
            for (int i = 0; i < shipPattern.numberOfShips; i++) {
                ships.add(buildShip(shipPattern.type, shipPattern.orientation));
            }
        }
        return ships;
    }
}

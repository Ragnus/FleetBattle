package de.hypoport.community.mobile.fleetbattle.engine.rules;

import com.google.common.base.Optional;

import java.util.ArrayList;

import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;

/**
 * Created by Ragnus on 20.11.2014.
 */
public interface Shipyard {

    public Ship buildShip(ShipType type, Optional<Orientation> orientation);

    public ArrayList<Ship> buildShips(ArrayList<ShipPattern> shipPatterns);
}

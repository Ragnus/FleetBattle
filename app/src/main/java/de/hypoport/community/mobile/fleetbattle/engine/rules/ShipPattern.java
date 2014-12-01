package de.hypoport.community.mobile.fleetbattle.engine.rules;

import com.google.common.base.Optional;

import de.hypoport.community.mobile.fleetbattle.engine.Orientation;

import static de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType.BATTLESHIP;
import static de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType.CRUISER;
import static de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType.DESTROYER;
import static de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType.SUBMARINE;

/**
 * Created by Ragnus on 20.11.2014.
 */
public class ShipPattern {

    public final Optional<Orientation> orientation;
    public final ShipType type;
    public final int numberOfShips;

    public ShipPattern(ShipType type, Orientation orientation, int numberOfShips) {
        this.type = type;
        this.numberOfShips = numberOfShips;
        this.orientation = Optional.fromNullable(orientation);
    }

    public ShipPattern(ShipType type, int numberOfShips) {
        this(type, null, numberOfShips);
    }

    public static ShipPattern battleship(int numberOfShips) {
        return new ShipPattern(BATTLESHIP, numberOfShips);
    }

    public static ShipPattern cruiser(int numberOfShips) {
        return new ShipPattern(CRUISER, numberOfShips);
    }

    public static ShipPattern destroyer(int numberOfShips) {
        return new ShipPattern(DESTROYER, numberOfShips);
    }

    public static ShipPattern submarine(int numberOfShips) {
        return new ShipPattern(SUBMARINE, numberOfShips);
    }
}

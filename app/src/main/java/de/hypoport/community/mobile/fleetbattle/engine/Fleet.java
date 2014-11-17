package de.hypoport.community.mobile.fleetbattle.engine;

import java.util.ArrayList;

/**
 * Created by Ragnus on 17.11.2014.
 * Besteht aus Schiffen
 */
public class Fleet {

    private final ArrayList<Ship> ships;

    public Fleet() {
        ships = new ArrayList<Ship>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public boolean isDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isSunken()) return false;
        }
        return true;
    }
}

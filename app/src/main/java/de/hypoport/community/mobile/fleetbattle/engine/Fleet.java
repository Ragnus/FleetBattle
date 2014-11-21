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

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public boolean isEmpty() {
        return ships.isEmpty();
    }

    public boolean isDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isSunken()) return false;
        }
        return true;
    }

    public boolean shootAt(int x, int y, int roundNr) {

        for (Ship ship : getShips()) {
            if (ship.setHit(x, y, roundNr)) return true;
        }

        return false;
    }

    public boolean isReadyToFight() {
        if (isEmpty()) return false;
        for (Ship ship : getShips()) {
            if (ship.isAtHarbour()) return false;
        }
        return true;
    }

    public boolean isShipAtLocation(int x, int y) {
        for (Ship ship : getShips()) {
            if (ship.isAtLocation(x, y)) return true;
        }
        return false;

    }
}

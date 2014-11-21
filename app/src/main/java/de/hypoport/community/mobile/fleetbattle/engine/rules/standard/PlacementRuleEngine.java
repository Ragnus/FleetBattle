package de.hypoport.community.mobile.fleetbattle.engine.rules.standard;

import java.util.ArrayList;

import de.hypoport.community.mobile.fleetbattle.engine.Fleet;
import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Segment;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;
import de.hypoport.community.mobile.fleetbattle.engine.rules.PlacementRules;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;

/**
 * Created by Ragnus on 19.11.2014.
 */
public class PlacementRuleEngine implements PlacementRules {

    private final int minX = 0;
    private final int minY = 0;
    private final int maxX = 9;
    private final int maxY = 9;

    private final int minShipSize = 2;
    private final int maxShipSize = 5;

    private final Fleet fleet;

    public PlacementRuleEngine(Fleet fleet) {
        this.fleet = fleet;
    }

    private int getMaxAmountOfShipSize(int size) {
        int amount;
        switch (size) {
            case 2:
                amount = 4;
                break;
            case 3:
                amount = 3;
                break;
            case 4:
                amount = 2;
                break;
            case 5:
                amount = 1;
                break;
            default:
                throw new RuntimeException(String.format("Schiffe dürfen nur %d - %d Größe haben. Angefordert wurde Größe %d", getMinShipSize(), getMaxShipSize(), size));

        }
        return amount;
    }

    @Override
    public int getMaxX() {
        return maxX;
    }

    @Override
    public int getMaxY() {
        return maxY;
    }

    @Override
    public int getMinX() {
        return minX;
    }

    @Override
    public int getMinY() {
        return minY;
    }

    private Fleet getFleet() {
        return fleet;
    }

    @Override
    public int getMinShipSize() {
        return minShipSize;
    }

    @Override
    public int getMaxShipSize() {
        return maxShipSize;
    }

    @Override
    public ArrayList<ShipPattern> getShipPatterns() {
        ArrayList<ShipPattern> shipPatterns = new ArrayList<ShipPattern>();
        shipPatterns.add(new ShipPattern(5, Orientation.HORIZONTAL, getMaxAmountOfShipSize(5)));
        shipPatterns.add(new ShipPattern(4, Orientation.HORIZONTAL, getMaxAmountOfShipSize(4)));
        shipPatterns.add(new ShipPattern(3, Orientation.HORIZONTAL, getMaxAmountOfShipSize(3)));
        shipPatterns.add(new ShipPattern(2, Orientation.HORIZONTAL, getMaxAmountOfShipSize(2)));
        return shipPatterns;
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < getMinX() || x > getMaxX() || y < getMinY() || y > getMaxY());
    }

    @Override
    public boolean isPositionGoodForPlacing(Ship ship) {
        final Fleet fleet = getFleet();

        for (Segment segment : ship.getSegments()) {
            final int x = segment.getX();
            final int y = segment.getY();
            if (isOutOfBounds(x, y)) return false;

            if (fleet.isShipAtLocation(x, y)) return false;
            if (fleet.isShipAtLocation(x, y - 1)) return false;
            if (fleet.isShipAtLocation(x, y + 1)) return false;

            if (fleet.isShipAtLocation(x - 1, y)) return false;
            if (fleet.isShipAtLocation(x - 1, y - 1)) return false;
            if (fleet.isShipAtLocation(x - 1, y + 1)) return false;

            if (fleet.isShipAtLocation(x + 1, y)) return false;
            if (fleet.isShipAtLocation(x + 1, y - 1)) return false;
            if (fleet.isShipAtLocation(x + 1, y + 1)) return false;
        }

        return true;
    }

    @Override
    public boolean isShipFittingToFleet(Ship ship) {
        int numberOfShipsSameSize = 0;
        final int size = ship.getSize();
        for (Ship compare : getFleet().getShips()) {
            if (compare.getSize() == size) numberOfShipsSameSize++;
        }
        return numberOfShipsSameSize < getMaxAmountOfShipSize(size);
    }
}

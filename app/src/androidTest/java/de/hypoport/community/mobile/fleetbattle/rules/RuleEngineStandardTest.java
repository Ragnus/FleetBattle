package de.hypoport.community.mobile.fleetbattle.rules;

import junit.framework.TestCase;

import java.util.ArrayList;

import de.hypoport.community.mobile.fleetbattle.engine.Fleet;
import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;
import de.hypoport.community.mobile.fleetbattle.engine.rules.PlacementRules;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.engine.rules.Shipyard;
import de.hypoport.community.mobile.fleetbattle.engine.rules.standard.PlacementRuleEngine;
import de.hypoport.community.mobile.fleetbattle.engine.rules.standard.ShipFactory;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class RuleEngineStandardTest extends TestCase {

    public static final String TAG = RuleEngineStandardTest.class.getSimpleName();

    Fleet fleet;
    Shipyard shipyard;
    PlacementRules placementRules;
    ArrayList<Ship> ships;

    @Override
    public void setUp() throws Exception {
        fleet = new Fleet();
        shipyard = new ShipFactory();
        placementRules = new PlacementRuleEngine(fleet);
        ArrayList<ShipPattern> shipPatterns = placementRules.getShipPatterns();
        ships = shipyard.buildShips(shipPatterns);
    }

    public void testBaseValues() throws Exception {
        assertEquals(placementRules.getMinX(), 0);
        assertEquals(placementRules.getMinY(), 0);
        assertEquals(placementRules.getMaxX(), 9);
        assertEquals(placementRules.getMaxY(), 9);
        assertEquals(placementRules.getMaxShipSize(), 5);
        assertEquals(placementRules.getMinShipSize(), 2);
        assertEquals(placementRules.getShipPatterns().size(), 4);
    }

    public void testShipNumbers() throws Exception {
        try {
            placementRules.isShipFittingToFleet(new Ship(1, Orientation.HORIZONTAL));
            fail();
        } catch (RuntimeException re) {
            // success
        }
        try {
            placementRules.isShipFittingToFleet(new Ship(6, Orientation.HORIZONTAL));
            fail();
        } catch (RuntimeException re) {
            // success
        }

        for (Ship ship : ships) {
            assertTrue(placementRules.isShipFittingToFleet(ship));
            fleet.addShip(ship);
        }

        for (ShipPattern shipPattern : placementRules.getShipPatterns()) {
            Ship ship = shipyard.buildShip(shipPattern.size, shipPattern.orientation);
            assertFalse(String.format("One ship too many for fleet mismatch. Ship size %d", ship.getSize()), placementRules.isShipFittingToFleet(ship));
        }


    }

    public void testBorders() throws Exception {
        Ship battleShip = ships.get(0);

        battleShip.moveToPosition(0, -1);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));
        battleShip.flip().moveToPosition(-1, 0);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));

        battleShip.moveToPosition(0, 6);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));
        battleShip.flip().moveToPosition(-1, 9);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));

        battleShip.moveToPosition(6, 9);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));
        battleShip.flip().moveToPosition(9, 6);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));

        battleShip.moveToPosition(9, -1);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));
        battleShip.flip().moveToPosition(6, 0);
        assertFalse(placementRules.isPositionGoodForPlacing(battleShip));
    }

    public void testPlacement() throws Exception {
        /*
              0   1   2   3   4   5   6   7   8   9
            +---+---+---+---+---+---+---+---+---+---+
          0 |   | ! |!x | x | x |!x | ! |   | 2 |   |        x = not allowed test positions on empty field
            +---+---+---+---+---+---+---+---+   +---+        ! = try to position there and fail
          1 | 4 |   | x | 2   2 |!x | * |   | 2 |   |        * = position there successfully
            +   +---+---+---+---+---+---+---+---+---+
          2 | 4 |   | x | x | x | x | * |   |   |   |
            +   +---+---+---+---+---+---+---+---+---+
          3 | 4 |   |   |   |   |   |   |   | ! |   |
            +   +---+---+---+---+---+---+---+---+---+
          4 | 4 |   | 5   5   5   5   5 |   |!x | x |
            +---+---+---+---+---+---+---+---+---+---+
          5 |   |   |   |   |   |   |   |   | x | 3 |
            +---+---+---+---+---+---+---+---+---+   +
          6 |   |   |   | 3   3   3 |   |   | x | 3 |
            +---+---+---+---+---+---+---+---+---+   +
          7 |   | 3 |   |   |   |   |   |   | x | 3 |
            +---+   +---+---+---+---+---+---+---+---+
          8 |   | 3 |   |   | 2 |   |   |   | x | x |
            +---+   +---+---+   +---+---+---+---+---+
          9 |   | 3 |   |   | 2 |   | 4   4   4   4 |
            +---+---+---+---+---+---+---+---+---+---+
              0   1   2   3   4   5   6   7   8   9
        */

        ships.get(0).moveToPosition(2, 4); // 5er
        ships.get(1).flip().moveToPosition(0, 1); // 4er
        ships.get(2).moveToPosition(6, 9); // 4er
        ships.get(3).flip().moveToPosition(1, 7); // 3er
        ships.get(4).moveToPosition(3, 6); // 3er
        ships.get(5).flip().moveToPosition(9, 5); // 3er
        ships.get(6).moveToPosition(3, 1); // 2er
        ships.get(7).flip().moveToPosition(8, 0); // 2er
        ships.get(8).flip().moveToPosition(4, 8); // 2er

        for (int i = 0; i < ships.size() - 1; i++) {

            final Ship ship = ships.get(i);
            assertTrue(String.format("Ship nr. %d fits to fleet", i), placementRules.isShipFittingToFleet(ship));
            assertTrue(String.format("Ship nr. %d is well positioned", i), placementRules.isPositionGoodForPlacing(ship));
            fleet.addShip(ship);
        }

        Ship submarine = ships.get(9); // 2er
        submarine.moveToPosition(1, 0);
        assertFalse(placementRules.isPositionGoodForPlacing(submarine));
        submarine.moveToPosition(5, 0);
        assertFalse(placementRules.isPositionGoodForPlacing(submarine));
        assertFalse(placementRules.isPositionGoodForPlacing(submarine.flip()));
        submarine.moveToPosition(8, 3);
        assertFalse(placementRules.isPositionGoodForPlacing(submarine));
        submarine.moveToPosition(6, 1);
        assertTrue(placementRules.isPositionGoodForPlacing(submarine));
        assertTrue(placementRules.isShipFittingToFleet(submarine));

    }
}
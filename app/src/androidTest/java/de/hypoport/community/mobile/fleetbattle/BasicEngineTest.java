package de.hypoport.community.mobile.fleetbattle;

import junit.framework.TestCase;

import de.hypoport.community.mobile.fleetbattle.engine.BattleField;
import de.hypoport.community.mobile.fleetbattle.engine.Fleet;
import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Segment;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class BasicEngineTest extends TestCase {

    public void testSegment() throws Exception {
        Segment segment = new Segment();
        assertEquals("Position x mismatch", segment.getX(), Segment.NO_POSITION);
        assertEquals("Position y mismatch", segment.getY(), Segment.NO_POSITION);
        assertFalse(segment.isPositioned());
        assertEquals("Segment not hit mismatch", segment.getHitAtRoundNr(), Segment.NOT_HIT_ROUND_NR);
        assertFalse(segment.isHit());

        segment = new Segment(1, 2);
        assertEquals("Position x mismatch", segment.getX(), 1);
        assertEquals("Position y mismatch", segment.getY(), 2);
        assertTrue(segment.isPositioned());
        assertEquals("Segment not hit mismatch", segment.getHitAtRoundNr(), Segment.NOT_HIT_ROUND_NR);

        segment.setXbyOffset(1);
        segment.setYbyOffset(1);
        assertEquals("Position x mismatch", segment.getX(), 2);
        assertEquals("Position y mismatch", segment.getY(), 3);

        segment.setPositionByOffset(-1, -1);
        assertEquals("Position x mismatch", segment.getX(), 1);
        assertEquals("Position y mismatch", segment.getY(), 2);

        segment.setPositionToUnset();
        assertEquals("Position x not unset", segment.getX(), Segment.NO_POSITION);
        assertEquals("Position y not unset", segment.getY(), Segment.NO_POSITION);
        assertFalse(segment.isPositioned());

        segment = new Segment(1, 2);
        segment.setHitAtRoundNr(5);
        assertTrue(segment.isHit());
        assertEquals("HitAtRoundNr mismatch", segment.getHitAtRoundNr(), 5);

        try {
            segment.setHitAtRoundNr(Segment.NOT_HIT_ROUND_NR);
            fail("Missing HitResetException");
        } catch (RuntimeException e) {
            // success
        }

        segment.setHitAtRoundNr(1);
        segment.clearHitRoundNr();
        assertEquals("Cleared Hit mismatch", segment.getHitAtRoundNr(), Segment.NOT_HIT_ROUND_NR);
    }

    public void testShipConstruction() throws Exception {
        Ship ship;
        try {
            ship = new Ship(0, Orientation.HORIZONTAL);
            fail("Shipsize min Exception missing");
        } catch (RuntimeException e) {
            // success
        }

        try {
            ship = new Ship(1, null);
            fail("Orientation not null Exception missing");
        } catch (RuntimeException e) {
            // success
        }

        ship = new Ship(2, Orientation.VERTICAL);
        assertEquals(ship.getSize(), 2);
        assertEquals(ship.getSize(), ship.getSegments().size());
        assertEquals(Orientation.VERTICAL, ship.getOrientation());

        for (Segment segment : ship.getSegments()) {
            assertNotNull(segment);
            if (segment != null) {
                assertFalse(segment.isHit());
                assertFalse(segment.isPositioned());
            }
        }

        assertFalse(ship.isSunken());

    }

    public void testShipFunctions() throws Exception {
        Ship ship = new Ship(1, Orientation.HORIZONTAL);
        assertEquals(ship.getSize(), 1);
        assertEquals(Orientation.HORIZONTAL, ship.getOrientation());

        ship.flip();
        assertEquals(Orientation.VERTICAL, ship.getOrientation());

        ship.flip();
        assertEquals(Orientation.HORIZONTAL, ship.getOrientation());

        // todo Test Positioning
        ship = new Ship(2, Orientation.HORIZONTAL);
        assertEquals(ship.getSize(), 2);
        assertTrue(ship.isAtHarbour());
        assertFalse(ship.isOnBattleField());

        ship.moveToPosition(1, 1);
        assertTrue(ship.isOnBattleField());

        int[] xCoordinates = {1, 2};
        int[] yCoordinates = {1, 1};

        for (int i = 0; i < ship.getSegments().size(); i++) {
            Segment segment = ship.getSegments().get(i);
            assertEquals("Position x not equal", xCoordinates[i], segment.getX());
            assertEquals("Position y not equal", yCoordinates[i], segment.getY());
        }

        ship.flip();
        for (int i = 0; i < ship.getSegments().size(); i++) {
            Segment segment = ship.getSegments().get(i);
            assertEquals("Position x not equal", yCoordinates[i], segment.getX());
            assertEquals("Position y not equal", xCoordinates[i], segment.getY());
        }

        ship.flip();
        for (int i = 0; i < ship.getSegments().size(); i++) {
            Segment segment = ship.getSegments().get(i);
            assertEquals("Position x not equal", xCoordinates[i], segment.getX());
            assertEquals("Position y not equal", yCoordinates[i], segment.getY());
        }

        ship.returnToHarbour();
        assertTrue(ship.isAtHarbour());

        ship = new Ship(2, Orientation.HORIZONTAL);
        ship.moveToPosition(1, 1);
        assertFalse(ship.isSunken());

        boolean isHit = ship.setHit(1, 1, 1);
        assertTrue(isHit);
        assertFalse(ship.isSunken());

        isHit = ship.setHit(1, 2, 2);
        assertFalse(isHit);
        assertFalse(ship.isSunken());

        isHit = ship.setHit(2, 1, 3);
        assertTrue(isHit);
        assertTrue(ship.isSunken());
    }

    public void testFleet() throws Exception {
        // todo Test

        Fleet fleet = new Fleet();

        assertTrue(fleet.isEmpty());
        assertTrue(fleet.isDestroyed());
        assertFalse(fleet.isReadyToFight());

        Ship ship = new Ship(1, Orientation.HORIZONTAL);
        fleet.addShip(ship);

        ship.moveToPosition(1, 1);

        assertFalse(fleet.isEmpty());
        assertFalse(fleet.isDestroyed());
        assertTrue(fleet.isReadyToFight());

        fleet.shootAt(1, 1, 1);
        assertFalse(fleet.isEmpty());
        assertTrue(fleet.isDestroyed());

        ship = new Ship(5, Orientation.HORIZONTAL);
        fleet.addShip(ship);
        assertFalse(fleet.isReadyToFight());

        ship.moveToPosition(4, 3);
        assertTrue(fleet.isReadyToFight());
    }

    public void testBattleField() throws Exception {

        Fleet fleet = new Fleet();
        BattleField battleField = new BattleField(fleet);

        Ship ship = new Ship(1, Orientation.HORIZONTAL);
        fleet.addShip(ship);
        ship.moveToPosition(1, 1);

        ship = new Ship(2, Orientation.VERTICAL);
        fleet.addShip(ship);
        ship.moveToPosition(3, 3);

        try {
            battleField.shootAt(1, 1, 0);
            fail("Min roundNr for shooting exception not thrown");
        } catch (RuntimeException e) {
            //success
        }


        assertFalse(battleField.shootAt(2, 2, 1));
        assertFalse(battleField.getFleet().isDestroyed());

        assertTrue(battleField.shootAt(1, 1, 2));
        assertFalse(battleField.getFleet().isDestroyed());

        assertTrue(battleField.shootAt(3, 3, 3));
        assertFalse(battleField.getFleet().isDestroyed());

        assertTrue(battleField.shootAt(3, 4, 4));
        assertTrue(battleField.getFleet().isDestroyed());
    }

}
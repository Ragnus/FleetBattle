package de.hypoport.community.mobile.fleetbattle;

import junit.framework.TestCase;

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

        for (Segment segment : ship.getSegments()){
            assertNotNull(segment);
            if (segment!=null){
                assertFalse(segment.isHit());
                assertFalse(segment.isPositioned());
            }
        }

        assertFalse(ship.isSunken());

    }

    public void testShipFunctions() throws Exception{
        Ship ship = new Ship(1, Orientation.HORIZONTAL);
        assertEquals(ship.getSize(), 1);
        assertEquals(Orientation.HORIZONTAL, ship.getOrientation());

        ship.flip();
        assertEquals(Orientation.VERTICAL, ship.getOrientation());

        ship.flip();
        assertEquals(Orientation.HORIZONTAL, ship.getOrientation());

        // todo Test Positioning
        assertTrue(false);
    }

    public void testFleet() throws Exception {
        // todo Test
        assertTrue(false);

    }

    public void testBattleField() throws Exception {
        // todo Test
        assertTrue(false);

    }

}
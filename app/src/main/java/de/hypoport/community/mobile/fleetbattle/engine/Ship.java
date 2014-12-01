package de.hypoport.community.mobile.fleetbattle.engine;

import com.google.common.base.Optional;

import java.util.ArrayList;

import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType;

/**
 * Created by Ragnus on 17.11.2014.
 */
public class Ship {

    private final ArrayList<Segment> segments = new ArrayList<>();
    private Orientation orientation;
    private final ShipType type;

    public Ship(ShipType type, Optional<Orientation> orientation) {
        this.type = type;

        if (orientation.isPresent()) {
            this.orientation = orientation.get();
        } else {
            throw new RuntimeException("Orientation is not set");
        }

        for (int i = 0; i < type.size; i++) {
            segments.add(new Segment());
        }
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public int getSize() {
        return type.size;
    }

    public ShipType getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Ship flip() {
        switch (orientation) {
            case HORIZONTAL:
                orientation = Orientation.VERTICAL;
                break;
            case VERTICAL:
                orientation = Orientation.HORIZONTAL;
                break;
            default:
                throw new RuntimeException("Orientation not implemented : " + orientation);
        }
        flipSegmentsAroundCenter();
        return this;
    }

    private void flipSegmentsAroundCenter() {
        int center = (int) Math.floor((1 + segments.size()) / 2) - 1;
        flipSegmentsAroundSegment(center);
    }

    private void flipSegmentsAroundSegment(int segmentNr) {

        for (int i = 0; i < getSegments().size(); i++) {
            Segment segment = getSegments().get(i);

            if (i != segmentNr && segment.isPositioned()) {
                int offset = segmentNr - i;
                int offsetX, offsetY;
                switch (orientation) {
                    case HORIZONTAL:
                        offsetX = -offset;
                        offsetY = offset;
                        break;
                    case VERTICAL:
                        offsetX = offset;
                        offsetY = -offset;
                        break;
                    default:
                        throw new RuntimeException("Orientation not implemented : " + orientation);
                }
                segment.setPositionByOffset(offsetX, offsetY);
            }

        }

    }

    public boolean isSunken() {
        for (Segment segment : getSegments()) {
            if (!segment.isHit()) return false;
        }
        return true;
    }

    public void moveToPosition(int x, int y) {
        int xFactor, yFactor;
        switch (orientation) {
            case HORIZONTAL:
                xFactor = 1;
                yFactor = 0;
                break;
            case VERTICAL:
                xFactor = 0;
                yFactor = 1;
                break;
            default:
                throw new RuntimeException("Orientation not implemented : " + orientation);
        }

        for (int i = 0; i < getSegments().size(); i++) {
            Segment segment = getSegments().get(i);
            segment.setX(x + (i * xFactor));
            segment.setY(y + (i * yFactor));
        }

    }

    public void returnToHarbour() {
        for (Segment segment : getSegments()) {
            segment.setPositionToUnset();
        }
    }

    public boolean isAtHarbour() {
        for (Segment segment : getSegments()) {
            if (segment.isPositioned()) return false;
        }
        return true;
    }

    public boolean isOnBattleField() {
        return !isAtHarbour();
    }

    private boolean isSegmentAtLocation(int x, int y, Segment segment) {
        int xPos = segment.getX();
        int yPos = segment.getY();
        boolean isNotHit = !segment.isHit();
        return (x == xPos && y == yPos && isNotHit);
    }

    public boolean isAtLocation(int x, int y) {
        for (Segment segment : getSegments()) {
            if (isSegmentAtLocation(x, y, segment)) return true;
        }
        return false;

    }

    public boolean setHit(int x, int y, int roundNr) {
        for (Segment segment : getSegments()) {
            if (isSegmentAtLocation(x, y, segment)) {
                segment.setHitAtRoundNr(roundNr);
                return true;
            }
        }
        return false;
    }
}

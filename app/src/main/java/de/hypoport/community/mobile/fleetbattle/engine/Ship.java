package de.hypoport.community.mobile.fleetbattle.engine;

import java.util.ArrayList;

/**
 * Created by Ragnus on 17.11.2014.
 */
public class Ship {

    private final ArrayList<Segment> segments = new ArrayList<Segment>();
    private Orientation orientation;

    public Ship(int size, Orientation orientation) {

        if (size < 1) throw new RuntimeException("Die Schiffsgröße muss mindestens 1 sein");
        if (orientation == null) throw new RuntimeException("Orientierung ist NULL");
        this.orientation = orientation;

        for (int i = 0; i < size; i++) segments.add(new Segment());
    }

    public ArrayList<Segment> getSegments(){
        return segments;
    }

    public int getSize(){
        return segments.size();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void flip() {
        flipSegmentsAroundCenter();
        switch (orientation) {
            case HORIZONTAL:
                orientation = Orientation.VERTICAL;
                break;
            case VERTICAL:
                orientation = Orientation.HORIZONTAL;
                break;
            default:
                throw new RuntimeException("Orientierung nicht implementiert : " + orientation);
        }
    }

    private void flipSegmentsAroundCenter() {
        int center = (int) Math.floor((1 + segments.size()) / 2) - 1;
        flipSegmentsAroundSegment(center);
    }

    private void flipSegmentsAroundSegment(int segmentNr) {

        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);

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
                        throw new RuntimeException("Orientierung nicht implementiert : " + orientation);
                }
                segment.setPositionByOffset(offsetX, offsetY);
            }

        }

    }

    public boolean isSunken() {
        for (Segment segment : segments) {
            if (!segment.isHit()) return false;
        }
        return true;
    }
}

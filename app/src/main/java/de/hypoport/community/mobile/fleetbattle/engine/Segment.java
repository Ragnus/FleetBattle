package de.hypoport.community.mobile.fleetbattle.engine;

/**
 * Created by Ragnus on 17.11.2014.
 * Segment-Klasse für Treffer und Positionierung
 */
public class Segment {
    public static final int NO_POSITION = -1;
    public static final int NOT_HIT_ROUND_NR = -1;

    private int x, y;
    private int hitAtRoundNr;

    public Segment() {
        this(NO_POSITION, NO_POSITION, NOT_HIT_ROUND_NR);
    }

    public Segment(int x, int y) {
        this(x, y, NOT_HIT_ROUND_NR);
    }

    public Segment(int x, int y, int hitAtRoundNr) {
        this.x = x;
        this.y = y;
        this.hitAtRoundNr = hitAtRoundNr;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHitAtRoundNr() {
        return hitAtRoundNr;
    }

    public void setHitAtRoundNr(int hitAtRoundNr) {
        if (hitAtRoundNr == NOT_HIT_ROUND_NR)
            throw new RuntimeException("Treffer darf nicht in der RundeNr. " + NOT_HIT_ROUND_NR + " erfolgen!");
        this.hitAtRoundNr = hitAtRoundNr;
    }

    public void clearHitRoundNr(){
        this.hitAtRoundNr = NOT_HIT_ROUND_NR;
    }

    public boolean isPositioned() {
        return x != NO_POSITION && y != NO_POSITION;
    }

    public void setXbyOffset(int offset) {
        setX(getX() + offset);
    }

    public void setYbyOffset(int offset) {
        setY(getY() + offset);
    }

    public void setPositionByOffset(int offsetX, int offsetY) {
        setXbyOffset(offsetX);
        setYbyOffset(offsetY);
    }

    public void setPositionToUnset() {
        setX(NO_POSITION);
        setY(NO_POSITION);
    }

    public boolean isHit() {
        return hitAtRoundNr != NOT_HIT_ROUND_NR;
    }

}

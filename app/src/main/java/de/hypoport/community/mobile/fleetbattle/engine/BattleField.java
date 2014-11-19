package de.hypoport.community.mobile.fleetbattle.engine;

import java.util.ArrayList;

/**
 * Created by Ragnus on 17.11.2014.
 * Schlachtfeld zum Setzen und Kämpfen.
 */
public class BattleField {
    public static final int MIN_ROUND_NR_TO_SHOOT = 1;
    private final Fleet fleet;
    private final ArrayList<Segment> shots;

    public BattleField(Fleet fleet) {
        this.fleet = fleet;
        this.shots = new ArrayList<Segment>();
    }

    public Fleet getFleet() {
        return fleet;
    }

    public boolean shootAt(int x, int y, int roundNr) {
        if (roundNr < MIN_ROUND_NR_TO_SHOOT)
            throw new RuntimeException("Treffer können erst ab Runde " + MIN_ROUND_NR_TO_SHOOT + " erfolgen");
        shots.add(new Segment(x, y, roundNr));
        return getFleet().shootAt(x, y, roundNr);
    }

    public ArrayList<Segment> getShots() {
        return shots;
    }
}

package de.hypoport.community.mobile.fleetbattle.engine;

import java.util.ArrayList;

/**
 * Created by Ragnus on 17.11.2014.
 * Schlachtfeld zum Setzen und Kämpfen.
 */
public class BattleField {

    private final Fleet fleet;
    private final ArrayList<Segment> shots;

    public BattleField(Fleet fleet) {
        this.fleet = fleet;
        this.shots = new ArrayList<Segment>();
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void shootAt(int x, int y, int roundNr){
        shots.add(new Segment(x,y,roundNr));
    }

    public ArrayList<Segment> getShots() {
        return shots;
    }
}

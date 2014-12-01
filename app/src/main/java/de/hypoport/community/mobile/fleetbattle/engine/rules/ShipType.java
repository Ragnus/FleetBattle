package de.hypoport.community.mobile.fleetbattle.engine.rules;

/**
 * Created by bozan on 01.12.2014.
 *
 * Definition of the type and the size of the ship. The id is for the textual representation in the ui.
 *
 */
public enum ShipType {
    BATTLESHIP(5, "battleship"),
    DESTROYER(4, "destroyer"),
    CRUISER(3, "cruiser"),
    SUBMARINE(2, "submarine");

    public final int size;
    private String id;

    ShipType(int size, String id) {
        this.size = size;
        this.id = id;
    }
}

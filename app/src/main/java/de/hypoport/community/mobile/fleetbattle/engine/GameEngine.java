package de.hypoport.community.mobile.fleetbattle.engine;

import java.util.ArrayList;
import java.util.List;

import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType;
import de.hypoport.community.mobile.fleetbattle.ui.views.Field;

import static java.util.Arrays.asList;

/**
 * Created by Matthias.Piehl on 17.12.2014.
 */
public class GameEngine {
    private static GameEngine instance = null;

    private ArrayList<Ship> shipList = null;

    private GameEngine() {
        shipList = new ArrayList<>(asList(
                createDropShip(Orientation.VERTICAL, ShipType.DESTROYER, new Field(4, 5)),
                createDropShip(Orientation.HORIZONTAL, ShipType.BATTLESHIP, new Field(3, 8))));
    }

    public static GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();
        return instance;
    }

    public List<Ship> getShipList(Player player) {
        //todo später lade die Liste der Schiffe, in Abhängigkeit zum Spieler
        return shipList;
    }

    public Ship createDropShip(Orientation orientation, ShipType type, Field center) {
        Ship ship = new Ship(type, orientation);
        ship.getSegments().clear();
        for (int i = 0; i < type.size; i++) {
            if (orientation == Orientation.HORIZONTAL) {
                ship.getSegments().add(i, new Segment(center.x - type.size / 2 + i, center.y));
            } else {
                ship.getSegments().add(i, new Segment(center.x, center.y - type.size / 2 + i));
            }
        }
        return ship;
    }
}

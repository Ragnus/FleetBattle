package de.hypoport.community.mobile.fleetbattle.ui.views;

/**
 * Created by Matthias.Piehl on 17.12.2014.
 */
public class Field {


        public final int x;
        public final int y;

        public Field(int x, int y) {
            this.x = x;
            this.y = y;
        }



        @Override
        public String toString() {
            return "X:" + x + "/Y:" + y;
        }

}

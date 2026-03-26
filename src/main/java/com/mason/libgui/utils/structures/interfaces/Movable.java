package com.mason.libgui.utils.structures.interfaces;

import com.mason.libgui.utils.structures.Coord;

public interface Movable extends CoordQuery{

    void setCoord(Coord c);


    static Movable buildDefaultMovable(Coord initialCoord){
        return new Movable(){

            private Coord coord = initialCoord;

            @Override
            public void setCoord(Coord c){
                coord = c;
            }

            @Override
            public Coord getCoord(){
                return coord;
            }
        };
    }

}

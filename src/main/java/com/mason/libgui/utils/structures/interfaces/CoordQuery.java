package com.mason.libgui.utils.structures.interfaces;

import com.mason.libgui.utils.structures.Coord;

public interface CoordQuery{

    Coord getCoord();


    default int x(){
        return getCoord().x();
    }

    default int y(){
        return getCoord().y();
    }

}

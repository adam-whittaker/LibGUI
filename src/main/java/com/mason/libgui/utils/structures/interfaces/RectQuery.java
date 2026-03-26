package com.mason.libgui.utils.structures.interfaces;

import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;

public interface RectQuery extends Boundable, CoordQuery{

    Size getSize();


    default int width(){
        return getSize().width();
    }

    default int height(){
        return getSize().height();
    }

    @Override
    default boolean withinBounds(Coord c){
        return c.x() >= x() && c.y() >= y() && c.x() < x()+width() && c.y() < y()+height();
    }

}

package com.mason.libgui.utils.structures;

public interface RectQuery extends Boundable{

    Coord getCoord();
    Size getSize();

    default int x(){
        return getCoord().x();
    }

    default int y(){
        return getCoord().y();
    }

    default int width(){
        return getSize().width();
    }

    default int height(){
        return getSize().height();
    }

}

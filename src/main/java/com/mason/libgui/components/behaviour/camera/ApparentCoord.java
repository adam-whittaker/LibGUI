package com.mason.libgui.components.behaviour.camera;

import com.mason.libstruct.geo.Coord;

public record ApparentCoord(double x, double y){

    public Coord asCoord(){
        return new Coord((int) x, (int) y);
    }

}

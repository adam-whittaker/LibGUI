package com.mason.libgui.core.component.hitbox;

import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.geo.Size;

public class BasicHitboxRect implements HitboxRect{


    private Coord topLeft;
    private Size size;


    public BasicHitboxRect(Coord topLeft, Size size){
        this.topLeft = topLeft;
        this.size = size;
    }


    @Override
    public void setCoord(Coord c){
        topLeft = c;
    }

    @Override
    public Coord getCoord(){
        return topLeft;
    }

    @Override
    public Size getSize(){
        return size;
    }

    public void setSize(Size size){
        this.size = size;
    }

}

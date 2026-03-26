package com.mason.libgui.core.component.hitbox;

import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;

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

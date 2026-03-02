package com.mason.libgui.core.component;

import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;

public abstract class AbstractUIComponent implements UIComponent{


    private final HitboxRect hitbox;


    public AbstractUIComponent(HitboxRect hitbox){
        this.hitbox = hitbox;
    }


    @Override
    public boolean withinBounds(Coord c){
        return hitbox.withinBounds(c);
    }

    @Override
    public void setCoord(Coord c){
        hitbox.setCoord(c);
    }

    @Override
    public Coord getCoord(){
        return hitbox.getCoord();
    }

    public Size getSize(){
        return hitbox.getSize();
    }

}

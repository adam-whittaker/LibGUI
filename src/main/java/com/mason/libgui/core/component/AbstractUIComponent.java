package com.mason.libgui.core.component;

import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.interfaces.RectQuery;
import com.mason.libstruct.geo.Size;

public abstract class AbstractUIComponent implements UIComponent, RectQuery{


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

    @Override
    public Size getSize(){
        return hitbox.getSize();
    }

}

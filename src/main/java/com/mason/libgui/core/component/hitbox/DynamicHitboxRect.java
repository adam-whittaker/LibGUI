package com.mason.libgui.core.component.hitbox;

import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.geo.Size;

import java.util.function.Supplier;

public class DynamicHitboxRect extends DynamicRect implements HitboxRect{

    public DynamicHitboxRect(Supplier<Coord> coord, Supplier<Size> size){
        super(coord, size);
    }

    public static DynamicHitboxRect buildWithStaticCoord(Coord coord, Supplier<Size> size){
        return new DynamicHitboxRect(() -> coord, size);
    }


    @Override
    public void setCoord(Coord coord){
        throw new IllegalStateException("Shouldn't be setting coord of Dynamic Hitbox. The supplier is the single source of truth.");
    }

}

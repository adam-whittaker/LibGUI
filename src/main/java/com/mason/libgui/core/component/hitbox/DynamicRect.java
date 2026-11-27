package com.mason.libgui.core.component.hitbox;

import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.geo.Size;
import com.mason.libstruct.interfaces.RectQuery;

import java.util.function.Supplier;

public class DynamicRect implements RectQuery{


    private final Supplier<Coord> coord;
    private final Supplier<Size> size;


    public DynamicRect(Supplier<Coord> coord, Supplier<Size> size){
        this.coord = coord;
        this.size = size;
    }

    public static DynamicRect buildWithStaticCoord(Coord coord, Supplier<Size> size){
        return new DynamicRect(() -> coord, size);
    }


    @Override
    public Size getSize(){
        return size.get();
    }

    @Override
    public Coord getCoord(){
        return coord.get();
    }

}

package com.mason.libgui.components.panes.layout;

import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.interfaces.RectQuery;
import com.mason.libgui.utils.structures.Size;

import java.util.HashMap;
import java.util.Map;

final class Box implements RectQuery{


    private RectQuery bounds;
    private final Map<String, Box> subBoxes;


    Box(RectQuery bounds){
        this.bounds = bounds;
        subBoxes = new HashMap<>();
    }


    void verifyEmpty(){
        if(!subBoxes.isEmpty()){
            throw new IllegalStateException("Box must be empty!");
        }
    }

    void putSubBox(String address, RectQuery bounds){
        if(subBoxes.containsKey(address)){
            throw new IllegalStateException("Address: " + address + " already exists!");
        }
        subBoxes.put(address, new Box(bounds));
    }

    Box getSubBoxAtAddress(String address){
        if(!subBoxes.containsKey(address)){
            throw new IllegalArgumentException("Box does not contain address: " + address);
        }
        return subBoxes.get(address);
    }

    Coord centreSizeInBox(Size size){
        verifyFit(size);
        int x = bounds.x() + (bounds.width() - size.width())/2;
        int y = bounds.y() + (bounds.height() - size.height())/2;
        return new Coord(x, y);
    }

    void verifyFit(Size size){
        if(!bounds.getSize().fits(size)){
            throw new IllegalArgumentException("Given size: " + size + " does not fit in box of size " + bounds.getSize());
        }
    }

    void setBounds(RectQuery bounds){
        this.bounds = bounds;
    }


    @Override
    public boolean withinBounds(Coord c){
        return bounds.withinBounds(c);
    }

    @Override
    public Coord getCoord(){
        return bounds.getCoord();
    }

    @Override
    public Size getSize(){
        return bounds.getSize();
    }

}

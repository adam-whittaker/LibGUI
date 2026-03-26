package com.mason.libgui.components.sliders.sliderPositionState;

import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.interfaces.Movable;

public class BasicSliderPositionState implements SliderPositionState{


    private final IntRange range;
    private final Movable positionCoord;


    public BasicSliderPositionState(Movable positionCoord, IntRange range){
        this.positionCoord = positionCoord;
        this.range = range;
        verifyPositionCoordWithinRange(positionCoord.getCoord());
    }

    private void verifyPositionCoordWithinRange(Coord coord){
        range.verifyWithinRange(coord.x());
    }


    @Override
    public double getPosition(){
        return range.position(positionCoord.x());
    }

    @Override
    public void setPosition(double position){
        verifyWithinBounds(position);
        int x = range.fromPosition(position);
        int y = positionCoord.y();
        setCoord(new Coord(x, y));
    }

    private void verifyWithinBounds(double position){
        if(position < 0 || position > 1){
            throw new IllegalArgumentException("Position must be between 0 and 1");
        }
    }


    @Override
    public void setCoordWithClamp(Coord coord){
        int x = range.clamp(coord.x());
        setCoord(new Coord(x, coord.y()));
    }

    @Override
    public void setCoord(Coord coord){
        positionCoord.setCoord(coord);
    }

    @Override
    public Coord getCoord(){
        return positionCoord.getCoord();
    }

}

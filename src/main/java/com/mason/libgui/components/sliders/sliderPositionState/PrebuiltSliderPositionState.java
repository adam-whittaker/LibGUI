package com.mason.libgui.components.sliders.sliderPositionState;

import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.interfaces.Movable;

public class PrebuiltSliderPositionState implements SliderPositionState{


    private IntRange range;
    private final Movable positionCoord;


    public PrebuiltSliderPositionState(Movable positionCoord){
        this.positionCoord = positionCoord;
        this.range = new IntRange(-2, -1);
        setCoordWithClamp(positionCoord.getCoord());
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


    public SliderPositionStateFactory packageIntoFactory(double initialPosition, int sliderHandleWidth){
        return (railClamp) -> {
            int traversableWidth = railClamp.width() - sliderHandleWidth;
            setCoord(new Coord(railClamp.x() + (int)(traversableWidth * initialPosition), railClamp.y()));
            range = new IntRange(railClamp.x(), railClamp.x() + traversableWidth);
            return this;
        };
    }

}

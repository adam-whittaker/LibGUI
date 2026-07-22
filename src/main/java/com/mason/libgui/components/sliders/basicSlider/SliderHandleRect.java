package com.mason.libgui.components.sliders.basicSlider;

import com.mason.libgui.components.sliders.sliderPositionState.SliderPositionState;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.geo.Size;
import com.mason.libstruct.states.position.PositionState;

public class SliderHandleRect implements HitboxRect, PositionState{


    private final SliderPositionState positionState;
    private final Size size;


    SliderHandleRect(SliderSkeleton skeleton){
        this.positionState = skeleton.getSliderPositionState();
        this.size = skeleton.getSliderHandleSize();
    }


    @Override
    public double getPosition(){
        return positionState.getPosition();
    }

    @Override
    public void setPosition(double position){
        positionState.setPosition(position);
    }

    public void setCoordWithClamp(Coord coord){
        positionState.setCoordWithClamp(coord);
    }

    @Override
    public void setCoord(Coord coord){
        positionState.setCoord(coord);
    }

    @Override
    public Coord getCoord(){
        return positionState.getCoord();
    }

    @Override
    public Size getSize(){
        return size;
    }

}

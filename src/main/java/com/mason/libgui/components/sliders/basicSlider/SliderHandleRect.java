package com.mason.libgui.components.sliders.basicSlider;

import com.mason.libgui.components.sliders.sliderPositionState.SliderPositionState;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;
import com.mason.libgui.utils.structures.states.position.PositionState;

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

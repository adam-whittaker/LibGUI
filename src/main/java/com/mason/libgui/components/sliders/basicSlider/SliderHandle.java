package com.mason.libgui.components.sliders.basicSlider;

import com.mason.libgui.components.behaviour.drag.ClampedDragBehaviour;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.interfaces.RectQuery;
import com.mason.libgui.utils.structures.states.position.PositionState;

public class SliderHandle extends ClampedDragBehaviour implements PositionState{


    private final SliderHandleRect rect;


    SliderHandle(SliderSkeleton skeleton){
        super(new SliderHandleRect(skeleton), skeleton.getRailHitbox());
        rect = (SliderHandleRect) getDragRegion();
    }


    protected RectQuery getBounds(){
        return rect;
    }

    protected void moveMiddleTo(int x){
        x -= rect.width()/2;
        rect.setCoordWithClamp(new Coord(x, rect.y()));
    }


    @Override
    public double getPosition(){
        return rect.getPosition();
    }

    @Override
    public void setPosition(double position){
        rect.setPosition(position);
    }

}

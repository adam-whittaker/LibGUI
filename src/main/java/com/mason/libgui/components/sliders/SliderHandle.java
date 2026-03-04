package com.mason.libgui.components.sliders;

import com.mason.libgui.components.behaviour.drag.ClampedDragBehaviour;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;

import java.util.HashSet;
import java.util.Set;

public class SliderHandle extends ClampedDragBehaviour{


    private final Set<SliderEventListener> listeners;
    private final SliderEvent.SliderEventConstructor eventConstructor;
    private final int minX;
    private final int maxX;

    private double position;


    public SliderHandle(SliderEvent.SliderEventConstructor eventConstructor, HitboxRect dragRect, RectQuery clampRect){
        super(dragRect, clampRect);
        listeners = new HashSet<>();
        this.eventConstructor = eventConstructor;
        minX = clampRect.x();
        maxX = clampRect.x() + clampRect.width() - dragRect.width();
        updatePositionToHitboxLocation();
    }

    private void updatePositionToHitboxLocation(){
        double hitboxX = getDragRegion().getCoord().x();
        position = (hitboxX-minX)/(maxX-minX);
    }

    private void updateHitboxLocationToPosition(){
        int hitboxX = (int)(((maxX-minX) * position) + minX);
        getDragRegion().setCoord(new Coord(hitboxX, getHitboxY()));
    }

    private int getHitboxY(){
        return getDragRegion().getCoord().y();
    }


    public double getPosition(){
        return position;
    }

    public void setPosition(double position){
        this.position = position;
        updateHitboxLocationToPosition();
    }

    protected RectQuery getBox(){
        return (RectQuery) getDragRegion();
    }

    protected void moveMiddleTo(int x){
        x -= getWidth()/2;
        if(x < minX){
            x = minX;
        }else if(x > maxX){
            x = maxX;
        }
        safelyMoveMiddleTo(x);
    }

    private int getWidth(){
        return ((RectQuery) getDragRegion()).width();
    }

    private void safelyMoveMiddleTo(int x){
        getDragRegion().setCoord(new Coord(x, getHitboxY()));
    }


    public void addSliderEventListener(SliderEventListener listener){
        listeners.add(listener);
    }

    public void removeSliderEventListener(SliderEventListener listener){
        listeners.remove(listener);
    }


    void dragIncrementEvent(){
        updatePositionToHitboxLocation();
        SliderEvent sliderEvent = eventConstructor.constructSliderEvent(SliderEventType.DRAGGING);
        listeners.forEach(listener -> listener.sliderDragged(sliderEvent));
    }

    @Override
    protected void onDragStart(MouseInputEvent event){
        super.onDragStart(event);
        SliderEvent sliderEvent = eventConstructor.constructSliderEvent(SliderEventType.GRABBED);
        listeners.forEach(listener -> listener.sliderGrabbed(sliderEvent));
    }

    @Override
    protected void onDragIncrement(MouseInputEvent event){
        super.onDragIncrement(event);
        dragIncrementEvent();
    }

    @Override
    protected void onDragRelease(MouseInputEvent event){
        super.onDragRelease(event);
        SliderEvent sliderEvent = eventConstructor.constructSliderEvent(SliderEventType.RELEASED);
        listeners.forEach(listener -> listener.sliderReleased(sliderEvent));
    }

}

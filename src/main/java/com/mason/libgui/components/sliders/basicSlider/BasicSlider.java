package com.mason.libgui.components.sliders.basicSlider;

import com.mason.libgui.components.ComponentIDRegister;
import com.mason.libgui.components.deco.SliderDeco;
import com.mason.libgui.components.sliders.Slider;
import com.mason.libgui.core.component.AbstractUIComponent;
import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

import java.awt.*;

public class BasicSlider extends AbstractUIComponent implements Slider, BoundedMouseInputListener{


    private final String name;
    private final int id;
    private final SliderDeco sliderDeco;
    private final RectQuery railBox;

    private final SliderHandle handle;


    public BasicSlider(SliderSkeleton skeleton){
        super(skeleton.getBoundary());
        this.name = skeleton.getName();
        this.id = ComponentIDRegister.registerComponent(this);
        this.sliderDeco = skeleton.getSliderDeco();
        this.railBox = skeleton.getRailHitbox();
        this.handle = new SliderHandle(skeleton);
    }


    @Override
    public int getID(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getPosition(){
        return handle.getPosition();
    }

    @Override
    public void setPosition(double position){
        if(position < 0 || position > 1){
            throw new IllegalArgumentException("Position must be between 0 and 1: " + position);
        }
        handle.setPosition(position);
    }


    @Override
    public void render(Graphics2D g){
        sliderDeco.drawSliderRail(g, railBox);
        sliderDeco.drawSliderHandle(g, handle.getBounds(), handle.isCurrentlyDragging());
    }

    @Override
    public void tick(){}


    @Override
    public void onMouseDragged(MouseInputEvent e){
        if(handle.withinBounds(e.getCoord())){
            handle.onMouseDragged(e);
        }
    }

    @Override
    public void onMousePressed(MouseInputEvent e){
        Coord coord = e.getCoord();
        if(!handle.withinBounds(coord)){
            handle.moveMiddleTo(coord.x());
        }
        handle.onMousePressed(e);
    }

    @Override
    public void onMouseReleased(MouseInputEvent e){
        handle.onMouseReleased(e);
    }

    @Override
    public void setInputSource(GUIInputRegister<BoundedMouseInputListener> inputSource){
        inputSource.addMouseInputListener(this);
        handle.setInputSource(inputSource);
    }

    @Override
    public void unsetInputSource(GUIInputRegister<BoundedMouseInputListener> inputSource){
        inputSource.removeMouseInputListener(this);
        handle.unsetInputSource(inputSource);
    }

}

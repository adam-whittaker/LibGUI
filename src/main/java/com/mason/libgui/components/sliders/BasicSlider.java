package com.mason.libgui.components.sliders;

import com.mason.libgui.components.ComponentIDRegister;
import com.mason.libgui.components.deco.BasicSliderDeco;
import com.mason.libgui.components.deco.SliderDeco;
import com.mason.libgui.core.component.AbstractUIComponent;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;
import com.mason.libgui.utils.structures.Size;

import java.awt.*;

import static com.mason.libgui.components.sliders.SliderEvent.sliderEventConstructor;

public class BasicSlider extends AbstractUIComponent implements Slider, BoundedMouseInputListener{


    private final String name;
    private final int id;
    private final SliderDeco sliderDeco;
    private final RectQuery railBox;

    private final SliderHandle handle;


    protected BasicSlider(String name, HitboxRect railHitbox, int handleWidth, SliderDeco sliderDeco){
        super(railHitbox);
        this.name = name.toUpperCase();
        this.id = ComponentIDRegister.registerComponent(this.name);
        this.sliderDeco = sliderDeco;
        this.railBox = railHitbox;
        this.handle = constructSliderHandle(railHitbox, handleWidth);
    }

    private SliderHandle constructSliderHandle(HitboxRect railHitbox, int handleWidth){
        HitboxRect sliderRect = new HitboxRect(railHitbox.getCoord(), new Size(handleWidth, railHitbox.height()));
        return new SliderHandle(sliderEventConstructor(this), sliderRect, railHitbox);
    }

    public static BasicSlider build(String name, HitboxRect railHitbox, int handleWidth, SliderDeco sliderDeco){
        return new BasicSlider(name, railHitbox, handleWidth, sliderDeco);
    }

    public static BasicSlider buildWithBasicDeco(String name, HitboxRect railHitbox, int handleWidth){
        return build(name, railHitbox, handleWidth, new BasicSliderDeco());
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
    public void addSliderEventListener(SliderEventListener listener){
        handle.addSliderEventListener(listener);
    }

    @Override
    public void removeSliderEventListener(SliderEventListener listener){
        handle.removeSliderEventListener(listener);
    }


    @Override
    public void render(Graphics2D g){
        sliderDeco.drawSliderRail(g, railBox);
        sliderDeco.drawSliderHandle(g, handle.getBox(), handle.isCurrentlyDragging());
    }

    @Override
    public void tick(){}


    public void dragIncrementEventForPaintToolkitSetUp(){
        handle.dragIncrementEvent();
    }

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

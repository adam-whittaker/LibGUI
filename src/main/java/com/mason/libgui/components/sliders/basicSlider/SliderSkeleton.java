package com.mason.libgui.components.sliders.basicSlider;

import com.mason.libgui.components.deco.SliderDeco;
import com.mason.libgui.components.sliders.sliderPositionState.SliderPositionState;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.utils.structures.Size;

public class SliderSkeleton{


    private String name;
    private HitboxRect railHitbox;
    private HitboxRect boundary;
    private SliderDeco sliderDeco;
    private Size sliderHandleSize;
    private SliderPositionState sliderPositionState;


    public SliderSkeleton(){}


    public String getName(){
        if(name == null){
            throw new IllegalStateException("name is not set");
        }
        return name;
    }

    public void setName(String name){
        if(this.name != null){
            throw new IllegalStateException("name is already set");
        }
        this.name = name;
    }

    public SliderDeco getSliderDeco(){
        if(sliderDeco == null){
            throw new IllegalStateException("sliderDeco is not set");
        }
        return sliderDeco;
    }

    public void setSliderDeco(SliderDeco sliderDeco){
        if(this.sliderDeco != null){
            throw new IllegalStateException("sliderDeco is already set");
        }
        this.sliderDeco = sliderDeco;
    }

    public HitboxRect getRailHitbox(){
        if(railHitbox == null){
            throw new IllegalStateException("railHitbox is not set");
        }
        return railHitbox;
    }

    public void setRailHitbox(HitboxRect railHitbox){
        if(this.railHitbox != null){
            throw new IllegalStateException("railHitbox is already set");
        }
        this.railHitbox = railHitbox;
    }

    public HitboxRect getBoundary(){
        if(boundary == null){
            throw new IllegalStateException("boundary is not set");
        }
        return boundary;
    }

    public void setBoundary(HitboxRect boundary){
        if(this.boundary != null){
            throw new IllegalStateException("boundary is already set");
        }
        this.boundary = boundary;
    }

    public Size getSliderHandleSize(){
        if(sliderHandleSize == null){
            throw new IllegalStateException("sliderHandleSize is not set");
        }
        return sliderHandleSize;
    }

    public void setSliderHandleSize(Size sliderHandleSize){
        if(this.sliderHandleSize != null){
            throw new IllegalStateException("sliderHandleSize is already set");
        }
        this.sliderHandleSize = sliderHandleSize;
    }

    public SliderPositionState getSliderPositionState(){
        if(sliderPositionState == null){
            throw new IllegalStateException("sliderPositionState is not set");
        }
        return sliderPositionState;
    }

    public void setSliderPositionState(SliderPositionState sliderPositionState){
        if(this.sliderPositionState != null){
            throw new IllegalStateException("sliderPositionState is already set");
        }
        this.sliderPositionState = sliderPositionState;
    }

}

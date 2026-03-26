package com.mason.libgui.components.sliders.event;

import com.mason.libgui.components.sliders.Slider;

public class SliderEvent{


    private final double position;
    private final SliderEventType type;
    private final Slider slider;
    private final long timeStampMillis;


    public SliderEvent(Slider slider, SliderEventType type){
        this.position = slider.getPosition();
        this.type = type;
        this.slider = slider;
        timeStampMillis = System.currentTimeMillis();
    }


    public double getPosition(){
        return position;
    }

    public SliderEventType getType(){
        return type;
    }

    public Slider getSlider(){
        return slider;
    }

    public long getTimeStampMillis(){
        return timeStampMillis;
    }

    public int getSliderID(){
        return slider.getID();
    }


    public static SliderEventConstructor sliderEventConstructor(Slider slider){
        return (type) -> new SliderEvent(slider, type);
    }

    public interface SliderEventConstructor{

        SliderEvent constructSliderEvent(SliderEventType type);

    }

}

package com.mason.libgui.components.sliders;

import com.mason.libgui.components.Identifiable;

public interface Slider extends Identifiable{


    double getPosition();

    void setPosition(double position);

    void addSliderEventListener(SliderEventListener listener);

    void removeSliderEventListener(SliderEventListener listener);

}

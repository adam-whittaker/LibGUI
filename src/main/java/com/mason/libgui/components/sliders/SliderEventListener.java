package com.mason.libgui.components.sliders;

public interface SliderEventListener{


    void sliderGrabbed(SliderEvent event);

    void sliderDragged(SliderEvent event);

    void sliderReleased(SliderEvent event);

}

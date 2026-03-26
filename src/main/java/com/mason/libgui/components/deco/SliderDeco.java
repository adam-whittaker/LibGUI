package com.mason.libgui.components.deco;

import com.mason.libgui.utils.structures.interfaces.RectQuery;

import java.awt.*;

public interface SliderDeco{


    void drawSliderRail(Graphics2D g, RectQuery rail);

    void drawSliderHandle(Graphics2D g, RectQuery handle, boolean selected);

}

package com.mason.libgui.components.deco;

import com.mason.libstruct.geo.Size;

import java.awt.*;

public interface PaneDeco{


    void drawBackground(Graphics2D g, Size size);

    void drawForeground(Graphics2D g, Size size);

}

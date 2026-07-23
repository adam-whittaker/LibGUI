package com.mason.libgui.components.deco;

import com.mason.libstruct.interfaces.RectQuery;

import java.awt.*;

public interface ButtonDeco{

    void drawButtonDeco(Graphics2D g, RectQuery box, ButtonDecoRenderState state);

}

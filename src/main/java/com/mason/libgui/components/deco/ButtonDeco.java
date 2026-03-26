package com.mason.libgui.components.deco;

import com.mason.libgui.components.toggles.ToggleRenderState;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

import java.awt.*;

public interface ButtonDeco{

    void drawButtonDeco(Graphics2D g, RectQuery box, ToggleRenderState state);

}

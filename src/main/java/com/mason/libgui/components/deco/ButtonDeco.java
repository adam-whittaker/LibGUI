package com.mason.libgui.components.deco;

import com.mason.libgui.components.toggles.ToggleState;
import com.mason.libgui.utils.structures.RectQuery;

import java.awt.*;

public interface ButtonDeco{

    void drawButtonDeco(Graphics2D g, RectQuery box, ToggleState state);

}

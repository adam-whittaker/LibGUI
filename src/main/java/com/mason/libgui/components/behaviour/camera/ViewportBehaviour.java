package com.mason.libgui.components.behaviour.camera;

import com.mason.libgui.core.input.mouse.MouseInputListener;
import com.mason.libstruct.geo.Coord;

public interface ViewportBehaviour extends MouseInputListener{


    ApparentCoord screenToApparent(Coord screen);

}

package com.mason.libgui.core.input.guiLayer;

import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libstruct.geo.Coord;

public class MouseInputCapturer extends SimpleGUIInputGate implements BoundedMouseInputListener{


    @Override
    public boolean withinBounds(Coord c){
        return true;
    }

}

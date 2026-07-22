package com.mason.libgui.components.sliders.sliderPositionState;

import com.mason.libstruct.states.position.PositionState;
import com.mason.libstruct.interfaces.Movable;
import com.mason.libstruct.geo.Coord;

public interface SliderPositionState extends PositionState, Movable{


    void setCoordWithClamp(Coord coord);

}

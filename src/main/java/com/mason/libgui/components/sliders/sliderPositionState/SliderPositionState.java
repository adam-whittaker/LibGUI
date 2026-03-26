package com.mason.libgui.components.sliders.sliderPositionState;

import com.mason.libgui.utils.structures.states.position.PositionState;
import com.mason.libgui.utils.structures.interfaces.Movable;
import com.mason.libgui.utils.structures.Coord;

public interface SliderPositionState extends PositionState, Movable{


    void setCoordWithClamp(Coord coord);

}

package com.mason.libgui.core.input.mouse;

import com.mason.libstruct.interfaces.Boundable;

public interface MouseInputBounder{

    BoundedMouseInputListener fromBounds(Boundable bounds);

}

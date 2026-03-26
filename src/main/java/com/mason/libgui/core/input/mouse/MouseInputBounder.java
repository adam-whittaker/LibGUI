package com.mason.libgui.core.input.mouse;

import com.mason.libgui.utils.structures.interfaces.Boundable;

public interface MouseInputBounder{

    BoundedMouseInputListener fromBounds(Boundable bounds);

}

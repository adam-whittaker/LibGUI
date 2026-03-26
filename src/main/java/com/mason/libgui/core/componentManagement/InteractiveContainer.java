package com.mason.libgui.core.componentManagement;

import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;

public interface InteractiveContainer extends UIComponentContainer, GUIInputRegister<BoundedMouseInputListener>{

}

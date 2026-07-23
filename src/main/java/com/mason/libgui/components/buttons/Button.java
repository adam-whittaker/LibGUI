package com.mason.libgui.components.buttons;

import com.mason.libgui.components.Identifiable;
import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.input.mouse.MouseInputEvent;

public interface Button extends Identifiable, UIComponent{

    void click(MouseInputEvent e);

}

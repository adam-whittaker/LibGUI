package com.mason.libgui.components.toggles.dispatch;

import com.mason.libgui.components.toggles.Toggle;
import com.mason.libgui.components.toggles.dispatch.ToggleEventListener;

public interface DispatchToggle extends Toggle{

    void addToggleEventListener(ToggleEventListener listener);

    void removeToggleEventListener(ToggleEventListener listener);

}

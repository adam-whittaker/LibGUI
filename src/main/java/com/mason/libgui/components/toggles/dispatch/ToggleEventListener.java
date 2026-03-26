package com.mason.libgui.components.toggles.dispatch;

import com.mason.libgui.components.toggles.dispatch.ToggleEvent;

public interface ToggleEventListener{


    void toggleSelected(ToggleEvent event);

    void toggleUnselected(ToggleEvent event);

}

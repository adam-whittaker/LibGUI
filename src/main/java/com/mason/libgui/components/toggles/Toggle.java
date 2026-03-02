package com.mason.libgui.components.toggles;

import com.mason.libgui.components.Identifiable;

public interface Toggle extends Identifiable{

    public ToggleState getState();

    default boolean isDown(){
        return getState().isDown();
    }

    public void select();

    public void unselect();

    void addToggleEventListener(ToggleEventListener listener);

    void removeToggleEventListener(ToggleEventListener listener);

}

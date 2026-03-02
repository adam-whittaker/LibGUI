package com.mason.libgui.components.toggles;

public enum ToggleState{


    UNSELECTED(false),
    HOVERING(false),
    SELECTED(true),
    PRESSED(true);


    private final boolean down;


    private ToggleState(boolean down){
        this.down = down;
    }


    public boolean isDown(){
        return down;
    }

}

package com.mason.libgui.components.toggles;

import com.mason.libgui.utils.structures.states.onOff.OnOffQuery;

public class ToggleRenderState{


    private boolean hovering;
    private boolean pressed;
    private final OnOffQuery toggle;


    ToggleRenderState(OnOffQuery toggle){
        this.toggle = toggle;
    }


    public boolean isDown(){
        return toggle.isOn();
    }

    public boolean isHovering(){
        return hovering && !toggle.isOn();
    }

    public void setMouseHovering(){
        this.hovering = true;
    }

    public void unsetMouseHovering(){
        this.hovering = false;
    }

    public boolean isBeingPressed(){
        return pressed && !toggle.isOn();
    }

    public void setMouseDown(){
        this.pressed = true;
    }

    public void releaseMouse(){
        pressed = false;
    }

}

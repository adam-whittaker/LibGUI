package com.mason.libgui.components.toggles;

import com.mason.libgui.components.deco.ButtonDecoRenderState;
import com.mason.libstruct.states.onOff.OnOffQuery;

public class ToggleRenderState implements ButtonDecoRenderState{


    private boolean hovering;
    private boolean pressed;
    private final OnOffQuery toggle;


    protected ToggleRenderState(OnOffQuery toggle){
        this.toggle = toggle;
    }


    @Override
    public boolean isDown(){
        return toggle.isOn();
    }

    @Override
    public boolean isHovering(){
        return hovering && !toggle.isOn();
    }

    @Override
    public void setMouseHovering(){
        this.hovering = true;
    }

    @Override
    public void unsetMouseHovering(){
        this.hovering = false;
    }

    @Override
    public boolean isBeingPressed(){
        return pressed && !toggle.isOn();
    }

    @Override
    public void setMouseDown(){
        this.pressed = true;
    }

    @Override
    public void releaseMouse(){
        pressed = false;
    }

}

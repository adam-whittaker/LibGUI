package com.mason.libgui.components.toggles.sourced;

import com.mason.libgui.components.deco.ButtonDeco;
import com.mason.libgui.components.toggles.AbstractToggle;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.utils.structures.states.onOff.OnOffState;

public class SourcedToggle extends AbstractToggle{


    private final OnOffState source;


    public SourcedToggle(String name, HitboxRect hitbox, ButtonDeco buttonDeco, OnOffState source){
        super(name, hitbox, buttonDeco);
        this.source = source;
    }


    @Override
    public boolean isOn(){
        return source.isOn();
    }

    @Override
    public void turnOn(){
        source.turnOn();
    }

    @Override
    public void turnOff(){
        source.turnOff();
    }

}

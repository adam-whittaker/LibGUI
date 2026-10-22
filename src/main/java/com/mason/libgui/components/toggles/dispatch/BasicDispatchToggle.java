package com.mason.libgui.components.toggles.dispatch;

import com.mason.libgui.components.toggles.AbstractToggle;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.components.deco.BasicButtonDeco;
import com.mason.libgui.components.deco.ButtonDeco;

import java.util.*;

public class BasicDispatchToggle extends AbstractToggle implements DispatchToggle{


    private final Set<ToggleEventListener> listeners;
    private boolean on;


    protected BasicDispatchToggle(HitboxRect hitbox, ButtonDeco buttonDeco){
        super(hitbox, buttonDeco);
        listeners = new HashSet<>();
    }

    public static BasicDispatchToggle buildWithBasicDeco(HitboxRect boundary, String iconFilepath){
        return build(boundary, BasicButtonDeco.build(iconFilepath));
    }

    public static BasicDispatchToggle build(HitboxRect boundary, ButtonDeco buttonDeco){
        return new BasicDispatchToggle(boundary, buttonDeco);
    }


    @Override
    public boolean isOn(){
        return on;
    }

    @Override
    public void turnOn(){
        if(!isOn()){
            on = true;
            dispatchToggleSelectEvent();
        }
    }

    private void dispatchToggleSelectEvent(){
        ToggleEvent event = new ToggleEvent(true, this);
        for(ToggleEventListener listener : listeners){
            listener.toggleSelected(event);
        }
    }

    @Override
    public void turnOff(){
        if(isOn()){
            on = false;
            dispatchToggleUnselectEvent();
        }
    }

    private void dispatchToggleUnselectEvent(){
        ToggleEvent event = new ToggleEvent(false, this);
        for(ToggleEventListener listener : listeners){
            listener.toggleUnselected(event);
        }
    }


    @Override
    public void addToggleEventListener(ToggleEventListener listener){
        listeners.add(listener);
    }

    @Override
    public void removeToggleEventListener(ToggleEventListener listener){
        listeners.remove(listener);
    }

}

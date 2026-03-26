package com.mason.libgui.components.toggles.dispatch;


import com.mason.libgui.components.toggles.Toggle;

import java.util.HashSet;
import java.util.Set;

public class ExclusiveDispatchToggleGroup implements ToggleEventListener{


    private final Set<DispatchToggle> toggles;


    public ExclusiveDispatchToggleGroup(){
        this.toggles = new HashSet<>();
    }


    @Override
    public void toggleSelected(ToggleEvent event){
        for(Toggle toggle : toggles){
            if(!toggle.equals(event.getToggle())){
                toggle.turnOff();
            }
        }
    }

    @Override
    public void toggleUnselected(ToggleEvent event){}


    public void addToggle(DispatchToggle toggle){
        toggles.add(toggle);
        toggle.addToggleEventListener(this);
    }

    public void removeToggle(DispatchToggle toggle){
        toggles.remove(toggle);
        toggle.removeToggleEventListener(this);
    }

}

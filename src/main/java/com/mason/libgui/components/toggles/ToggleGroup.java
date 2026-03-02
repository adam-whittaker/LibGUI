package com.mason.libgui.components.toggles;


import java.util.HashSet;
import java.util.Set;

public class ToggleGroup implements ToggleEventListener{


    private final Set<Toggle> toggles;


    public ToggleGroup(){
        this.toggles = new HashSet<>();
    }


    @Override
    public void toggleSelected(ToggleEvent event){
        for(Toggle toggle : toggles){
            if(!toggle.equals(event.getToggle())){
                toggle.unselect();
            }
        }
    }

    @Override
    public void toggleUnselected(ToggleEvent event){}


    public void addToggle(Toggle toggle){
        toggles.add(toggle);
        toggle.addToggleEventListener(this);
    }

    public void removeToggle(Toggle toggle){
        toggles.remove(toggle);
        toggle.removeToggleEventListener(this);
    }

}

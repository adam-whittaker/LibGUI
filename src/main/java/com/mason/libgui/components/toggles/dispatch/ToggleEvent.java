package com.mason.libgui.components.toggles.dispatch;

import com.mason.libgui.components.toggles.Toggle;

public class ToggleEvent{


    private final boolean selected;
    private final Toggle toggle;
    private final long timeStampMillis;


    public ToggleEvent(boolean selected, Toggle toggle){
        this.selected = selected;
        this.toggle = toggle;
        timeStampMillis = System.currentTimeMillis();
    }


    public boolean isSelected(){
        return selected;
    }

    public Toggle getToggle(){
        return toggle;
    }

    public long getTimeStampMillis(){
        return timeStampMillis;
    }

    public int getToggleID(){
        return toggle.getID();
    }

}

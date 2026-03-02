package com.mason.libgui.components.toggles;

public class ToggleEvent{


    private final ToggleState state;
    private final Toggle toggle;
    private final long timeStampMillis;


    public ToggleEvent(ToggleState state, Toggle toggle){
        this.state = state;
        this.toggle = toggle;
        timeStampMillis = System.currentTimeMillis();
    }


    public ToggleState getState(){
        return state;
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

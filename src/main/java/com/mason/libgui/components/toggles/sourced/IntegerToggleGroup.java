package com.mason.libgui.components.toggles.sourced;

import com.mason.libgui.components.deco.ButtonDeco;
import com.mason.libgui.components.toggles.Toggle;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.utils.structures.states.intState.IntState;
import com.mason.libgui.utils.structures.states.onOff.OnOffState;

public class IntegerToggleGroup{


    private final IntState state;


    public IntegerToggleGroup(IntState state){
        this.state = state;
    }


    public Toggle createToggle(String name, HitboxRect rect, ButtonDeco deco, int value){
        OnOffState source = createSource(value);
        return new SourcedToggle(name, rect, deco, source);
    }

    private OnOffState createSource(int value){
        return new OnOffState(){

            @Override
            public void turnOn(){
                state.setState(value);
            }

            @Override
            public void turnOff(){}

            @Override
            public boolean isOn(){
                return value == state.getState();
            }

        };
    }

}

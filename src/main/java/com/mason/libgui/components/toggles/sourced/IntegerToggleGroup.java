package com.mason.libgui.components.toggles.sourced;

import com.mason.libgui.components.deco.ButtonDeco;
import com.mason.libgui.components.toggles.Toggle;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libstruct.states.intState.IntState;
import com.mason.libstruct.states.onOff.OnOffState;

public class IntegerToggleGroup{


    private final IntState state;
    private final Runnable toggleChangeListener;


    public IntegerToggleGroup(IntState state, Runnable toggleChangeListener){
        this.state = state;
        this.toggleChangeListener = toggleChangeListener;
    }

    public IntegerToggleGroup(IntState state){
        this.state = state;
        this.toggleChangeListener = () -> {};
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
                toggleChangeListener.run();
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

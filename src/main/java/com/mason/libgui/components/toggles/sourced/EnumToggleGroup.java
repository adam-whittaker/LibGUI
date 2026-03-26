package com.mason.libgui.components.toggles.sourced;

import com.mason.libgui.components.deco.ButtonDeco;
import com.mason.libgui.components.toggles.Toggle;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.utils.structures.states.enumState.EnumState;
import com.mason.libgui.utils.structures.states.onOff.OnOffState;

public class EnumToggleGroup<T extends Enum<T>>{


    private final EnumState<T> state;


    public EnumToggleGroup(EnumState<T> state){
        this.state = state;
    }


    public Toggle createToggle(HitboxRect rect, ButtonDeco deco, T value){
        OnOffState source = createSource(value);
        return new SourcedToggle(value.name(), rect, deco, source);
    }

    private OnOffState createSource(T value){
        return new OnOffState(){

            @Override
            public void turnOn(){
                state.setState(value);
            }

            @Override
            public void turnOff(){}

            @Override
            public boolean isOn(){
                return value.equals(state.getState());
            }

        };
    }

}

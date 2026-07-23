package com.mason.libgui.components.buttons;

import com.mason.libgui.components.toggles.ToggleRenderState;
import com.mason.libstruct.states.onOff.OnOffState;

public class ButtonRenderState extends ToggleRenderState{


    protected ButtonRenderState(){
        super(OnOffState.newDefault());
    }

}

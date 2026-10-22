package com.mason.libgui.components.toggles.dispatch;

import com.mason.libgui.components.deco.ButtonDeco;
import com.mason.libgui.components.toggles.dispatch.BasicDispatchToggle;
import com.mason.libgui.components.toggles.dispatch.ToggleEventListener;
import com.mason.libgui.core.component.hitbox.HitboxRect;

public abstract class PresetToggle extends BasicDispatchToggle implements ToggleEventListener{


    protected PresetToggle(HitboxRect hitbox, ButtonDeco buttonDeco){
        super(hitbox, buttonDeco);
        addToggleEventListener(this);
    }

}

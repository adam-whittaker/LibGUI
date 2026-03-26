
package com.mason.libgui.core.component;

import com.mason.libgui.core.component.hitbox.Hitbox;

import java.awt.*;

public interface UIComponent extends Hitbox{


    public abstract void render(Graphics2D g);
    
    public abstract void tick();
    
}

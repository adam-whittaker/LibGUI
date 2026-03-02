
package com.mason.libgui.core.component;

import java.awt.*;

public interface UIComponent extends Hitbox{


    public abstract void render(Graphics2D g);
    
    public abstract void tick();
    
}

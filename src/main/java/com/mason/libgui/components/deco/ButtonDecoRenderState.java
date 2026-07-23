package com.mason.libgui.components.deco;

public interface ButtonDecoRenderState{

    public boolean isDown();

    public boolean isHovering();

    public void setMouseHovering();

    public void unsetMouseHovering();

    public boolean isBeingPressed();

    public void setMouseDown();

    public void releaseMouse();

}

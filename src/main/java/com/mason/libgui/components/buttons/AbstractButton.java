package com.mason.libgui.components.buttons;

import com.mason.libgui.components.ComponentIDRegister;
import com.mason.libgui.components.behaviour.MouseInputListenerWithCapture;
import com.mason.libgui.components.deco.ButtonDeco;
import com.mason.libgui.components.toggles.ToggleRenderState;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libstruct.geo.Coord;

import java.awt.*;

public abstract class AbstractButton extends MouseInputListenerWithCapture implements Button{


    private final String name;
    private final int id;
    private final HitboxRect hitbox;
    private final ButtonDeco buttonDeco;
    private final ToggleRenderState renderState;


    protected AbstractButton(String name, HitboxRect hitbox, ButtonDeco buttonDeco){
        super();
        this.name = name.toUpperCase();
        id = ComponentIDRegister.registerComponent(this);
        this.hitbox = hitbox;
        this.buttonDeco = buttonDeco;
        renderState = new ButtonRenderState();
    }


    @Override
    public void render(Graphics2D g){
        buttonDeco.drawButtonDeco(g, hitbox, renderState);
    }

    @Override
    public void tick(){

    }

    @Override
    public void setCoord(Coord c){
        hitbox.setCoord(c);
    }

    @Override
    public Coord getCoord(){
        return hitbox.getCoord();
    }

    @Override
    public int getID(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean withinBounds(Coord c){
        return hitbox.withinBounds(c);
    }


    @Override
    public void onMouseMoved(MouseInputEvent e){
        if(withinBounds(e.getCoord())){
            renderState.setMouseHovering();
            captureMouse();
        }else{
            renderState.unsetMouseHovering();
            releaseMouse();
        }
    }

    @Override
    public void onMousePressed(MouseInputEvent e){
        renderState.setMouseDown();
        captureMouse();
    }

    @Override
    public void onMouseReleased(MouseInputEvent e){
        renderState.releaseMouse();
        releaseMouse();
        click(e);
    }

}

package com.mason.libgui.components.toggles;

import com.mason.libgui.components.ComponentIDRegister;
import com.mason.libgui.components.behaviour.MouseInputListenerWithCapture;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.components.deco.BasicButtonDeco;
import com.mason.libgui.components.deco.ButtonDeco;

import java.awt.*;
import java.util.*;

import static com.mason.libgui.components.toggles.ToggleState.*;

public class BasicToggle extends MouseInputListenerWithCapture implements UIComponent, Toggle{


    private final String name;
    private final int id;
    private final HitboxRect hitbox;
    private final ButtonDeco buttonDeco;
    private final Set<ToggleEventListener> listeners;
    private ToggleState state;


    protected BasicToggle(String name, HitboxRect hitbox, ButtonDeco buttonDeco){
        super();
        this.name = name.toUpperCase();
        id = ComponentIDRegister.registerComponent(this.name);
        this.hitbox = hitbox;
        this.buttonDeco = buttonDeco;
        listeners = new HashSet<>();
        state = ToggleState.UNSELECTED;
    }

    public static BasicToggle buildWithBasicDeco(String name, HitboxRect boundary, String iconFilepath){
        return build(name, boundary, BasicButtonDeco.build(iconFilepath));
    }

    public static BasicToggle build(String name, HitboxRect boundary, ButtonDeco buttonDeco){
        return new BasicToggle(name, boundary, buttonDeco);
    }


    @Override
    public void render(Graphics2D g){
        buttonDeco.drawButtonDeco(g, hitbox, state);
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
    public ToggleState getState(){
        return state;
    }

    @Override
    public void select(){
        if(!state.equals(SELECTED)){
            state = SELECTED;
            dispatchToggleSelectEvent();
        }
    }

    private void dispatchToggleSelectEvent(){
        ToggleEvent event = new ToggleEvent(SELECTED, this);
        for(ToggleEventListener listener : listeners){
            listener.toggleSelected(event);
        }
    }

    @Override
    public void unselect(){
        if(!state.equals(UNSELECTED)){
            state = UNSELECTED;
            dispatchToggleUnselectEvent();
        }
    }

    private void dispatchToggleUnselectEvent(){
        ToggleEvent event = new ToggleEvent(UNSELECTED, this);
        for(ToggleEventListener listener : listeners){
            listener.toggleUnselected(event);
        }
    }


    @Override
    public void addToggleEventListener(ToggleEventListener listener){
        listeners.add(listener);
    }

    @Override
    public void removeToggleEventListener(ToggleEventListener listener){
        listeners.remove(listener);
    }


    @Override
    public void onMouseMoved(MouseInputEvent e){
        if(state.isDown()){
            return;
        }
        if(withinBounds(e.getCoord())){
            state = HOVERING;
            captureMouse();
        }else{
            state = UNSELECTED;
            releaseMouse();
        }
    }

    @Override
    public void onMousePressed(MouseInputEvent e){
        if(!state.isDown()){
            state = PRESSED;
        }
    }

    @Override
    public void onMouseReleased(MouseInputEvent e){
        if(state.equals(SELECTED)){
            unselect();
        }else{
            select();
            releaseMouse();
        }
    }

}

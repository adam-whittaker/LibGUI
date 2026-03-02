package com.mason.libgui.components.panes;

import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.componentManagement.UIComponentManager;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class PaneSkeleton{


    private HitboxRect boundary;
    private UIComponentManager componentManager;
    private final List<UIComponent> components;
    private final List<BoundedMouseInputListener> mouseInputListeners;
    private final List<KeyListener> keyListeners;


    public PaneSkeleton(){
        components = new ArrayList<>();
        mouseInputListeners = new ArrayList<>();
        keyListeners = new ArrayList<>();
    }


    public HitboxRect getBoundary(){
        if(boundary == null){
            throw new IllegalStateException("Argument unset!");
        }
        return boundary;
    }

    public void setBoundary(HitboxRect boundary){
        this.boundary = boundary;
    }

    public UIComponentManager getComponentManager(){
        if(componentManager == null){
            setComponentManager(UIComponentManager.buildSimpleUIComponentManager());
        }
        return componentManager;
    }

    public void setComponentManager(UIComponentManager componentManager){
        this.componentManager = componentManager;
    }


    public Iterable<UIComponent> getComponents(){
        return components;
    }

    public Iterable<BoundedMouseInputListener> getMouseInputListeners(){
        return mouseInputListeners;
    }

    public Iterable<KeyListener> getKeyListeners(){
        return keyListeners;
    }

    public void addComponent(UIComponent component){
        components.add(component);
    }

    public void addMouseInputListener(BoundedMouseInputListener listener){
        mouseInputListeners.add(listener);
    }

    public void addKeyListener(KeyListener keyListener){
        keyListeners.add(keyListener);
    }

}

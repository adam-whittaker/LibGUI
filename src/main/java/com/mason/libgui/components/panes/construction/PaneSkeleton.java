package com.mason.libgui.components.panes.construction;

import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.componentManagement.InteractiveContainer;
import com.mason.libgui.core.componentManagement.UIComponentManager;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class PaneSkeleton implements InteractiveContainer{


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
        if(this.boundary != null){
            throw new IllegalStateException("boundary is already set");
        }
        this.boundary = boundary;
    }

    public void setBoundary(RectQuery rect){
        setBoundary(HitboxRect.fromRect(rect));
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

    @Override
    public void addComponent(UIComponent component){
        components.add(component);
    }

    @Override
    public void removeComponent(UIComponent comp){
        throw new IllegalStateException("Should not remove from skeleton.");
    }

    @Override
    public void addMouseInputListener(BoundedMouseInputListener listener){
        mouseInputListeners.add(listener);
    }

    @Override
    public void removeMouseInputListener(BoundedMouseInputListener listener){
        throw new IllegalStateException("Should not remove from skeleton.");
    }

    @Override
    public void addKeyListener(KeyListener keyListener){
        keyListeners.add(keyListener);
    }

    @Override
    public void removeKeyListener(KeyListener listener){
        throw new IllegalStateException("Should not remove from skeleton.");
    }

}

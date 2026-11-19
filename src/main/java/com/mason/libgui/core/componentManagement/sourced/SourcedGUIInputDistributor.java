package com.mason.libgui.core.componentManagement.sourced;

import com.mason.libgui.core.input.componentLayer.AbstractInputDistributor;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;

import java.awt.event.KeyListener;

public class SourcedGUIInputDistributor extends AbstractInputDistributor{


    private final Iterable<? extends BoundedMouseInputListener> source;


    public SourcedGUIInputDistributor(Iterable<? extends BoundedMouseInputListener> source){
        this.source = source;
    }


    @Override
    public void addKeyListener(KeyListener listener){
        throw new IllegalStateException("Should not be externally modifying a sourced container");
    }

    @Override
    public void removeKeyListener(KeyListener listener){
        throw new IllegalStateException("Should not be externally modifying a sourced container");
    }

    @Override
    public void addMouseInputListener(BoundedMouseInputListener listener){
        throw new IllegalStateException("Should not be externally modifying a sourced container");
    }

    @Override
    public void removeMouseInputListener(BoundedMouseInputListener listener){
        throw new IllegalStateException("Should not be externally modifying a sourced container");
    }


    @Override
    protected Iterable<? extends BoundedMouseInputListener> getMouseRegister(){
        return source;
    }

}

package com.mason.libgui.core.input.componentLayer;

import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;

import java.awt.event.*;

public class UIComponentManagerInputDistributor extends AbstractInputDistributor{


    private final ListenerRegister<KeyListener> keyRegister;
    private final ListenerRegister<BoundedMouseInputListener> mouseRegister;


    public UIComponentManagerInputDistributor(){
        keyRegister = new OrderedListenerRegister<>();
        mouseRegister = new OrderedListenerRegister<>();
    }


    @Override
    protected Iterable<BoundedMouseInputListener> getMouseRegister(){
        return mouseRegister;
    }


    @Override
    public void addKeyListener(KeyListener listener){
        keyRegister.addListener(listener);
    }

    @Override
    public void removeKeyListener(KeyListener listener){
        keyRegister.removeListener(listener);
    }

    @Override
    public void addMouseInputListener(BoundedMouseInputListener listener){
        mouseRegister.addListener(listener);
    }

    @Override
    public void removeMouseInputListener(BoundedMouseInputListener listener){
        mouseRegister.removeListener(listener);
    }


    @Override
    public void keyTyped(KeyEvent e){
        keyRegister.forEach(listener -> listener.keyTyped(e));
    }

    @Override
    public void keyPressed(KeyEvent e){
        keyRegister.forEach(listener -> listener.keyPressed(e));
    }

    @Override
    public void keyReleased(KeyEvent e){
        keyRegister.forEach(listener -> listener.keyReleased(e));
    }

}

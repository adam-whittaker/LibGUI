package com.mason.libgui.core.input.guiLayer;

import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.core.input.mouse.MouseInputListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface GUIInputSocket extends KeyListener, MouseInputListener{

    @Override
    default void keyTyped(KeyEvent e){}

    @Override
    default void keyPressed(KeyEvent e){}

    @Override
    default void keyReleased(KeyEvent e){}


    public static GUIInputSocket wrap(MouseInputListener listener){
        return new GUIInputSocket(){
            public void onMouseMoved(MouseInputEvent e){
                listener.onMouseMoved(e);
            }
            public void onMouseDragged(MouseInputEvent e){
                listener.onMouseDragged(e);
            }
            public void onMousePressed(MouseInputEvent e){
                listener.onMousePressed(e);
            }
            public void onMouseReleased(MouseInputEvent e){
                listener.onMouseReleased(e);
            }
            public void onMouseClicked(MouseInputEvent e){
                listener.onMouseClicked(e);
            }
            public void onMouseWheel(MouseInputEvent e){
                listener.onMouseWheel(e);
            }
        };
    }

}

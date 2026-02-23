package com.mason.libgui.core.input.mouse;

public interface MouseInputListener{

    default void onMouseMoved(MouseInputEvent e){
        e.reject();
    }
    default void onMouseDragged(MouseInputEvent e){
        e.reject();
    }

    default void onMousePressed(MouseInputEvent e){
        e.reject();
    }
    default void onMouseReleased(MouseInputEvent e){
        e.reject();
    }

    default void onMouseClicked(MouseInputEvent e){
        e.reject();
    }

    default void onMouseWheel(MouseInputEvent e){
        e.reject();
    }

}

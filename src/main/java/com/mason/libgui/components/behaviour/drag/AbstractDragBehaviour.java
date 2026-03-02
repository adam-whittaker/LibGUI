package com.mason.libgui.components.behaviour.drag;

import com.mason.libgui.components.behaviour.MouseInputListenerWithCapture;
import com.mason.libgui.core.input.guiLayer.MouseInputCapturer;
import com.mason.libgui.core.input.mouse.MouseInputEvent;

public abstract class AbstractDragBehaviour extends MouseInputListenerWithCapture{


    private boolean currentlyDragging = false;


    protected AbstractDragBehaviour(){
        super();
    }

    protected AbstractDragBehaviour(MouseInputCapturer mouseInputCapturer){
        super(mouseInputCapturer);
    }


    public boolean isCurrentlyDragging(){
        return currentlyDragging;
    }


    @Override
    public void onMousePressed(MouseInputEvent event){
        if(!currentlyDragging){
            currentlyDragging = true;
            captureMouse();
            onDragStart(event);
        }
    }

    protected abstract void onDragStart(MouseInputEvent event);


    @Override
    public void onMouseDragged(MouseInputEvent event){
        if(currentlyDragging){
            onDragIncrement(event);
        }
    }

    protected abstract void onDragIncrement(MouseInputEvent event);


    @Override
    public void onMouseReleased(MouseInputEvent event){
        if(currentlyDragging){
            currentlyDragging = false;
            releaseMouse();
            onDragRelease(event);
        }
    }

    protected abstract void onDragRelease(MouseInputEvent event);

}

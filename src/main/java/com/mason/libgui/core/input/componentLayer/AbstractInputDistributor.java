package com.mason.libgui.core.input.componentLayer;

import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.core.input.mouse.MouseInputListener;

public abstract class AbstractInputDistributor implements GUIMouseInputCoagulator, GUIInputDistributor<BoundedMouseInputListener>{


    public AbstractInputDistributor(){}


    protected abstract Iterable<? extends BoundedMouseInputListener> getMouseRegister();

    @Override
    public void onMouseInput(MouseInputEvent event){
        for(BoundedMouseInputListener listener : getMouseRegister()){
            if(listener.withinBounds(event.getCoord())){
                if(tryDispatchMouseInputEventToListener(event, listener)){
                    break;
                }
            }
        }
    }

    protected final boolean tryDispatchMouseInputEventToListener(MouseInputEvent event, MouseInputListener listener){
        dispatchMouseInputEventToListener(event, listener);
        if(event.isRejected()){
            event.unreject();
            return false;
        }
        return true;
    }

    protected final void dispatchMouseInputEventToListener(MouseInputEvent event, MouseInputListener listener){
        switch(event.getType()){
            case MOVE -> listener.onMouseMoved(event);
            case DRAG -> listener.onMouseDragged(event);

            case PRESS -> listener.onMousePressed(event);
            case RELEASE -> listener.onMouseReleased(event);

            case CLICK -> listener.onMouseClicked(event);
            case WHEEL -> listener.onMouseWheel(event);
        }
    }

}

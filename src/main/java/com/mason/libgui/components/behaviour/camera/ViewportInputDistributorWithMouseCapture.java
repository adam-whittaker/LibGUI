package com.mason.libgui.components.behaviour.camera;

import com.mason.libgui.core.input.componentLayer.UIComponentManagerInputDistributor;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;

public class ViewportInputDistributorWithMouseCapture extends ViewportInputDistributor{


    private final ViewportMouseInputCapturer viewportCapturer;


    public ViewportInputDistributorWithMouseCapture(ViewportBehaviour behaviour, ViewportMouseInputCapturer viewportCapturer){
        super(behaviour);
        this.viewportCapturer = viewportCapturer;
    }


    @Override
    public void onMouseInput(MouseInputEvent event){
        if(viewportCapturer.isActive()){
            dispatchMouseInputEventToBehaviour(event);
        }else{
            super.onMouseInput(event);
        }
    }

}

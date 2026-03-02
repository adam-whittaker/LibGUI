package com.mason.libgui.components.behaviour;

import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.guiLayer.GUIInputSocket;
import com.mason.libgui.core.input.guiLayer.MouseInputCapturer;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;

public abstract class MouseInputListenerWithCapture implements BoundedMouseInputListener, GUIInputSocket{


    private final MouseCaptureBehaviour mouseCapture;


    protected MouseInputListenerWithCapture(MouseInputCapturer capturer){
        this.mouseCapture = MouseCaptureBehaviour.buildWithDelegateSocketAndCustomCapturer(this, capturer);
    }

    protected MouseInputListenerWithCapture(){
        this.mouseCapture = MouseCaptureBehaviour.buildWithDelegateSocket(this);
    }


    @Override
    public void setInputSource(GUIInputRegister<BoundedMouseInputListener> inputSource){
        mouseCapture.setInputRegister(inputSource);
        BoundedMouseInputListener.super.setInputSource(inputSource);
    }

    protected MouseCaptureBehaviour getMouseCaptureBehaviour(){
        return mouseCapture;
    }

    protected void captureMouse(){
        mouseCapture.captureMouse();
    }

    protected void releaseMouse(){
        mouseCapture.releaseMouse();
    }

}

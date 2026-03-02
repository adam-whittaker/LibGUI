package com.mason.libgui.components.behaviour;

import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.guiLayer.GUIInputSocket;
import com.mason.libgui.core.input.guiLayer.MouseInputCapturer;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputListener;

public class MouseCaptureBehaviour{


    private GUIInputRegister<BoundedMouseInputListener> inputRegister;
    private final MouseInputCapturer mouseCapturer;
    private boolean isCaptured = false;


    private MouseCaptureBehaviour(MouseInputCapturer mouseCapturer){
        this.mouseCapturer = mouseCapturer;
    }

    public static MouseCaptureBehaviour buildWithoutDelegateSocket(){
        return new MouseCaptureBehaviour(new MouseInputCapturer());
    }

    public static MouseCaptureBehaviour buildWithDelegateSocket(GUIInputSocket socket){
        return buildWithDelegateSocketAndCustomCapturer(socket, new MouseInputCapturer());
    }

    public static MouseCaptureBehaviour buildWithDelegateMouseInputListener(MouseInputListener listener){
        return buildWithDelegateSocket(GUIInputSocket.wrap(listener));
    }

    public static MouseCaptureBehaviour buildWithDelegateSocketAndCustomCapturer(GUIInputSocket socket, MouseInputCapturer capturer){
        MouseCaptureBehaviour behaviour = new MouseCaptureBehaviour(capturer);
        behaviour.setDelegateSocket(socket);
        return behaviour;
    }


    public void setDelegateSocket(GUIInputSocket socket){
        mouseCapturer.setDelegateSocket(socket);
    }

    public void setInputRegister(GUIInputRegister<BoundedMouseInputListener> inputRegister){
        this.inputRegister = inputRegister;
    }


    public void captureMouse(){
        if(!isCaptured){
            validateInputRegister();
            isCaptured = true;
            mouseCapturer.setInputSource(inputRegister);
        }
    }

    private void validateInputRegister(){
        if(inputRegister == null){
            throw new IllegalStateException("Mouse Capture Behaviour Input Register not set.");
        }
    }

    public void releaseMouse(){
        if(isCaptured){
            isCaptured = false;
            mouseCapturer.unsetInputSource(inputRegister);
        }
    }

}

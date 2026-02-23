package com.mason.libgui.core.input.guiLayer;

import com.mason.libgui.core.input.mouse.MouseInputEvent;

import java.util.function.Consumer;

public class GUIMouseInputTransformer extends SimpleGUIInputGate{


    private Consumer<MouseInputEvent> transform;


    public GUIMouseInputTransformer(){

    }


    public final void setTransform(Consumer<MouseInputEvent> transform){
        this.transform = transform;
    }


    @Override
    public void onMouseMoved(MouseInputEvent e){
        transform.accept(e);
        super.onMouseMoved(e);
    }

    @Override
    public void onMouseDragged(MouseInputEvent e){
        transform.accept(e);
        super.onMouseDragged(e);
    }

    @Override
    public void onMousePressed(MouseInputEvent e){
        transform.accept(e);
        super.onMousePressed(e);
    }

    @Override
    public void onMouseReleased(MouseInputEvent e){
        transform.accept(e);
        super.onMouseReleased(e);
    }

    @Override
    public void onMouseClicked(MouseInputEvent e){
        transform.accept(e);
        super.onMouseClicked(e);
    }

    @Override
    public void onMouseWheel(MouseInputEvent e){
        transform.accept(e);
        super.onMouseWheel(e);
    }

}

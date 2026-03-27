package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.MouseCaptureBehaviour;
import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.guiLayer.GUIMouseInputTransformer;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

public class PaneGUIInputTranslator extends GUIMouseInputTransformer implements BoundedMouseInputListener{

    private final RectQuery boundary;
    private final MouseCaptureBehaviour mouseCapture;


    PaneGUIInputTranslator(RectQuery boundary){
        super();
        this.boundary = boundary;
        this.mouseCapture = MouseCaptureBehaviour.buildWithDelegateSocket(this);
        setTransform(this::relativizeMouseInputEvent);
    }


    private void relativizeMouseInputEvent(MouseInputEvent event){
        event.setCoordRelativeTo(boundary.getCoord());
    }


    @Override
    public boolean withinBounds(Coord c){
        return boundary.withinBounds(c);
    }

    @Override
    public void setInputSource(GUIInputRegister<BoundedMouseInputListener> inputSource){
        mouseCapture.setInputRegister(inputSource);
        BoundedMouseInputListener.super.setInputSource(inputSource);
        inputSource.addKeyListener(this);
    }

    @Override
    public void onMousePressed(MouseInputEvent e){
        captureMouse();
        super.onMousePressed(e);
    }

    @Override
    public void onMouseReleased(MouseInputEvent e){
        releaseMouse();
        super.onMouseReleased(e);
    }

    protected void captureMouse(){
        mouseCapture.captureMouse();
    }

    protected void releaseMouse(){
        mouseCapture.releaseMouse();
    }

}

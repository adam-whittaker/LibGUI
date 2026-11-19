package com.mason.libgui.components.panes.construction;

import com.mason.libgui.components.behaviour.camera.PanZoomBehaviour;
import com.mason.libgui.components.behaviour.camera.ViewportMouseInputCapturer;
import com.mason.libstruct.setters.ConsumerSetter;

import java.awt.*;

public class PanZoomPaneSkeleton extends PaneSkeleton{


    private PanZoomBehaviour behaviour;
    private ViewportMouseInputCapturer viewportMouseInputCapturer;
    private ConsumerSetter<Graphics2D> renderableSetter;


    public PanZoomPaneSkeleton(){
        super();
    }


    public ConsumerSetter<Graphics2D> getRenderableSetter(){
        if(renderableSetter == null){
            throw new IllegalStateException("renderableSetter is not set");
        }
        return renderableSetter;
    }

    public void setRenderableSetter(ConsumerSetter<Graphics2D> renderableSetter){
        if(this.renderableSetter != null){
            throw new IllegalStateException("renderableSetter is already set");
        }
        this.renderableSetter = renderableSetter;
    }

    public PanZoomBehaviour getBehaviour(){
        if(behaviour == null){
            throw new IllegalStateException("Argument unset!");
        }
        return behaviour;
    }

    public void setBehaviour(PanZoomBehaviour behaviour){
        if(this.behaviour != null){
            throw new IllegalStateException("behaviour is already set");
        }
        this.behaviour = behaviour;
    }

    public ViewportMouseInputCapturer getViewportMouseInputCapturer(){
        if(viewportMouseInputCapturer == null){
            throw new IllegalStateException("Argument unset!");
        }
        return viewportMouseInputCapturer;
    }

    public void setViewportMouseInputCapturer(ViewportMouseInputCapturer viewportMouseInputCapturer){
        if(this.viewportMouseInputCapturer != null){
            throw new IllegalStateException("viewportMouseInputCapturer is already set");
        }
        this.viewportMouseInputCapturer = viewportMouseInputCapturer;
    }


}

package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.camera.PanZoomBehaviour;
import com.mason.libgui.components.behaviour.camera.ViewportMouseInputCapturer;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.componentManagement.UIComponentManager;

import java.awt.*;
import java.util.function.Consumer;

public class PanZoomPaneSkeleton extends PaneSkeleton{


    private PanZoomBehaviour behaviour;
    private ViewportMouseInputCapturer viewportMouseInputCapturer;
    private final RenderReference renderReference;


    public PanZoomPaneSkeleton(){
        super();
        renderReference = new RenderReference();
    }


    public PanZoomBehaviour getBehaviour(){
        if(behaviour == null){
            throw new IllegalStateException("Argument unset!");
        }
        return behaviour;
    }

    public void setBehaviour(PanZoomBehaviour behaviour){
        this.behaviour = behaviour;
    }


    public ViewportMouseInputCapturer getViewportMouseInputCapturer(){
        if(viewportMouseInputCapturer == null){
            throw new IllegalStateException("Argument unset!");
        }
        return viewportMouseInputCapturer;
    }

    public void setViewportMouseInputCapturer(ViewportMouseInputCapturer viewportMouseInputCapturer){
        this.viewportMouseInputCapturer = viewportMouseInputCapturer;
    }

    public Consumer<Graphics2D> getRenderable(){
        return renderReference;
    }

    public void wirePaneToRenderReference(Consumer<Graphics2D> renderable){
        renderReference.setRenderable(renderable);
    }


    private static final class RenderReference implements Consumer<Graphics2D>{


        Consumer<Graphics2D> renderable = (g) -> {
            throw new IllegalStateException("Render reference for PanZoomPane unset.");
        };

        void setRenderable(Consumer<Graphics2D> renderable){
            this.renderable = renderable;
        }

        @Override
        public void accept(Graphics2D g){
            renderable.accept(g);
        }

    }

}

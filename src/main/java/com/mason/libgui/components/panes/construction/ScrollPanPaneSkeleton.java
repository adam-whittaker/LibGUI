package com.mason.libgui.components.panes.construction;

import com.mason.libgui.components.behaviour.camera.ScrollPanBehaviour;
import com.mason.libstruct.setters.ConsumerSetter;

import java.awt.*;

public class ScrollPanPaneSkeleton extends PaneSkeleton{


    private ScrollPanBehaviour behaviour;
    private ConsumerSetter<Graphics2D> renderableSetter;


    public ScrollPanPaneSkeleton(){
        super();
    }


    public ScrollPanBehaviour getBehaviour(){
        if(behaviour == null){
            throw new IllegalStateException("behaviour is not set");
        }
        return behaviour;
    }

    public void setBehaviour(ScrollPanBehaviour behaviour){
        if(this.behaviour != null){
            throw new IllegalStateException("behaviour is already set");
        }
        this.behaviour = behaviour;
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

}

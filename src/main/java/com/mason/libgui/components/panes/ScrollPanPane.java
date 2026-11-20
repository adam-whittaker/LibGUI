package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.camera.ScrollPanBehaviour;
import com.mason.libgui.components.panes.construction.ScrollPanPaneSkeleton;
import com.mason.libstruct.setters.ConsumerSetter;

import java.awt.*;

public class ScrollPanPane extends Pane{


    private final ScrollPanBehaviour behaviour;


    protected ScrollPanPane(ScrollPanPaneSkeleton skeleton){
        super(skeleton);
        this.behaviour = skeleton.getBehaviour();
        this.behaviour.setInputSource(skeleton.getComponentManager().getInputDistributor());
        ConsumerSetter<Graphics2D> renderableSetter = skeleton.getRenderableSetter();
        renderableSetter.setConsumer(this::renderComponents);
    }


    @Override
    protected void renderAfterTranslation(Graphics2D g){
        drawBackground(g);
        behaviour.renderAfterTranslation(g);
        drawForeground(g);
    }

    public void setScrollSpeed(double scrollSpeed){
        behaviour.setSpeed(scrollSpeed);
    }

}

package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.camera.PanZoomBehaviour;
import com.mason.libgui.components.behaviour.camera.Zoom;
import com.mason.libgui.components.panes.construction.PanZoomPaneFactory;
import com.mason.libgui.components.panes.construction.PanZoomPaneSkeleton;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libstruct.interfaces.RectQuery;
import com.mason.libstruct.setters.ConsumerSetter;

import java.awt.*;

public class PanZoomPane extends Pane{


    private final PanZoomBehaviour panZoomBehaviour;


    protected PanZoomPane(PanZoomPaneSkeleton skeleton){
        super(skeleton);
        this.panZoomBehaviour = skeleton.getBehaviour();
        panZoomBehaviour.setInputSource(skeleton.getComponentManager().getInputDistributor());
        ConsumerSetter<Graphics2D> renderableSetter = skeleton.getRenderableSetter();
        renderableSetter.setConsumer(this::renderComponents);
    }

    public static PanZoomPane build(HitboxRect boundary, RectQuery clampingRect, RectQuery initialView, Zoom zoom){
        PanZoomPaneFactory<PanZoomPane> factory = new PanZoomPaneFactory<>(PanZoomPane::new);
        return factory.build(boundary, clampingRect, initialView, zoom);
    }


    @Override
    protected void renderAfterTranslation(Graphics2D g){
        drawBackground(g);
        panZoomBehaviour.renderAfterTranslation(g);
        drawForeground(g);
    }

    public void recentreView(){
        panZoomBehaviour.recentreView();
    }

}

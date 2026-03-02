package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.camera.PanZoomBehaviour;
import com.mason.libgui.components.behaviour.camera.Zoom;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.utils.structures.RectQuery;

import java.awt.*;
import java.awt.event.KeyListener;

public class PanZoomPane extends Pane{


    private final PanZoomBehaviour panZoomBehaviour;


    protected PanZoomPane(PanZoomPaneSkeleton skeleton){
        super(skeleton);
        this.panZoomBehaviour = skeleton.getBehaviour();
        wireUpPaneFromSkeleton(skeleton);
    }

    private void wireUpPaneFromSkeleton(PanZoomPaneSkeleton skeleton){
        panZoomBehaviour.setInputSource(skeleton.getComponentManager().getInputDistributor());
        skeleton.wirePaneToRenderReference(this::renderComponents);
    }

    public static PanZoomPane buildFullyZoomedOutPane(HitboxRect boundary){
        return new PanZoomPane(PanZoomPaneBuilder.buildSkeletonWithFullyZoomedOutBehaviour(boundary));
    }

    public static PanZoomPane build(HitboxRect boundary, RectQuery initialView, RectQuery clampingRect, Zoom zoom){
        return new PanZoomPane(PanZoomPaneBuilder.buildSkeleton(boundary, initialView, clampingRect, zoom));
    }


    @Override
    protected void renderAfterTranslation(Graphics2D g){
        drawBackground(g);
        panZoomBehaviour.renderAfterTranslation(g);
        drawForeground(g);
    }

}

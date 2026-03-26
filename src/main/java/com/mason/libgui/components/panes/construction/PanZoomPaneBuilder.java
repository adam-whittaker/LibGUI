package com.mason.libgui.components.panes.construction;

import com.mason.libgui.components.behaviour.camera.*;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.componentManagement.SimpleUIComponentContainer;
import com.mason.libgui.core.componentManagement.UIComponentManager;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

public class PanZoomPaneBuilder{


    private interface BehaviourFactory{

        PanZoomBehaviour constructBehaviour(PanZoomPaneSkeleton skeleton);

    }


    public static PanZoomPaneSkeleton buildSkeleton(HitboxRect boundary, RectQuery initialView, RectQuery clampingRect, Zoom zoom){
        BehaviourFactory behaviourFactory = buildBehaviourFactory(initialView, clampingRect, zoom);
        return buildSkeletonUsingBehaviourFactory(boundary, behaviourFactory);
    }

    private static BehaviourFactory buildBehaviourFactory(RectQuery initialView, RectQuery clampingRect, Zoom zoom){
        return (skeleton) -> {
            return PanZoomBehaviour.buildBehaviour(skeleton.getRenderable(),
                    clampingRect,
                    initialView,
                    zoom,
                    skeleton.getViewportMouseInputCapturer());
        };
    }

    private static PanZoomPaneSkeleton buildSkeletonUsingBehaviourFactory(HitboxRect boundary, BehaviourFactory factory){
        PanZoomPaneSkeleton skeleton = new PanZoomPaneSkeleton();
        skeleton.setBoundary(boundary);
        skeleton.setViewportMouseInputCapturer(new ViewportMouseInputCapturer());
        PanZoomBehaviour behaviour = factory.constructBehaviour(skeleton);
        UIComponentManager componentManager = buildViewportComponentManager(behaviour, skeleton.getViewportMouseInputCapturer());
        skeleton.setComponentManager(componentManager);
        skeleton.setBehaviour(behaviour);
        return skeleton;
    }

    private static UIComponentManager buildViewportComponentManager(PanZoomBehaviour behaviour, ViewportMouseInputCapturer viewportCapturer){
        ViewportInputDistributor inputDistributor = new ViewportInputDistributor(behaviour, viewportCapturer);
        return UIComponentManager.buildUIComponentManager(new SimpleUIComponentContainer(), inputDistributor);
    }


    public static PanZoomPaneSkeleton buildSkeletonWithFullyZoomedOutBehaviour(HitboxRect boundary){
        BehaviourFactory behaviourFactory = buildFullyZoomedOutBehaviourFactory(boundary);
        return buildSkeletonUsingBehaviourFactory(boundary, behaviourFactory);
    }

    private static BehaviourFactory buildFullyZoomedOutBehaviourFactory(RectQuery clampingRect){
        return (skeleton) -> {
            return PanZoomBehaviour.buildFullyZoomedOutBehaviour(skeleton.getRenderable(),
                    clampingRect,
                    skeleton.getViewportMouseInputCapturer());
        };
    }

}

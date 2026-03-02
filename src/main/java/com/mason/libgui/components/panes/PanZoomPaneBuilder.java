package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.camera.*;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.componentManagement.SimpleUIComponentContainer;
import com.mason.libgui.core.componentManagement.UIComponentManager;
import com.mason.libgui.utils.structures.RectQuery;

public class PanZoomPaneBuilder{


    private interface BehaviourFactory{

        PanZoomBehaviour constructBehaviour(PanZoomPaneSkeleton skeleton);

    }


    /*public PanZoomPaneSkeleton buildSkeleton(HitboxRect boundary, RectQuery initialView, RectQuery clampingRect, Zoom zoom){
        BehaviourFactory behaviourFactory = buildStandardBehaviourFactory(clampingRect, initialView, zoom);
        return buildUsingBehaviourFactory(boundary, behaviourFactory);
    }

    private BehaviourFactory buildStandardBehaviourFactory(RectQuery clampingRect, RectQuery initialView, Zoom zoom){
        return (renderReference, viewportCapturer) -> {
            return PanZoomBehaviour.buildBehaviour(renderReference, clampingRect, initialView, zoom, viewportCapturer);
        };
    }

    private PanZoomPaneSkeleton buildUsingBehaviourFactory(HitboxRect boundary, BehaviourFactory behaviourFactory){
        RenderReference renderReference = new RenderReference();
        ViewportMouseInputCapturer viewportCapturer = new ViewportMouseInputCapturer();
        PanZoomBehaviour behaviour = behaviourFactory.build(renderReference, viewportCapturer);
        PanZoomPaneSkeleton skeleton = buildUnreferencedPaneFromBehaviour(boundary, behaviour, viewportCapturer);
        renderReference.setPane(pane);
        return pane;
    }

    private PanZoomPaneSkeleton buildUnreferencedPaneFromBehaviour(HitboxRect boundary, PanZoomBehaviour behaviour, ViewportMouseInputCapturer viewportCapturer){
        UIComponentManager componentManager = buildViewportComponentManager(behaviour, viewportCapturer);
        PanZoomPaneSkeleton skeleton = buildSkeleton(boundary, behaviour, componentManager);
        return constructor.constructUnwiredPane(skeleton);
    }

    private static UIComponentManager buildViewportComponentManager(PanZoomBehaviour behaviour, ViewportMouseInputCapturer viewportCapturer){
        ViewportInputDistributor inputDistributor = new ViewportInputDistributor(behaviour, viewportCapturer);
        return UIComponentManager.buildUIComponentManager(new SimpleUIComponentContainer(), inputDistributor);
    }

    private static PanZoomPaneSkeleton buildSkeleton(HitboxRect boundary, PanZoomBehaviour behaviour, UIComponentManager componentManager){
        PanZoomPaneSkeleton skeleton = new PanZoomPaneSkeleton();
        skeleton.setBehaviour(behaviour);
        skeleton.setBoundary(boundary);
        skeleton.setComponentManager(componentManager);
        return skeleton;
    }


    public PanZoomPaneSkeleton buildFullyZoomedOutPane(HitboxRect boundary){
        BehaviourFactory behaviourFactory = buildFullyZoomedOutBehaviourFactory(boundary);
        return buildUsingBehaviourFactory(boundary, behaviourFactory);
    }

    private BehaviourFactory buildFullyZoomedOutBehaviourFactory(RectQuery clampingRect){
        return (renderReference, viewportCapturer) -> {
            return PanZoomBehaviour.buildFullyZoomedOutDefaultBehaviour(renderReference, clampingRect, viewportCapturer);
        };
    }


    public interface PaneConstructor<T>{

        T constructUnwiredPane(PanZoomPaneSkeleton skeleton);

    }

    private interface BehaviourFactory{

        PanZoomBehaviour build(RenderReference renderReference, ViewportMouseInputCapturer viewportCapturer);

    }*/


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

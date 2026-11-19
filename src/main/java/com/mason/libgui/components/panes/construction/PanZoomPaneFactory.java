package com.mason.libgui.components.panes.construction;

import com.mason.libgui.components.behaviour.camera.*;
import com.mason.libgui.components.panes.PanZoomPane;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.componentManagement.SimpleUIComponentContainer;
import com.mason.libgui.core.componentManagement.UIComponentManager;
import com.mason.libstruct.interfaces.RectQuery;
import com.mason.libstruct.setters.ConsumerSetter;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PanZoomPaneFactory<T extends PanZoomPane>{


    private final ConsumerSetter.ConsumerSlot<Graphics2D> renderSlot;
    private final PaneConstructor<T> paneConstructor;

    private Supplier<ViewportMouseInputCapturer> viewportCapturerFactory;
    private BehaviourFactory behaviourFactory;
    private ComponentManagerFactory componentManagerFactory;


    public PanZoomPaneFactory(PaneConstructor<T> paneConstructor){
        this.paneConstructor = paneConstructor;
        renderSlot = new ConsumerSetter.ConsumerSlot<>();
        viewportCapturerFactory = ViewportMouseInputCapturer::new;
        behaviourFactory = PanZoomBehaviour::buildBehaviour;
        componentManagerFactory = PanZoomPaneFactory::buildViewportComponentManager;
    }

    private static UIComponentManager buildViewportComponentManager(ViewportMouseInputCapturer viewportCapturer, PanZoomBehaviour behaviour){
        ViewportInputDistributor inputDistributor = new ViewportInputDistributor(behaviour, viewportCapturer);
        return UIComponentManager.buildUIComponentManager(new SimpleUIComponentContainer(), inputDistributor);
    }


    public void setViewportCapturerFactory(Supplier<ViewportMouseInputCapturer> viewportCapturerFactory){
        this.viewportCapturerFactory = viewportCapturerFactory;
    }

    public void setBehaviourFactory(BehaviourFactory behaviourFactory){
        this.behaviourFactory = behaviourFactory;
    }

    public void setComponentManagerFactory(ComponentManagerFactory componentManagerFactory){
        this.componentManagerFactory = componentManagerFactory;
    }


    public T build(HitboxRect boundary, RectQuery clampingRect, RectQuery initialView, Zoom zoom){
        ViewportMouseInputCapturer capturer = viewportCapturerFactory.get();
        PanZoomBehaviour behaviour = behaviourFactory.build(
                renderSlot,
                clampingRect,
                initialView,
                zoom,
                capturer
        );
        UIComponentManager componentManager = componentManagerFactory.build(capturer, behaviour);

        PanZoomPaneSkeleton skeleton = new PanZoomPaneSkeleton();
        skeleton.setBoundary(boundary);
        skeleton.setBehaviour(behaviour);
        skeleton.setRenderableSetter(renderSlot);
        skeleton.setViewportMouseInputCapturer(capturer);
        skeleton.setComponentManager(componentManager);
        return paneConstructor.construct(skeleton);
    }


    public interface BehaviourFactory{

        PanZoomBehaviour build(
                Consumer<Graphics2D> renderable,
                RectQuery clampingRect,
                RectQuery initialView,
                Zoom zoom,
                ViewportMouseInputCapturer capturer
        );

    }

    public interface ComponentManagerFactory{

        UIComponentManager build(ViewportMouseInputCapturer capturer, PanZoomBehaviour behaviour);

    }

    public interface PaneConstructor<T>{

        T construct(PanZoomPaneSkeleton skeleton);

    }

}

package com.mason.libgui.components.behaviour.camera;

import com.mason.libgui.components.behaviour.drag.AbstractDragBehaviour;
import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;

import java.awt.*;
import java.util.function.Consumer;

public class PanZoomBehaviour extends AbstractDragBehaviour{


    private final Viewport viewport;
    private ApparentCoord initialDragApparentCoord;


    private PanZoomBehaviour(Viewport viewport, ViewportMouseInputCapturer capturer){
        super(capturer);
        this.viewport = viewport;
    }


    public static PanZoomBehaviour buildBehaviour(Consumer<Graphics2D> renderable, RectQuery clampingRect, RectQuery initialView, Zoom zoom, ViewportMouseInputCapturer capturer){
        Viewport viewport = Viewport.buildViewport(renderable, clampingRect, initialView, zoom);
        return new PanZoomBehaviour(viewport, capturer);
    }

    public static PanZoomBehaviour buildFullyZoomedOutDefaultBehaviour(Consumer<Graphics2D> renderable, RectQuery clampingRect, ViewportMouseInputCapturer capturer){
        Viewport viewport = Viewport.buildViewportWithDefaultZoomAndInitialView(renderable, clampingRect);
        return new PanZoomBehaviour(viewport, capturer);
    }


    @Override
    public boolean withinBounds(Coord c){
        return true;
    }

    protected ApparentCoord screenToApparent(Coord screen){
        return viewport.screenToApparent(screen);
    }

    public void renderAfterTranslation(Graphics2D g){
        viewport.renderAfterTranslation(g);
    }


    //Zoom
    @Override
    public void onMouseWheel(MouseInputEvent event){
        ApparentCoord apparentAtMouse = viewport.screenToApparent(event.getCoord());
        double oldZoom = viewport.getZoom();
        viewport.mouseWheel(event);
        double newZoom = viewport.getZoom();
        recalculateViewAfterZoom(newZoom/oldZoom, apparentAtMouse, event.getCoord());
    }

    private void recalculateViewAfterZoom(double zoomFactorChange, ApparentCoord apparentAtMouse, Coord screenCoordOfMouse){
        viewport.resizeAfterZoom(zoomFactorChange);
        moveApparentCoordToScreenCoord(apparentAtMouse, screenCoordOfMouse);
        viewport.clampWithinBoundary();
    }

    private void moveApparentCoordToScreenCoord(ApparentCoord apparent, Coord screen){
        double zoom = viewport.getZoom();
        double newTopLeftX = apparent.x() - screen.x()/zoom;
        double newTopLeftY = apparent.y() - screen.y()/zoom;
        viewport.setPreciseTopLeft(new ApparentCoord(newTopLeftX, newTopLeftY));
    }


    //Drag
    @Override
    protected void onDragStart(MouseInputEvent event){
        initialDragApparentCoord = viewport.screenToApparent(event.getCoord());
    }

    @Override
    protected void onDragIncrement(MouseInputEvent event){
        moveApparentCoordToScreenCoord(initialDragApparentCoord, event.getCoord());
        viewport.clampWithinBoundary();
    }

    @Override
    protected void onDragRelease(MouseInputEvent event){}


    @Override
    public void setInputSource(GUIInputRegister<BoundedMouseInputListener> inputSource){
        getMouseCaptureBehaviour().setInputRegister(inputSource);
    }

}

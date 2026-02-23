package com.mason.libgui.components.behaviour.camera;

import com.mason.libgui.components.behaviour.GraphicsTransformBehaviour;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;
import com.mason.libgui.utils.structures.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.Consumer;

public class Viewport{


    private final RectQuery clampingRect;
    private final ViewDoubleRect view;
    private final Zoom zoom;
    private final GraphicsTransformBehaviour graphicsTransformer;


    private Viewport(Consumer<Graphics2D> renderable, RectQuery boundingRect, RectQuery initialView, Zoom zoom){
        this.clampingRect = boundingRect;
        this.view = new ViewDoubleRect(initialView);
        this.zoom = zoom;
        graphicsTransformer = GraphicsTransformBehaviour.buildTransformOnlyBehaviour(renderable, this::constructTransform);
    }

    private AffineTransform constructTransform(){
        double zoom = getZoom();
        AffineTransform transform = AffineTransform.getScaleInstance(zoom, zoom);
        transform.translate(-view.xInt(), -view.yInt());
        return transform;
    }

    static Viewport buildViewport(Consumer<Graphics2D> renderable, RectQuery boundingRect, RectQuery initialView, Zoom zoom){
        return new Viewport(renderable, boundingRect, initialView, zoom);
    }

    static Viewport buildViewportWithDefaultZoomAndInitialView(Consumer<Graphics2D> renderable, RectQuery boundingRect){
        Rect initialView = Rect.buildRect(new Coord(0, 0), boundingRect.getSize());
        return new Viewport(renderable, boundingRect, initialView, Zoom.buildDefaultFullyZoomedOutZoom());
    }


    Coord apparentToScreen(ApparentCoord apparent){
        double zoom = getZoom();
        ApparentCoord topLeft = view.getPreciseCoord();
        int x = (int)((apparent.x() - topLeft.x()) * zoom);
        int y = (int)((apparent.y() - topLeft.y()) * zoom);
        return new Coord(x, y);
    }

    ApparentCoord screenToApparent(Coord screen){
        double zoom = getZoom();
        ApparentCoord topLeft = view.getPreciseCoord();
        double x = screen.x()/zoom + topLeft.x();
        double y = screen.y()/zoom + topLeft.y();
        return new ApparentCoord(x, y);
    }

    double getZoom(){
        return zoom.getZoom();
    }


    void mouseWheel(MouseInputEvent event){
        zoom.mouseWheel(event);
    }

    void resizeAfterZoom(double zoomFactorChange){
        view.resizeAfterZoom(zoomFactorChange);
    }

    void clampWithinBoundary(){
        view.clampWithinBoundary(clampingRect);
    }


    void setPreciseTopLeft(ApparentCoord c){
        view.setPreciseCoord(c);
    }

    ApparentCoord getPreciseTopLeft(){
        return view.getPreciseCoord();
    }


    void renderAfterTranslation(Graphics2D g){
        graphicsTransformer.transformAndRender(g);
    }

}

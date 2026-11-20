package com.mason.libgui.components.behaviour.camera;

import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.interfaces.Boundable;
import com.mason.libstruct.interfaces.RectQuery;

import java.awt.*;
import java.util.function.Consumer;

public class ScrollPanBehaviour implements BoundedMouseInputListener, ViewportBehaviour{


    private final Viewport viewport;
    private final boolean vertical;
    private final Boundable scrollInputBounds;
    private double speed;


    public ScrollPanBehaviour(
            Consumer<Graphics2D> renderable,
            Boundable scrollInputBounds,
            RectQuery clampingRect,
            RectQuery initialView,
            boolean vertical
    ){
        viewport = Viewport.buildViewport(renderable, clampingRect, initialView, Zoom.buildFixedZoom(1));
        this.vertical = vertical;
        this.scrollInputBounds = scrollInputBounds;
        this.speed = 60;
    }

    public static ScrollPanBehaviour buildDefaultVerticalBehaviour(
            Consumer<Graphics2D> renderable,
            RectQuery clampingRect,
            RectQuery initialView){
        return new ScrollPanBehaviour(
                renderable,
                (coord) -> true,
                clampingRect,
                initialView,
                true
        );
    }

    public static ScrollPanBehaviour buildDefaultHorizontalBehaviour(
            Consumer<Graphics2D> renderable,
            RectQuery clampingRect,
            RectQuery initialView){
        return new ScrollPanBehaviour(
                renderable,
                (coord) -> true,
                clampingRect,
                initialView,
                false
        );
    }


    @Override
    public boolean withinBounds(Coord coord){
        return scrollInputBounds.withinBounds(coord);
    }

    @Override
    public ApparentCoord screenToApparent(Coord screen){
        return viewport.screenToApparent(screen);
    }

    public void renderAfterTranslation(Graphics2D g){
        viewport.renderAfterTranslation(g);
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }


    @Override
    public void onMouseWheel(MouseInputEvent e){
        ApparentCoord viewCoord = viewport.getPreciseTopLeft();
        if(vertical){
            viewport.setPreciseTopLeft(new ApparentCoord(viewCoord.x(), viewCoord.y() + e.getWheelMotion()*speed));
        }else{
            viewport.setPreciseTopLeft(new ApparentCoord(viewCoord.x() + e.getWheelMotion()*speed, viewCoord.y()));
        }
        viewport.clampWithinBoundary();
    }

}

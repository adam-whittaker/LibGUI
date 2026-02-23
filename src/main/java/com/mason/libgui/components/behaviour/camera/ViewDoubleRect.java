package com.mason.libgui.components.behaviour.camera;

import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;

public class ViewDoubleRect{


    private double x, y, width, height;


    ViewDoubleRect(RectQuery rect){
        x = rect.getCoord().x();
        y = rect.getCoord().y();
        width = rect.getSize().width();
        height = rect.getSize().height();
    }


    int xInt(){
        return (int) x;
    }

    int yInt(){
        return (int) y;
    }

    void resizeAfterZoom(double zoomFactorChange){
        width /= zoomFactorChange;
        height /= zoomFactorChange;
    }

    ApparentCoord getPreciseCoord(){
       return new ApparentCoord(x, y);
    }

    void setPreciseCoord(ApparentCoord coord){
        x = coord.x();
        y = coord.y();
    }

    void clampWithinBoundary(RectQuery boundary){
        clampX(boundary);
        clampY(boundary);
    }

    private void clampX(RectQuery boundary){
        int boundaryX = boundary.getCoord().x();
        int boundaryWidth = boundary.getSize().width();
        if(x < boundaryX){
            x = boundaryX;
        }else if(x > boundaryX + boundaryWidth - width){
            x = boundaryX + boundaryWidth - width;
        }
    }

    private void clampY(RectQuery boundary){
        int boundaryY = boundary.getCoord().y();
        int boundaryHeight = boundary.getSize().height();
        if(y < boundaryY){
            y = boundaryY;
        }else if(y > boundaryY + boundaryHeight - height){
            y = boundaryY + boundaryHeight - height;
        }
    }

}

package com.mason.libgui.core.component.hitbox;

import com.mason.libgui.utils.structures.*;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

public interface HitboxRect extends Hitbox, RectQuery{


    default void clampWithinBoundary(RectQuery boundary){
        int newX = getClampedX(boundary);
        int newY = getClampedY(boundary);
        setCoord(new Coord(newX, newY));
    }

    private int getClampedX(RectQuery boundary){
        int x = x();
        int boundaryX = boundary.x();
        int boundaryWidth = boundary.width();
        if(x < boundaryX){
            x = boundaryX;
        }else if(x > boundaryX + boundaryWidth - width()){
            x = boundaryX + boundaryWidth - width();
        }
        return x;
    }

    private int getClampedY(RectQuery boundary){
        int y = y();
        int boundaryY = boundary.getCoord().y();
        int boundaryHeight = boundary.getSize().height();
        if(y < boundaryY){
            y = boundaryY;
        }else if(y > boundaryY + boundaryHeight - height()){
            y = boundaryY + boundaryHeight - height();
        }
        return y;
    }


    static HitboxRect fromRect(RectQuery rect){
        return build(rect.getCoord(), rect.getSize());
    }

    static HitboxRect build(int x, int y, int width, int height){
        return fromRect(new Rect(x, y, width, height));
    }

    static HitboxRect build(Coord coord, Size size){
        return new BasicHitboxRect(coord, size);
    }

}

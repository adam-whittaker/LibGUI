package com.mason.libgui.core.input.mouse;

import com.mason.libgui.utils.structures.Coord;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseInputEvent{


    private final MouseEvent event;
    private final MouseInputType type;
    private Coord coord;
    private final RejectedFlag rejectedFlag;


    public MouseInputEvent(MouseEvent event){
        this(event, new RejectedFlag());
    }

    private MouseInputEvent(MouseEvent event, RejectedFlag rejectedFlag){
        this.event = event;
        coord = new Coord(event.getX(), event.getY());
        type = MouseInputType.fromRawMouseEvent(event);
        this.rejectedFlag = rejectedFlag;
    }


    public Coord getCoord(){
        return coord;
    }

    public void setCoord(Coord c){
        coord = c;
    }

    public MouseInputEvent changedCoordMask(Coord newCoord){
        MouseInputEvent mask = new MouseInputEvent(event, rejectedFlag);
        mask.setCoord(newCoord);
        return mask;
    }

    public boolean isRejected(){
        return rejectedFlag.isRejected();
    }

    public void reject(){
        rejectedFlag.reject();
    }

    public void unreject(){
        rejectedFlag.unreject();
    }


    public MouseInputType getType(){
        return type;
    }

    public void setCoordRelativeTo(Coord topLeft){
        coord = new Coord(coord.x() - topLeft.x(), coord.y() - topLeft.y());
    }


    public int getWheelMotion(){
        validateTypeIsMouseWheel();
        return ((MouseWheelEvent) event).getWheelRotation();
    }

    private void validateTypeIsMouseWheel(){
        if(!type.equals(MouseInputType.WHEEL)){
            throw new IllegalStateException("Attempting to get wheel state of non-wheel event");
        }
    }


    private static class RejectedFlag{

        boolean rejected = false;

        boolean isRejected(){
            return rejected;
        }

        void reject(){
            rejected = true;
        }

        void unreject(){
            rejected = false;
        }

    }

}

package com.mason.libgui.components.behaviour.drag;

import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.geo.Size;
import com.mason.libstruct.interfaces.Movable;
import com.mason.libstruct.interfaces.RectQuery;

public class ClampedDragBehaviour extends StandardDragBehaviour{


    private final Movable dragRect;
    private final HitboxRect tempRect;
    private final RectQuery clampRect;


    public ClampedDragBehaviour(HitboxRect dragRect, RectQuery clampRect){
        super(dragRect);
        this.dragRect = dragRect;
        this.tempRect = maskSizeOfDragRect(dragRect);
        this.clampRect = clampRect;
    }

    private static HitboxRect maskSizeOfDragRect(HitboxRect dragRect){
        return new HitboxRect(){

            private Coord coord;

            @Override
            public void setCoord(Coord c){
                coord = c;
            }

            @Override
            public Size getSize(){
                return dragRect.getSize();
            }

            @Override
            public Coord getCoord(){
                return coord;
            }
        };

    }


    @Override
    protected void onDragIncrement(MouseInputEvent event){
        Coord newCoord = calculateNewCoord(event.getCoord());
        tempRect.setCoord(newCoord);
        tempRect.clampWithinBoundary(clampRect);
        dragRect.setCoord(tempRect.getCoord());
    }

}

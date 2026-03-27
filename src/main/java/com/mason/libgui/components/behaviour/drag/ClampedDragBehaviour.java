package com.mason.libgui.components.behaviour.drag;

import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;
import com.mason.libgui.utils.structures.interfaces.Movable;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

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

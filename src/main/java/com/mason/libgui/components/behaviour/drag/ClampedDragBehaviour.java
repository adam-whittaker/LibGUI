package com.mason.libgui.components.behaviour.drag;

import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.core.input.mouse.MouseInputEvent;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;

public class ClampedDragBehaviour extends StandardDragBehaviour{


    private final RectQuery clampRect;


    public ClampedDragBehaviour(HitboxRect dragRect, RectQuery clampRect){
        super(dragRect);
        this.clampRect = clampRect;
    }


    @Override
    protected void onDragIncrement(MouseInputEvent event){
        super.onDragIncrement(event);
        ((HitboxRect)getDragRegion()).clampWithinBoundary(clampRect);
    }

}

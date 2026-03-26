package com.mason.libgui.testHelpers.stubs;

import com.mason.libgui.components.behaviour.drag.StandardDragBehaviour;
import com.mason.libgui.core.component.hitbox.Hitbox;
import com.mason.libgui.utils.structures.interfaces.Boundable;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;

import java.awt.*;

public class DraggableComponent extends DefaultUIComponent{



    public DraggableComponent(Coord topLeft, Size size, Color color){
        super(topLeft, size, color, () -> {}, DraggableComponent::generateBehaviour);
    }

    public DraggableComponent(Coord topLeft, Size size){
        super(topLeft, size, DraggableComponent::generateBehaviour);
    }

    private static StandardDragBehaviour generateBehaviour(Boundable boundable){
        if(!(boundable instanceof Hitbox)){
            throw new IllegalArgumentException("Trying to generate drag behaviour without a hitbox");
        }
        return new StandardDragBehaviour((Hitbox) boundable);
    }

}

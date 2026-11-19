package com.mason.libgui.core.componentManagement.sourced;

import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.componentManagement.IterableUIComponentContainer;

import java.util.Iterator;

public class SourcedInteractiveContainer implements IterableUIComponentContainer{


    private final Iterable<? extends UIComponent> source;


    public SourcedInteractiveContainer(Iterable<? extends UIComponent> source){
        this.source = source;
    }


    @Override
    public void addComponent(UIComponent comp){
        throw new IllegalStateException("Should not be externally modifying a sourced container");
    }

    @Override
    public void removeComponent(UIComponent comp){
        throw new IllegalStateException("Should not be externally modifying a sourced container");
    }

    @Override
    public Iterator<UIComponent> iterator(){
        Iterator<? extends UIComponent> iter = source.iterator();
        return new Iterator<>(){

            @Override
            public boolean hasNext(){
                return iter.hasNext();
            }

            @Override
            public UIComponent next(){
                return iter.next();
            }
        };
    }

}

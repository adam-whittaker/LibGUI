package com.mason.libgui.construction;

import java.util.Set;

public abstract class ComponentBuildResource<T> extends BuildResource<T> implements BuildComponent{


    public ComponentBuildResource(){
        super();
    }


    @Override
    public Set<BuildResource<?>> suppliedBuildResources(){
        return Set.of(this);
    }

}

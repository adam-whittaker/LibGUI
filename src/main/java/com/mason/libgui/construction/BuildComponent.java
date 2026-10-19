package com.mason.libgui.construction;

import java.util.Set;

public interface BuildComponent{


    default Set<BuildResource<?>> requiredPreBuildResources(){
        return Set.of();
    }

    Set<BuildResource<?>> suppliedBuildResources();

    void ensureBuilt();

    default void postBuild(){}


    static <T> BuildComponent getBasicResourceComponent(BuildResource<T> resource){
        return new BuildComponent(){

            @Override
            public Set<BuildResource<?>> requiredPreBuildResources(){
                return Set.of();
            }

            @Override
            public Set<BuildResource<?>> suppliedBuildResources(){
                return Set.of(resource);
            }

            @Override
            public void ensureBuilt(){
                resource.ensureBuilt();
            }

            @Override
            public void postBuild(){}

            @Override
            public String toString(){
                return resource.toString();
            }
        };
    }

}

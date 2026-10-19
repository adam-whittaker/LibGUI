package com.mason.libgui.construction;

import java.util.*;

public class BuildComponentCollection implements Iterable<BuildComponent>{


    private Collection<BuildComponent> components;
    private boolean sorted;


    public BuildComponentCollection(){
        components = new ArrayList<>();
        sorted = false;
    }


    public void addComponent(BuildComponent component){
        components.add(component);
        sorted = false;
    }

    public <T> void addResource(BuildResource<T> resource){
        BuildComponent component = BuildComponent.getBasicResourceComponent(resource);
        addComponent(component);
    }

    public void ensureSorted(){
        if(!sorted){
            components = PreBuildRequirementsSort.sort(components);
            sorted = true;
        }
    }


    @Override
    public Iterator<BuildComponent> iterator(){
        return components.iterator();
    }

}

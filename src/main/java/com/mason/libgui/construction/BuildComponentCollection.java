package com.mason.libgui.construction;

import java.util.*;
import java.util.function.Supplier;

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

    public void addResource(BuildResource resource){
        BuildComponent component = createBuildlessResourceComponent(resource);
        addComponent(component);
    }

    public <T> void addPlainObjectResource(String name, T obj){
        BuildResource resource = new PlainObjectResource<>(name, obj);
        addResource(resource);
    }

    private BuildComponent createBuildlessResourceComponent(BuildResource resource){
        Set<String> nameSet = Set.of(resource.name());
        Set<BuildResource> resourceSet = Set.of(resource);
        return new PureResourceBuildComponent(nameSet){

            @Override
            public void build(){}

            @Override
            public Collection<BuildResource> supplyResources(){
                return resourceSet;
            }
        };
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


    private record PlainObjectResource<T>(String name, T resource) implements BuildResource, Supplier<T>{

        @Override
        public T get(){
            return resource;
        }

    }

}

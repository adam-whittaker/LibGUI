package com.mason.libgui.construction;

import java.util.*;

public class ResourceRegister{


    private final Map<String, Optional<BuildResource>> resources;


    public ResourceRegister(){
        resources = new HashMap<>();
    }


    void registerName(String resourceName){
        resources.put(resourceName, Optional.empty());
    }

    Set<String> getNames(){
        return resources.keySet();
    }

    void acceptResource(BuildResource resource){
        if(!resources.containsKey(resource.name())){
            throw new IllegalArgumentException("Resource " + resource.name() + " not registered!");
        }
        resources.put(resource.name(), Optional.of(resource));
    }

    BuildResource getResource(String resourceName){
        Optional<BuildResource> resource = resources.get(resourceName);
        if(resource.isEmpty()){
            throw new IllegalStateException("Resource " + resourceName + " not present.");
        }
        return resource.get();
    }

}

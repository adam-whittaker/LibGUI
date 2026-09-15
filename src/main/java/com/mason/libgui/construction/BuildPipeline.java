package com.mason.libgui.construction;

import com.mason.libgui.components.panes.layout.PaneLayout;
import com.mason.libgui.core.componentManagement.UIComponentContainer;

import java.util.*;

public class BuildPipeline{


    private final Map<String, BuildResource> extantResources;
    private final BuildComponentCollection components;


    public BuildPipeline(BuildComponentCollection components){
        this.extantResources = new HashMap<>();
        this.components = components;
        components.ensureSorted();
    }


    public void build(){
        for(BuildComponent component : components){
            feedPreBuildResources(component);
            component.build();
            registerResources(component);
        }
        for(BuildComponent component : components){
            feedPostBuildResources(component);
        }
    }

    private void feedPreBuildResources(BuildComponent component){
        for(String resourceName : component.requiredPreBuildResourceNames()){
            BuildResource resource = extantResources.get(resourceName);
            component.acceptPreBuildResource(resource);
        }
    }

    private void registerResources(BuildComponent component){
        for(BuildResource resource : component.supplyResources()){
            extantResources.put(resource.name(), resource);
        }
    }

    private void feedPostBuildResources(BuildComponent component){
        for(String resourceName : component.requiredPostBuildResourceNames()){
            BuildResource resource = extantResources.get(resourceName);
            component.acceptPostBuildResource(resource);
        }
    }


    public void layout(PaneLayout layout){
        for(BuildComponent component : components){
            component.layout(layout);
        }
    }

    public void addToContainer(UIComponentContainer container){
        for(BuildComponent component : components){
            component.addToContainer(container);
        }
    }

}

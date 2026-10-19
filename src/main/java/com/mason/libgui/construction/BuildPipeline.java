package com.mason.libgui.construction;

import java.util.function.Consumer;

public class BuildPipeline{


    private final BuildComponentCollection components;
    private final Consumer<String> updater;


    protected BuildPipeline(BuildComponentCollection components, Consumer<String> updater){
        this.components = components;
        this.updater = updater;
    }

    public static BuildPipeline constructPipeline(BuildComponentCollection components, Consumer<String> updater){
        return new BuildPipeline(components, updater);
    }

    public static BuildPipeline constructPipelineWithNoUpdater(BuildComponentCollection components){
        Consumer<String> updater = s -> {};
        return new BuildPipeline(components, updater);
    }

    public static BuildPipeline constructPipelineWithDefaultUpdater(BuildComponentCollection components){
        Consumer<String> updater = componentName -> {
            System.out.println("Component " + componentName + " has been built!");
        };
        return new BuildPipeline(components, updater);
    }


    public void build(){
        components.ensureSorted();
        for(BuildComponent component : components){
            ensurePrerequisitesBuilt(component);
            component.ensureBuilt();
            updater.accept(component.toString());
        }
        for(BuildComponent component : components){
            component.postBuild();
        }
    }

    private void ensurePrerequisitesBuilt(BuildComponent component){
        for(BuildResource<?> resource : component.requiredPreBuildResources()){
            if(!resource.isBuilt()){
                throw new IllegalStateException("Resource unbuilt: " + resource);
            }
        }
    }

}

package com.mason.libgui.construction;

import java.util.Set;

public abstract class AbstractBuildComponent implements BuildComponent{


    private final ResourceRegister preBuildRegister;
    private final ResourceRegister postBuildRegister;
    private final Set<String> suppliedResourceNames;


    public AbstractBuildComponent(Set<String> suppliedResourceNames){
        this.preBuildRegister = new ResourceRegister();
        this.postBuildRegister = new ResourceRegister();
        this.suppliedResourceNames = suppliedResourceNames;
    }


    @Override
    public void registerPreBuildResource(String resourceName){
        preBuildRegister.registerName(resourceName);
    }

    @Override
    public void registerPostBuildResource(String resourceName){
        postBuildRegister.registerName(resourceName);
    }

    @Override
    public Set<String> requiredPreBuildResourceNames(){
        return preBuildRegister.getNames();
    }

    @Override
    public Set<String> requiredPostBuildResourceNames(){
        return postBuildRegister.getNames();
    }

    @Override
    public Set<String> suppliedBuildResourceNames(){
        return suppliedResourceNames;
    }

    @Override
    public void acceptPreBuildResource(BuildResource resource){
        preBuildRegister.acceptResource(resource);
    }

    @Override
    public void acceptPostBuildResource(BuildResource resource){
        postBuildRegister.acceptResource(resource);
    }

    protected BuildResource getPreBuildResource(String resourceName){
        return preBuildRegister.getResource(resourceName);
    }

}

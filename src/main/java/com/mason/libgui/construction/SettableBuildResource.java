package com.mason.libgui.construction;

public class SettableBuildResource<T> extends BuildResource<T>{


    private final String name;
    private T resource;


    public SettableBuildResource(String name){
        super();
        this.name = name;
    }


    @Override
    protected T build(){
        if(resource == null){
            throw new IllegalStateException("Resource unset!");
        }
        return resource;
    }

    public void setResource(T resource){
        if(isBuilt()){
            throw new IllegalStateException("Resource already set!");
        }
        this.resource = resource;
        ensureBuilt();
    }

    @Override
    public String toString(){
        return name;
    }

}

package com.mason.libgui.construction;

public abstract class BuildResource<T>{


    private boolean isBuilt;
    private T resource;


    public BuildResource(){
        isBuilt = false;
    }


    public boolean isBuilt(){
        return isBuilt;
    }

    public T get(){
        if(!isBuilt){
            throw new IllegalStateException("Resource unbuilt!");
        }
        if(resource == null){
            throw new IllegalStateException("Resource marked as built but does not exist!");
        }
        return resource;
    }

    public final void ensureBuilt(){
        if(!isBuilt){
            resource = build();
            isBuilt = true;
        }
    }

    protected abstract T build();


    public static <T> BuildResource<T> getPreBuiltResource(T resource, String name){
        BuildResource<T> buildResource = new BuildResource<>(){

            @Override
            protected T build(){
                return resource;
            }

            @Override
            public String toString(){
                return name;
            }
        };
        buildResource.ensureBuilt();
        return buildResource;
    }

}

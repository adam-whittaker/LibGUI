package com.mason.libgui.components;

import java.util.HashMap;
import java.util.Map;

public final class ComponentIDRegister{


    private static final Map<String, Integer> idMap = new HashMap<>();
    private static int currentID = -1;


    private ComponentIDRegister(){}


    public static int getID(String componentName){
        componentName = componentName.toUpperCase();
        if(!idMap.containsKey(componentName)){
            throw new IllegalArgumentException("Invalid toggle name: " + componentName);
        }
        return idMap.get(componentName);
    }

    public static int registerComponent(Identifiable component){
        String componentName = component.getName().toUpperCase();
        if(idMap.containsKey(componentName)){
            throw new IllegalArgumentException("Component name already registered: " + componentName);
        }
        int id = nextID();
        idMap.put(componentName, id);
        return id;
    }

    private static int nextID(){
        currentID++;
        return currentID;
    }

}

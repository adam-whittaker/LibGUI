package com.mason.libgui.components.panes.layout;

import java.util.HashMap;
import java.util.Map;

public class BoxAddressManager{


    private final Map<String, Box> namedBoxes;
    private final Box root;


    BoxAddressManager(Box root){
        namedBoxes = new HashMap<>();
        this.root = root;
    }


    boolean isNamedAddress(String address){
        return isRootAddress(address) || namedBoxes.containsKey(address);
    }

    private boolean isRootAddress(String address){
        return address.isEmpty() || address.equalsIgnoreCase("ROOT") || address.equals("*");
    }

    Box findBoxFromNamedAddress(String address){
        if(isRootAddress(address)){
            return root;
        }
        return namedBoxes.get(address);
    }

    boolean startsWithNamedAddress(String address){
        if(!address.contains("-")){
            return false;
        }
        String firstSubAddress = getFirstSubAddress(address);
        return isNamedAddress(firstSubAddress);
    }

    private String getFirstSubAddress(String address){
        return address.substring(0, address.indexOf('-'));
    }

    String getAddressTail(String address){
        return address.substring(address.indexOf('-')+1);
    }

    String[] getSubAddresses(String address){
        return address.split("-");
    }

    void nameBox(Box box, String name){
        verifyNameSafe(name);
        namedBoxes.put(name, box);
    }

    private void verifyNameSafe(String name){
        if(name.contains("-")){
            throw new IllegalArgumentException("Given box name: " + name + " not allowed");
        }
        if(namedBoxes.containsKey(name)){
            throw new IllegalArgumentException("Box with name: " + name + " already exists");
        }
    }

    public Box findInitialBoxFromNamedStartAddress(String address){
        String namedStart = getFirstSubAddress(address);
        return findBoxFromNamedAddress(namedStart);
    }

}

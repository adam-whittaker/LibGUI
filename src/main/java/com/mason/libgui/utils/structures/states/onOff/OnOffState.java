package com.mason.libgui.utils.structures.states.onOff;

public interface OnOffState extends OnOffQuery{

    void turnOn();

    void turnOff();


    static OnOffState newDefault(){
        return new OnOffState(){

            private boolean on = false;

            @Override
            public void turnOn(){
                on = true;
            }

            @Override
            public void turnOff(){
                on = false;
            }

            @Override
            public boolean isOn(){
                return on;
            }

        };
    }

}

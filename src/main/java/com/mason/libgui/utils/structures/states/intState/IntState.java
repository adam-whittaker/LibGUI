package com.mason.libgui.utils.structures.states.intState;

public interface IntState extends IntQuery{


    void setState(int state);


    static void verifyStateWithinBounds(int state, int lowerBound, int upperBound){
        if(state < lowerBound || state >= upperBound){
            throw new IllegalArgumentException("State should be within bounds: " + lowerBound + ", " + upperBound);
        }
    }

}

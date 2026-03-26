package com.mason.libgui.utils.structures.states.intState;

public class BoundedIntState implements IntState{


    private int state;
    private final int lowerBound;
    private final int upperBound;


    public BoundedIntState(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        state = lowerBound;
    }


    @Override
    public int getState(){
        return state;
    }

    @Override
    public void setState(int state){
        IntState.verifyStateWithinBounds(state, lowerBound, upperBound);
        this.state = state;
    }

}

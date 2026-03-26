package com.mason.libgui.utils.structures.states.intState;

import com.mason.libgui.utils.structures.states.position.PositionState;

public class PositionTo8BitIntStateAdapter implements IntState{


    private final PositionState positionState;


    public PositionTo8BitIntStateAdapter(PositionState positionState){
        this.positionState = positionState;
    }


    @Override
    public int getState(){
        return (int)(positionState.getPosition() * 255);
    }

    @Override
    public void setState(int state){
        positionState.setPosition(state/255D);
    }

}

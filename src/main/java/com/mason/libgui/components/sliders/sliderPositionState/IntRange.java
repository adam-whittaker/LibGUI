package com.mason.libgui.components.sliders.sliderPositionState;

import static java.lang.Math.max;

public record IntRange(int minX, int maxX){


    int clamp(int x){
        if(x > maxX){
            return maxX;
        }
        if(x < minX){
            return minX;
        }
        return x;
    }

    int span(){
        return max(maxX - minX, 1);
    }

    double position(int x){
        return ((double)x - minX)/span();
    }

    int fromPosition(double position){
        return (int)(((maxX-minX) * position) + minX);
    }

    void verifyWithinRange(int x){
        if(minX > x || maxX < x){
            throw new IllegalStateException("value outside of allowed range");
        }
    }

}

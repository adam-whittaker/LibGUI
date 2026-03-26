package com.mason.libgui.utils.structures.states.position;

public interface PositionState extends PositionQuery{


    void setPosition(double position);


    class DefaultUnbounded implements PositionState{


        private double position = 0;


        @Override
        public double getPosition(){
            return position;
        }

        @Override
        public void setPosition(double position){
            this.position = position;
        }

    }

    class DefaultBounded extends DefaultUnbounded{


        private final double lowerBound;
        private final double upperBound;


        public DefaultBounded(double lower, double upper){
            lowerBound = lower;
            upperBound = upper;
        }


        @Override
        public void setPosition(double position){
            if(position < lowerBound || position > upperBound){
                throw new IllegalArgumentException("Position must be in range " + lowerBound + " to " + upperBound);
            }
            super.setPosition(position);
        }

    }


    static PositionState constructDefaultUnboundedPosition(){
        return new DefaultUnbounded();
    }

    static PositionState constructDefaultUnitIntervalPosition(){
        return new DefaultBounded(0, 1);
    }

}

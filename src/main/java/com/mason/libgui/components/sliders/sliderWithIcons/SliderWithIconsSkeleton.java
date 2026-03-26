package com.mason.libgui.components.sliders.sliderWithIcons;

import com.mason.libgui.components.sliders.basicSlider.SliderSkeleton;
import com.mason.libgui.utils.structures.Coord;

import java.awt.image.BufferedImage;

public class SliderWithIconsSkeleton extends SliderSkeleton{
    
    
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private Coord leftCoord;
    private Coord rightCoord;
    
    
    public SliderWithIconsSkeleton(){}


    public BufferedImage getLeftImage(){
        if(leftImage == null){
            throw new IllegalStateException("leftImage is not set");
        }
        return leftImage;
    }

    public void setLeftImage(BufferedImage leftImage){
        if(this.leftImage != null){
            throw new IllegalStateException("leftImage is already set");
        }
        this.leftImage = leftImage;
    }

    public BufferedImage getRightImage(){
        if(rightImage == null){
            throw new IllegalStateException("rightImage is not set");
        }
        return rightImage;
    }

    public void setRightImage(BufferedImage rightImage){
        if(this.rightImage != null){
            throw new IllegalStateException("rightImage is already set");
        }
        this.rightImage = rightImage;
    }

    public Coord getLeftIconCoord(){
        if(leftCoord == null){
            throw new IllegalStateException("leftCoord is not set");
        }
        return leftCoord;
    }

    public void setLeftIconCoord(Coord leftCoord){
        if(this.leftCoord != null){
            throw new IllegalStateException("leftCoord is already set");
        }
        this.leftCoord = leftCoord;
    }

    public Coord getRightIconCoord(){
        if(rightCoord == null){
            throw new IllegalStateException("rightCoord is not set");
        }
        return rightCoord;
    }

    public void setRightIconCoord(Coord rightCoord){
        if(this.rightCoord != null){
            throw new IllegalStateException("rightCoord is already set");
        }
        this.rightCoord = rightCoord;
    }

}

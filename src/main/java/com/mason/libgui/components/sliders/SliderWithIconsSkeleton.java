package com.mason.libgui.components.sliders;

import com.mason.libgui.components.deco.SliderDeco;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.utils.structures.Coord;

import java.awt.image.BufferedImage;

public class SliderWithIconsSkeleton{
    
    
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private int handleWidth;
    private String name;
    private HitboxRect railHitbox;
    private SliderDeco sliderDeco;
    private Coord leftCoord;
    private Coord rightCoord;
    
    
    SliderWithIconsSkeleton(){}


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

    public int getHandleWidth(){
        return handleWidth;
    }

    public void setHandleWidth(int handleWidth){
        this.handleWidth = handleWidth;
    }

    public String getName(){
        if(name == null){
            throw new IllegalStateException("name is not set");
        }
        return name;
    }

    public void setName(String name){
        if(this.name != null){
            throw new IllegalStateException("name is already set");
        }
        this.name = name;
    }

    public HitboxRect getRailHitbox(){
        if(railHitbox == null){
            throw new IllegalStateException("railHitbox is not set");
        }
        return railHitbox;
    }

    public void setRailHitbox(HitboxRect railHitbox){
        if(this.railHitbox != null){
            throw new IllegalStateException("railHitbox is already set");
        }
        this.railHitbox = railHitbox;
    }

    public SliderDeco getSliderDeco(){
        if(sliderDeco == null){
            throw new IllegalStateException("sliderDeco is not set");
        }
        return sliderDeco;
    }

    public void setSliderDeco(SliderDeco sliderDeco){
        if(this.sliderDeco != null){
            throw new IllegalStateException("sliderDeco is already set");
        }
        this.sliderDeco = sliderDeco;
    }

    public Coord getLeftCoord(){
        if(leftCoord == null){
            throw new IllegalStateException("leftCoord is not set");
        }
        return leftCoord;
    }

    public void setLeftCoord(Coord leftCoord){
        if(this.leftCoord != null){
            throw new IllegalStateException("leftCoord is already set");
        }
        this.leftCoord = leftCoord;
    }

    public Coord getRightCoord(){
        if(rightCoord == null){
            throw new IllegalStateException("rightCoord is not set");
        }
        return rightCoord;
    }

    public void setRightCoord(Coord rightCoord){
        if(this.rightCoord != null){
            throw new IllegalStateException("rightCoord is already set");
        }
        this.rightCoord = rightCoord;
    }

}

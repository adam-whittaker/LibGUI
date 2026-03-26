package com.mason.libgui.components.sliders.sliderWithIcons;

import com.mason.libgui.components.sliders.basicSlider.BasicSlider;
import com.mason.libgui.utils.structures.Coord;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SliderWithIcons extends BasicSlider{


    private final BufferedImage leftIcon;
    private final BufferedImage rightIcon;
    private final Coord leftIconCoord;
    private final Coord rightIconCoord;


    public SliderWithIcons(SliderWithIconsSkeleton skeleton){
        super(skeleton);
        leftIcon = skeleton.getLeftImage();
        rightIcon = skeleton.getRightImage();
        leftIconCoord = skeleton.getLeftIconCoord();
        rightIconCoord = skeleton.getRightIconCoord();
    }


    @Override
    public void render(Graphics2D g){
        g.drawImage(leftIcon, null, leftIconCoord.x(), leftIconCoord.y());
        g.drawImage(rightIcon, null, rightIconCoord.x(), rightIconCoord.y());
        super.render(g);
    }

}

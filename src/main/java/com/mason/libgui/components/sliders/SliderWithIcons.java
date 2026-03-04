package com.mason.libgui.components.sliders;

import com.mason.libgui.components.deco.BasicSliderDeco;
import com.mason.libgui.core.component.HitboxRect;
import com.mason.libgui.utils.ImageUtils;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.RectQuery;
import com.mason.libgui.utils.structures.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SliderWithIcons extends BasicSlider{


    private static final int PADDING = 6;

    private final BufferedImage leftIcon;
    private final BufferedImage rightIcon;
    private final Coord leftCoord;
    private final Coord rightCoord;


    protected SliderWithIcons(SliderWithIconsSkeleton skeleton){
        super(skeleton.getName(), skeleton.getRailHitbox(), skeleton.getHandleWidth(), skeleton.getSliderDeco());
        leftIcon = skeleton.getLeftImage();
        rightIcon = skeleton.getRightImage();
        leftCoord = skeleton.getLeftCoord();
        rightCoord = skeleton.getRightCoord();
    }

    public static SliderWithIcons buildWithBasicDeco(String name, RectQuery fullBox, int handleWidth, String leftIconFilepath, String rightIconFilepath){
        SliderWithIconsSkeleton skeleton = new SliderWithIconsSkeleton();
        BufferedImage leftIcon = ImageUtils.readImage(leftIconFilepath);
        BufferedImage rightIcon = ImageUtils.readImage(rightIconFilepath);
        int leftIconWidth = leftIcon.getWidth(null);
        int rightIconWidth = rightIcon.getWidth(null);

        skeleton.setLeftCoord(fullBox.getCoord());
        skeleton.setRightCoord(new Coord(fullBox.x() + fullBox.width() - rightIconWidth, fullBox.y()));
        skeleton.setLeftImage(leftIcon);
        skeleton.setRightImage(rightIcon);

        skeleton.setName(name);
        skeleton.setRailHitbox(calculateRailHitbox(fullBox, leftIconWidth, rightIconWidth));
        skeleton.setHandleWidth(handleWidth);
        skeleton.setSliderDeco(new BasicSliderDeco());

        return new SliderWithIcons(skeleton);
    }

    private static HitboxRect calculateRailHitbox(RectQuery fullBox, int leftIconWidth, int rightIconWidth){
        int x = fullBox.x() + PADDING + leftIconWidth;
        int y = fullBox.y();
        int width = fullBox.width() - 2*PADDING - leftIconWidth - rightIconWidth;
        int height = fullBox.height();
        return new HitboxRect(new Coord(x, y), new Size(width, height));
    }


    @Override
    public void render(Graphics2D g){
        g.drawImage(leftIcon, null, leftCoord.x(), leftCoord.y());
        g.drawImage(rightIcon, null, rightCoord.x(), rightCoord.y());
        super.render(g);
    }

}

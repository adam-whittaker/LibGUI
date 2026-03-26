package com.mason.libgui.components.sliders.sliderWithIcons;

import com.mason.libgui.components.deco.SliderDeco;
import com.mason.libgui.components.sliders.sliderPositionState.SliderPositionState;
import com.mason.libgui.components.sliders.sliderPositionState.SliderPositionStateFactory;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.utils.ImageUtils;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;
import com.mason.libgui.utils.structures.interfaces.RectQuery;

import java.awt.*;

import static java.lang.Math.max;

public class SliderWithIconsBuilder{


    private final int padding;
    private final SliderDeco deco;
    protected final Size sliderHandleSize;


    public SliderWithIconsBuilder(int padding, SliderDeco deco, Size sliderHandleSize){
        this.padding = padding;
        this.deco = deco;
        this.sliderHandleSize = sliderHandleSize;
    }


    public SliderWithIconsSkeleton buildSkeleton(String name,
                                                 RectQuery fullBoundary,
                                                 String leftImageFilepath,
                                                 String rightImageFilepath,
                                                 SliderPositionStateFactory positionState){
        SliderWithIconsSkeleton skeleton = packInitialParametersIntoSkeleton(
                name,
                fullBoundary,
                leftImageFilepath,
                rightImageFilepath);
        setUpRailHitbox(skeleton);
        setUpImageCoords(skeleton);
        setUpSliderHandle(skeleton, positionState);
        return skeleton;
    }

    private SliderWithIconsSkeleton packInitialParametersIntoSkeleton(String name,
                                                                      RectQuery fullBoundary,
                                                                      String leftImageFilepath,
                                                                      String rightImageFilepath){
        verifySliderHandleSizeWithinBounds(fullBoundary);
        SliderWithIconsSkeleton skeleton = new SliderWithIconsSkeleton();
        skeleton.setName(name);
        skeleton.setBoundary(HitboxRect.fromRect(fullBoundary));
        skeleton.setLeftImage(ImageUtils.readImage(leftImageFilepath));
        skeleton.setRightImage(ImageUtils.readImage(rightImageFilepath));
        skeleton.setSliderDeco(deco);
        skeleton.setSliderHandleSize(sliderHandleSize);
        return skeleton;
    }

    private void verifySliderHandleSizeWithinBounds(RectQuery boundary){
        if(boundary.height() < sliderHandleSize.height()){
            throw new IllegalStateException("Boundary shorter than slider handle");
        }
    }

    private void setUpRailHitbox(SliderWithIconsSkeleton skeleton){
        RectQuery boundary = skeleton.getBoundary();
        Image leftImage = skeleton.getLeftImage();
        Image rightImage = skeleton.getRightImage();
        int x = boundary.x() + leftImage.getWidth(null) + padding;
        int y = boundary.y() + (boundary.height() - sliderHandleSize.height())/2;
        int width = boundary.width() - 2 * padding - leftImage.getWidth(null) - rightImage.getWidth(null);
        int height = sliderHandleSize.height();
        verifyMinimumRailHitboxWidth(width);
        skeleton.setRailHitbox(HitboxRect.build(x, y, width, height));
    }

    private void verifyMinimumRailHitboxWidth(int width){
        if(width < 2 * sliderHandleSize.width()){
            throw new IllegalStateException("Slider rail not long enough");
        }
    }

    private void setUpImageCoords(SliderWithIconsSkeleton skeleton){
        verifyImageHeightsWithinBounds(skeleton);
        RectQuery boundary = skeleton.getBoundary();
        RectQuery railBox = skeleton.getRailHitbox();
        Image leftImage = skeleton.getLeftImage();
        Image rightImage = skeleton.getRightImage();
        int leftX = boundary.x();
        int leftY = boundary.y() + (boundary.height() - leftImage.getHeight(null))/2;
        int rightX = railBox.x() + railBox.width() + padding;
        int rightY = boundary.y() + (boundary.height() - rightImage.getHeight(null))/2;
        skeleton.setLeftIconCoord(new Coord(leftX, leftY));
        skeleton.setRightIconCoord(new Coord(rightX, rightY));
    }

    private void verifyImageHeightsWithinBounds(SliderWithIconsSkeleton skeleton){
        RectQuery boundary = skeleton.getBoundary();
        Image leftImage = skeleton.getLeftImage();
        Image rightImage = skeleton.getRightImage();
        int maxImageHeight = max(leftImage.getHeight(null), rightImage.getHeight(null));
        if(maxImageHeight > boundary.height()){
            throw new IllegalStateException("Images are taller than slider boundary");
        }
    }

    private void setUpSliderHandle(SliderWithIconsSkeleton skeleton, SliderPositionStateFactory stateFactory){
        RectQuery clampRect = skeleton.getRailHitbox();
        SliderPositionState state = stateFactory.buildSliderPositionState(clampRect);
        skeleton.setSliderPositionState(state);
    }

}

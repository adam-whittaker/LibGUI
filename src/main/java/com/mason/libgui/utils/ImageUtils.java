package com.mason.libgui.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImageUtils{


    private ImageUtils(){}


    public static BufferedImage readImage(String iconFilepath){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(iconFilepath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image from given filepath: " + iconFilepath, e);
        }
        return image;
    }

}

package com.mason.libgui.components.deco;

import com.mason.libgui.utils.structures.Size;

import java.awt.*;

public class BasicPaneDeco implements PaneDeco{


    private static final Color BACKGROUND_COLOR = new Color(20, 22, 30);
    private static final Color BORDER_COLOR = new Color(50, 50, 60);
    private static final int BORDER_THICKNESS = 4;


    public BasicPaneDeco(){}


    @Override
    public void drawBackground(Graphics2D g, Size size){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, size.width(), size.height());
    }

    @Override
    public void drawForeground(Graphics2D g, Size size){
        g.setColor(BORDER_COLOR);
        g.fillRect(0, 0, BORDER_THICKNESS, size.height());
        g.fillRect(0, 0, size.width(), BORDER_THICKNESS);
        g.fillRect(size.width() - BORDER_THICKNESS, 0, BORDER_THICKNESS, size.height());
        g.fillRect(0, size.height() - BORDER_THICKNESS, size.width(), BORDER_THICKNESS);
    }

}

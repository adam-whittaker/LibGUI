package com.mason.libgui.components.deco;

import com.mason.libgui.components.toggles.ToggleState;
import com.mason.libgui.utils.ImageUtils;
import com.mason.libgui.utils.structures.RectQuery;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicButtonDeco implements ButtonDeco{


    private static final int ARC = 14;
    private static final Color BASE_TOP_BUTTON_DOWN_COLOR = new Color(60, 64, 82);
    private static final Color BASE_TOP_BUTTON_UP_COLOR = new Color(55, 55, 65);
    private static final Color BASE_BOTTOM_BUTTON_DOWN_COLOR = new Color(38, 40, 56);
    private static final Color BASE_BOTTOM_BUTTON_UP_COLOR = new Color(45, 45, 55);
    private static final Color HOVER_GLOW_ACCENT_COLOR = new Color(120, 180, 255, 55);
    private static final Color SELECTED_RING_COLOR = new Color(180, 220, 255, 120);

    private final Image icon;


    private BasicButtonDeco(Image icon){
        this.icon = icon;
    }

    public static BasicButtonDeco build(String iconFilepath){
        BufferedImage image = ImageUtils.readImage(iconFilepath);
        return new BasicButtonDeco(image);
    }


    @Override
    public void drawButtonDeco(Graphics2D g, RectQuery box, ToggleState state){
        Graphics2D g2 = (Graphics2D) g.create();
        try{
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            drawButtonDecoWithGraphicsContext(g2, box, state);
        }finally{
            g2.dispose();
        }
    }

    private void drawButtonDecoWithGraphicsContext(Graphics2D g, RectQuery box, ToggleState state){
        RectangularShape shape = constructShape(box);
        fillBackgroundGradient(g, shape, state);
        drawInnerHighlight(g, box, state);
        drawBorder(g, shape, state);
        tryDrawHoverGlowAccent(g, box, state);
        tryDrawSelectedRing(g, box, state);
        tryDrawPressedDepression(g, state);
        drawIcon(g, box);
    }

    private RectangularShape constructShape(RectQuery box){
        return new RoundRectangle2D.Float(box.x(), box.y(), box.width() - 1, box.height() - 1, ARC, ARC);
    }

    private void fillBackgroundGradient(Graphics2D g, RectangularShape shape, ToggleState state){
        Color baseTop = getBaseTopColor(state);
        Color baseBot = getBaseBottomColor(state);
        GradientPaint gp = new GradientPaint( 0, 0, baseTop, 0, (float)shape.getHeight(), baseBot);
        g.setPaint(gp);
        g.fill(shape);
    }

    private Color getBaseTopColor(ToggleState state){
        if(state.isDown()){
            return shadeBaseColorBasedOnState(BASE_TOP_BUTTON_DOWN_COLOR, state);
        }
        return shadeBaseColorBasedOnState(BASE_TOP_BUTTON_UP_COLOR, state);
    }

    private Color getBaseBottomColor(ToggleState state){
        if(state.isDown()){
            return shadeBaseColorBasedOnState(BASE_BOTTOM_BUTTON_DOWN_COLOR, state);
        }
        return shadeBaseColorBasedOnState(BASE_BOTTOM_BUTTON_UP_COLOR, state);
    }

    private Color shadeBaseColorBasedOnState(Color base, ToggleState state){
        if (state.equals(ToggleState.PRESSED)) {
            return base.darker();
        } else if (state.equals(ToggleState.HOVERING)) {
            return brighten(base, 12);
        }
        return base;
    }

    private Color brighten(Color c, int amount) {
        int r = Math.min(255, c.getRed() + amount);
        int g = Math.min(255, c.getGreen() + amount);
        int b = Math.min(255, c.getBlue() + amount);
        return new Color(r, g, b, c.getAlpha());
    }

    private void drawInnerHighlight(Graphics2D g, RectQuery box, ToggleState state){
        g.setColor(getInnerHighlightColor(state));
        g.drawRoundRect(box.x()+1, box.y()+1, box.width() - 3, box.height() - 3, ARC - 2, ARC - 2);
    }

    private void drawBorder(Graphics2D g, Shape shape, ToggleState state){
        g.setColor(getBorderHighlightColor(state));
        g.draw(shape);
    }

    private Color getBorderHighlightColor(ToggleState state){
        return new Color(0, 0, 0, state.equals(ToggleState.PRESSED) ? 140 : 110);
    }

    private Color getInnerHighlightColor(ToggleState state){
        return new Color(255, 255, 255, state.equals(ToggleState.PRESSED) ? 18 : 28);
    }

    private void tryDrawHoverGlowAccent(Graphics2D g, RectQuery box, ToggleState state){
        if(state.equals(ToggleState.HOVERING)){
            g.setColor(HOVER_GLOW_ACCENT_COLOR);
            g.setStroke(new BasicStroke(2f));
            g.drawRoundRect(box.x()+1, box.y()+1, box.width() - 3, box.height() - 3, ARC - 2, ARC - 2);
            g.setStroke(new BasicStroke(1f));
        }
    }

    private void tryDrawSelectedRing(Graphics2D g, RectQuery box, ToggleState state){
        if(state.equals(ToggleState.SELECTED)){
            g.setColor(SELECTED_RING_COLOR);
            g.setStroke(new BasicStroke(2f));
            g.drawRoundRect(box.x()+2, box.y()+2, box.width() - 5, box.height() - 5, ARC - 4, ARC - 4);
        }
    }

    private void tryDrawPressedDepression(Graphics2D g, ToggleState state){
        if(state.equals(ToggleState.PRESSED)){
            g.translate(0, 1);
        }
    }

    private void drawIcon(Graphics2D g, RectQuery box){
        g.drawImage(icon, box.x()+6, box.y()+6, null);
    }

}

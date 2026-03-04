package com.mason.libgui.components.deco;

import com.mason.libgui.utils.structures.RectQuery;

import java.awt.*;

public class BasicSliderDeco implements SliderDeco{


    private static final Color SELECTED_SHADOW_COLOR = new Color(0, 0, 0, 75);
    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 55);
    private static final Color SELECTED_GLOW_COLOR = new Color(120, 180, 255, 70);
    private static final Color SELECTED_TOP_COLOR = new Color(245, 248, 255);
    private static final Color TOP_COLOR = new Color(230, 234, 242);
    private static final Color SELECTED_BOTTOM_COLOR = new Color(185, 198, 220);
    private static final Color BOTTOM_COLOR = new Color(170, 178, 190);
    private static final Color SELECTED_BORDER_COLOR = new Color(70, 110, 180, 180);
    private static final Color BORDER_COLOR = new Color(60, 68, 82, 140);
    private static final Color SELECTED_INNER_HIGHLIGHT_COLOR = new Color(255, 255, 255, 140);
    private static final Color INNER_HIGHLIGHT_COLOR = new Color(255, 255, 255, 110);
    private static final Color SELECTED_GRIP_COLOR = new Color(70, 95, 140, 110);
    private static final Color GRIP_COLOR = new Color(90, 98, 110, 90);
    private static final Color TRANSPARENT_WHITE = new Color(255, 255, 255, 0);


    public BasicSliderDeco(){}


    @Override
    public void drawSliderRail(Graphics2D g, RectQuery rail){
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int x = rail.x();
            int y = rail.y();
            int w = rail.width();
            int h = rail.height();

            drawSliderRailWithTemporaryGraphicsContext(g2, x, y, w, h);
        } finally {
            g2.dispose();
        }
    }

    private void drawSliderRailWithTemporaryGraphicsContext(Graphics2D g, int x, int y, int w, int h){
        int grooveH = Math.max(2, h / 3);
        int grooveY = y + (h - grooveH) / 2;
        int grooveArc = Math.min(grooveH, 8);

        g.setPaint(new GradientPaint(
                x, grooveY, new Color(20, 22, 36, 100),
                x, grooveY + grooveH, new Color(255, 255, 255, 18)
        ));
        g.fillRoundRect(x + 2, grooveY, Math.max(0, w - 4), grooveH, grooveArc, grooveArc);
    }


    @Override
    public void drawSliderHandle(Graphics2D g, RectQuery handle, boolean selected){
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int x = handle.x();
            int y = handle.y();
            int w = handle.width();
            int h = handle.height();

            drawSliderHandleWithTemporaryGraphicsContext(g2, x, y, w, h, selected);

        } finally {
            g2.dispose();
        }
    }

    private void drawSliderHandleWithTemporaryGraphicsContext(Graphics2D g, int x, int y, int w, int h, boolean selected){
        int arc = Math.min(Math.min(w, h), 14);

        Color shadowColor = getShadowColor(selected);
        Color topColor = getTopColor(selected);
        Color bottomColor = getBottomColor(selected);
        Color borderColor = getBorderColor(selected);
        Color innerHighlight = getInnerHighlightColor(selected);
        Color gripColor = getGripColor(selected);

        drawHandleShadow(g, shadowColor, x, y, w, h, arc);
        tryDrawSelectedGlow(g, x, y, w, h, arc, selected);
        drawMainHandleBody(g, x, y, w, h, arc, topColor, bottomColor);
        drawOuterBorder(g, x, y, w, h, arc, borderColor);
        drawInnerHighlight(g, x, y, w, h, arc, innerHighlight);
        drawTopGloss(g, x, y, w, h, arc, selected);
        drawGripLines(g, x, y, w, h, gripColor);
    }

    private Color getShadowColor(boolean selected){
        return selected ? SELECTED_SHADOW_COLOR : SHADOW_COLOR;
    }

    private Color getTopColor(boolean selected){
        return selected ? SELECTED_TOP_COLOR : TOP_COLOR;
    }

    private Color getBottomColor(boolean selected){
        return selected ? SELECTED_BOTTOM_COLOR : BOTTOM_COLOR;
    }

    private Color getBorderColor(boolean selected){
        return selected ? SELECTED_BORDER_COLOR : BORDER_COLOR;
    }

    private Color getInnerHighlightColor(boolean selected){
        return selected ? SELECTED_INNER_HIGHLIGHT_COLOR : INNER_HIGHLIGHT_COLOR;
    }

    private Color getGripColor(boolean selected){
        return selected ? SELECTED_GRIP_COLOR : GRIP_COLOR;
    }

    private void drawHandleShadow(Graphics2D g, Color shadowColor, int x, int y, int w, int h, int arc){
        g.setColor(shadowColor);
        g.fillRoundRect(x, y + 1, w, h, arc, arc);
    }

    private void tryDrawSelectedGlow(Graphics2D g, int x, int y, int w, int h, int arc, boolean selected){
        if(selected){
            g.setColor(SELECTED_GLOW_COLOR);
            g.fillRoundRect(x - 2, y - 2, w + 4, h + 4, arc + 4, arc + 4);
        }
    }

    private void drawMainHandleBody(Graphics2D g, int x, int y, int w, int h, int arc, Color topColor, Color bottomColor){
        g.setPaint(new GradientPaint(
                x, y, topColor,
                x, y + h, bottomColor
        ));
        g.fillRoundRect(x, y, w, h - 1, arc, arc);
    }

    private void drawOuterBorder(Graphics2D g, int x, int y, int w, int h, int arc, Color borderColor){
        g.setColor(borderColor);
        g.drawRoundRect(x, y, w - 1, h - 2, arc, arc);
    }

    private void drawInnerHighlight(Graphics2D g, int x, int y, int w, int h, int arc, Color innerHighlight){
        g.setColor(innerHighlight);
        g.drawRoundRect(
                x + 1, y + 1,
                w - 3, h - 4,
                Math.max(arc - 2, 4),
                Math.max(arc - 2, 4)
        );
    }

    private void drawTopGloss(Graphics2D g, int x, int y, int w, int h, int arc, boolean selected){
        int glossH = Math.max(3, h / 3);
        g.setPaint(new GradientPaint(
                x, y, getTopGlossColor(selected),
                x, y + glossH, TRANSPARENT_WHITE
        ));
        g.fillRoundRect(
                x + 1, y + 1,
                w - 2, glossH,
                Math.max(arc - 2, 4),
                Math.max(arc - 2, 4)
        );
    }

    private Color getTopGlossColor(boolean selected){
        return new Color(255, 255, 255, selected ? 120 : 90);
    }

    private void drawGripLines(Graphics2D g, int x, int y, int w, int h, Color gripColor){
        g.setColor(gripColor);

        int cx = x + w / 2;
        int gy1 = y + h / 3;
        int gy2 = y + h - h / 3;

        g.drawLine(cx - 3, gy1, cx - 3, gy2);
        g.drawLine(cx,     gy1, cx,     gy2);
        g.drawLine(cx + 3, gy1, cx + 3, gy2);
    }

}

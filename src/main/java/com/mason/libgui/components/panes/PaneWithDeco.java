package com.mason.libgui.components.panes;

import com.mason.libgui.components.deco.PaneDeco;
import com.mason.libgui.components.panes.construction.PaneSkeleton;

import java.awt.*;

public class PaneWithDeco extends Pane{


    private final PaneDeco deco;


    protected PaneWithDeco(PaneSkeleton skeleton, PaneDeco deco){
        super(skeleton);
        this.deco = deco;
    }


    protected void drawBackground(Graphics2D g){
        deco.drawBackground(g, getSize());
    }

    protected void drawForeground(Graphics2D g){
        deco.drawForeground(g, getSize());
    }

}

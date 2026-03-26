package com.mason.libgui.testHelpers.launch;

import com.mason.libgui.components.panes.*;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.guiManagement.GUI;
import com.mason.libgui.testHelpers.stubs.*;
import com.mason.libgui.utils.structures.Coord;
import com.mason.libgui.utils.structures.Size;

public class DebugLauncher{


    public static void main(String[] args){
        GUI gui = GUI.buildSimpleGUI(new Size(800, 600), "Example pane");

        HitboxRect paneBoundary = HitboxRect.build(new Coord(100, 100), new Size(400, 300));
        PanZoomPane pane = PanZoomPane.buildFullyZoomedOutPane(paneBoundary);
        gui.addComponent(pane);
        pane.setInputSource(gui);

        DefaultUIComponent component = UIComponentPrintingStub.buildDefault(new Coord(100, 100), new Size(100, 150));
        pane.addComponent(component);
        component.setInputSource(pane);

        DefaultUIComponent component2 = UIComponentPrintingStub.buildDefault(new Coord(350, 250), new Size(100, 100));
        pane.addComponent(component2);
        component2.setInputSource(pane);


        gui.start();
    }

}

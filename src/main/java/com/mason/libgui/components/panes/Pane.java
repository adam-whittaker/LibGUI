
package com.mason.libgui.components.panes;

import com.mason.libgui.components.behaviour.GraphicsTransformBehaviour;
import com.mason.libgui.components.panes.construction.PaneGraphicsTransformBuilder;
import com.mason.libgui.components.panes.construction.PaneSkeleton;
import com.mason.libgui.core.component.AbstractUIComponent;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import com.mason.libgui.core.component.UIComponent;
import com.mason.libgui.core.componentManagement.InteractiveContainer;
import com.mason.libgui.core.componentManagement.UIComponentManager;
import com.mason.libgui.core.input.componentLayer.GUIInputRegister;
import com.mason.libgui.core.input.mouse.BoundedMouseInputListener;
import com.mason.libgui.core.input.mouse.InputDelegator;

import java.awt.*;
import java.awt.event.KeyListener;

public class Pane extends AbstractUIComponent implements InteractiveContainer, InputDelegator{


    private final UIComponentManager componentManager;
    private final PaneGUIInputTranslator inputTranslator;
    private final GraphicsTransformBehaviour graphicsTransform;


    protected Pane(PaneSkeleton skeleton){
        super(skeleton.getBoundary());
        componentManager = skeleton.getComponentManager();
        inputTranslator = new PaneGUIInputTranslator(skeleton.getBoundary());
        inputTranslator.setDelegateSocket(componentManager.getInputDistributor());

        PaneGraphicsTransformBuilder transformBuilder =
                new PaneGraphicsTransformBuilder(this::renderAfterTranslation, skeleton.getBoundary());
        graphicsTransform = transformBuilder.build();
        addItemsFromSkeleton(skeleton);
    }

    private void addItemsFromSkeleton(PaneSkeleton skeleton){
        for(UIComponent component : skeleton.getComponents()){
            this.addComponent(component);
        }
        for(BoundedMouseInputListener mouseListener : skeleton.getMouseInputListeners()){
            mouseListener.setInputSource(this);
        }
        for(KeyListener keyListener : skeleton.getKeyListeners()){
            this.addKeyListener(keyListener);
        }
    }

    public static Pane build(HitboxRect boundary){
        return buildWithCustomComponentManager(boundary, UIComponentManager.buildSimpleUIComponentManager());
    }

    public static Pane buildWithCustomComponentManager(HitboxRect boundary, UIComponentManager componentManager){
        PaneSkeleton skeleton = new PaneSkeleton();
        skeleton.setBoundary(boundary);
        skeleton.setComponentManager(componentManager);
        return new Pane(skeleton);
    }


    //Rendering relativisation
    @Override
    public void render(Graphics2D g){
        graphicsTransform.transformAndRender(g);
    }

    protected void renderAfterTranslation(Graphics2D g){
        drawBackground(g);
        renderComponents(g);
        drawForeground(g);
    }

    protected void renderComponents(Graphics2D g){
        componentManager.renderComponents(g);
    }

    protected void drawBackground(Graphics2D g){}

    protected void drawForeground(Graphics2D g){}


    //Ticking
    @Override
    public void tick(){
        componentManager.tickComponents();
    }


    @Override
    public void addComponent(UIComponent comp){
        componentManager.addComponent(comp);
    }

    @Override
    public void removeComponent(UIComponent comp){
        componentManager.removeComponent(comp);
    }


    //Input Register
    @Override
    public void addKeyListener(KeyListener listener){
        componentManager.addKeyListener(listener);
    }

    @Override
    public void removeKeyListener(KeyListener listener){
        componentManager.removeKeyListener(listener);
    }

    @Override
    public void addMouseInputListener(BoundedMouseInputListener listener){
        componentManager.addMouseInputListener(listener);
    }

    @Override
    public void removeMouseInputListener(BoundedMouseInputListener listener){
        componentManager.removeMouseInputListener(listener);
    }

    @Override
    public void setInputSource(GUIInputRegister<BoundedMouseInputListener> inputRegister){
        inputTranslator.setInputSource(inputRegister);
    }

    @Override
    public void unsetInputSource(GUIInputRegister<BoundedMouseInputListener> inputRegister){
        inputRegister.removeKeyListener(inputTranslator);
        inputRegister.removeMouseInputListener(inputTranslator);
    }

}

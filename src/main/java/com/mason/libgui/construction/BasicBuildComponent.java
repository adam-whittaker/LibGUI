package com.mason.libgui.construction;

import com.mason.libgui.components.panes.layout.PaneLayout;
import com.mason.libgui.core.component.AbstractUIComponent;
import com.mason.libgui.core.componentManagement.UIComponentContainer;
import com.mason.libstruct.geo.Coord;
import com.mason.libstruct.geo.Size;

import java.util.Optional;
import java.util.Set;

public abstract class BasicBuildComponent extends AbstractBuildComponent{


    private final Optional<AbstractUIComponent> component;
    private final String componentName;


    public BasicBuildComponent(String componentName, Set<String> suppliedResourceNames){
        super(suppliedResourceNames);
        this.component = Optional.empty();
        this.componentName = componentName;
    }


    private void verifyComponentBuilt(){
        if(component.isEmpty()){
            throw new IllegalStateException("Component unbuilt!");
        }
    }

    public AbstractUIComponent getComponent(){
        verifyComponentBuilt();
        return component.get();
    }

    @Override
    public void layout(PaneLayout layout){
        AbstractUIComponent component = getComponent();
        Size size = component.getSize();
        Coord topLeft = layout.centre(componentName, size);
        component.setCoord(topLeft);
    }

    @Override
    public void addToContainer(UIComponentContainer container){
        container.addComponent(getComponent());
    }

}

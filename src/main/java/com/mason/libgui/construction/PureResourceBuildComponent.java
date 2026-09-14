package com.mason.libgui.construction;

import com.mason.libgui.components.panes.layout.PaneLayout;
import com.mason.libgui.core.componentManagement.UIComponentContainer;
import java.util.Set;

public abstract class PureResourceBuildComponent extends AbstractBuildComponent{


    public PureResourceBuildComponent(Set<String> suppliedResourceNames){
        super(suppliedResourceNames);
    }


    @Override
    public void layout(PaneLayout layout){}

    @Override
    public void addToContainer(UIComponentContainer container){}

}

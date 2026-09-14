package com.mason.libgui.construction;

import com.mason.libgui.components.panes.layout.PaneLayout;
import com.mason.libgui.core.componentManagement.UIComponentContainer;

import java.util.Collection;
import java.util.Set;

public interface BuildComponent{


    void registerPreBuildResource(String resourceName);
    void registerPostBuildResource(String resourceName);

    Set<String> requiredPreBuildResourceNames();
    Set<String> requiredPostBuildResourceNames();
    Set<String> suppliedBuildResourceNames();

    void acceptPreBuildResource(BuildResource resource);
    void acceptPostBuildResource(BuildResource resource);

    void build();
    Collection<BuildResource> supplyResources();

    void layout(PaneLayout layout);

    void addToContainer(UIComponentContainer container);

}

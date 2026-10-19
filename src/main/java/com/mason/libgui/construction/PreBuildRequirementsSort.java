package com.mason.libgui.construction;

import java.util.*;

public class PreBuildRequirementsSort{


    public static <T extends BuildComponent> List<BuildComponent> sort(Collection<T> components){
        List<BuildComponent> sorted = new ArrayList<>();
        DependencyMap<T> dependencyMap = new DependencyMap<>(components);

        while(dependencyMap.areReadyElements()){
            BuildComponent current = dependencyMap.nextReadyComponent();
            sorted.add(current);
            dependencyMap.removeDependencies(current);
        }

        if(sorted.size() != components.size()){
            throw new IllegalArgumentException("Dependencies unresolvable! Either there is a cycle in the dependency graph, or required resources are unprovided altogether.");
        }

        return sorted;
    }


    private static class DependencyMap<T extends BuildComponent>{


        private final Map<T, Set<BuildResource<?>>> dependencies;
        private final Queue<T> ready;


        DependencyMap(Collection<T> components){
            dependencies = createDependencyMap(components);
            ready = initializeReadyQueue(dependencies);
        }

        private static <T extends BuildComponent> Map<T, Set<BuildResource<?>>> createDependencyMap(Collection<T> components){
            Map<T, Set<BuildResource<?>>> dependencies = new HashMap<>();
            for(T component : components){
                dependencies.put(component, new HashSet<>(component.requiredPreBuildResources()));
            }
            return dependencies;
        }

        private static <T extends BuildComponent> Queue<T> initializeReadyQueue(Map<T, Set<BuildResource<?>>> dependencies){
            Queue<T> ready = new ArrayDeque<>();
            dependencies.forEach((comp, deps) -> {
                if(deps.isEmpty()){
                    ready.add(comp);
                }
            });
            return ready;
        }


        private boolean areReadyElements(){
            return !ready.isEmpty();
        }

        private BuildComponent nextReadyComponent(){
            return ready.remove();
        }

        private void removeDependencies(BuildComponent currentComponent){
            Set<BuildResource<?>> resourceNames = currentComponent.suppliedBuildResources();
            dependencies.forEach((comp, deps) -> {
                boolean changed = deps.removeAll(resourceNames);
                if(changed && deps.isEmpty()){
                    ready.add(comp);
                }
            });
        }

    }

}

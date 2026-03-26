package com.mason.libgui.core.componentManagement;

import com.mason.libgui.core.component.*;
import com.mason.libgui.core.component.hitbox.HitboxRect;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.mason.libgui.utils.structures.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SimpleUIComponentContainerTest{

    private static class TestHitbox extends HitboxRect{

        boolean withinBoundsReturn = false;


        public TestHitbox(){
            super(new Coord(0, 0), new Size(-1, -1));
        }

        @Override
        public boolean withinBounds(Coord c) {
            return withinBoundsReturn;
        }

    }

    private static class TestComponent extends AbstractUIComponent{
        final String name;

        public TestComponent(String name) {
            super(new TestHitbox());
            this.name = name;
        }

        @Override
        public void render(Graphics2D g) {
            // no-op for tests
        }

        @Override
        public void tick() {
            // no-op for tests
        }

        @Override
        public String toString() {
            return "TestComponent{" + name + '}';
        }
    }

    @Test
    void newContainer_hasEmptyIterator() {
        SimpleUIComponentContainer container = new SimpleUIComponentContainer();

        Iterator<UIComponent> it = container.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    void addComponent_appendsToEnd() {
        SimpleUIComponentContainer container = new SimpleUIComponentContainer();

        UIComponent c1 = new TestComponent("A");
        UIComponent c2 = new TestComponent("B");

        container.addComponent(c1);
        container.addComponent(c2);

        List<UIComponent> seen = new ArrayList<>();
        container.iterator().forEachRemaining(seen::add);

        assertEquals(2, seen.size());
        assertSame(c1, seen.get(0));
        assertSame(c2, seen.get(1));
    }

    @Test
    void addComponentToBack_insertsAtFront() {
        SimpleUIComponentContainer container = new SimpleUIComponentContainer();

        UIComponent c1 = new TestComponent("A");
        UIComponent c2 = new TestComponent("B");
        UIComponent c3 = new TestComponent("C");

        container.addComponent(c1);          // [A]
        container.addComponent(c2);          // [A, B]
        container.addComponentToBack(c3);    // [C, A, B]

        List<UIComponent> seen = new ArrayList<>();
        container.iterator().forEachRemaining(seen::add);

        assertEquals(3, seen.size());
        assertSame(c3, seen.get(0));
        assertSame(c1, seen.get(1));
        assertSame(c2, seen.get(2));
    }

    @Test
    void removeComponent_removesMatchingInstance() {
        SimpleUIComponentContainer container = new SimpleUIComponentContainer();

        UIComponent c1 = new TestComponent("A");
        UIComponent c2 = new TestComponent("B");
        UIComponent c3 = new TestComponent("C");

        container.addComponent(c1);
        container.addComponent(c2);
        container.addComponent(c3);

        container.removeComponent(c2);

        List<UIComponent> seen = new ArrayList<>();
        container.iterator().forEachRemaining(seen::add);

        assertEquals(2, seen.size());
        assertSame(c1, seen.get(0));
        assertSame(c3, seen.get(1));
        assertFalse(seen.contains(c2));
    }

    @Test
    void removeComponent_nonPresentComponent_doesNothing() {
        SimpleUIComponentContainer container = new SimpleUIComponentContainer();

        UIComponent c1 = new TestComponent("A");
        UIComponent c2 = new TestComponent("B");
        UIComponent cX = new TestComponent("X");

        container.addComponent(c1);
        container.addComponent(c2);

        container.removeComponent(cX); // not in container

        List<UIComponent> seen = new ArrayList<>();
        container.iterator().forEachRemaining(seen::add);

        assertEquals(2, seen.size());
        assertSame(c1, seen.get(0));
        assertSame(c2, seen.get(1));
    }

}
package net.onelitefeather.coris.component;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Default {@link Componentable} implementation backed by a {@link HashMap}.
 * Intended to be used via composition by classes that need to support components
 * without duplicating the map bookkeeping.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.6.1
 */
public final class ComponentContainer implements Componentable {

    private final Map<Class<? extends CorisComponent>, CorisComponent> components;

    /**
     * Creates a new, empty component container.
     */
    public ComponentContainer() {
        this(new HashMap<>());
    }

    /**
     * Creates a new component container, defensively copying the given components.
     *
     * @param components the initial components to seed the container with
     */
    public ComponentContainer(Map<Class<? extends CorisComponent>, CorisComponent> components) {
        this.components = new HashMap<>(components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> void add(Class<T> componentClass, T component) {
        this.components.put(componentClass, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> boolean has(Class<T> componentClass) {
        return this.components.containsKey(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> @Nullable T get(Class<T> componentClass) {
        return componentClass.cast(this.components.get(componentClass));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> @Nullable T remove(Class<T> componentClass) {
        return componentClass.cast(this.components.remove(componentClass));
    }

    /**
     * Removes all components from this container.
     */
    public void clear() {
        this.components.clear();
    }
}

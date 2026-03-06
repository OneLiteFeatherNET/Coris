package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link BaseRoom} class is a basic implementation of the {@link Room} interface.
 * It provides a structure for rooms with a unique identifier, a shape, and a collection of components.
 *
 * @author theEvilReaper
 * @version 1.4.0
 * @since 0.1.0
 */
@ApiStatus.Experimental
public class BaseRoom implements Room {

    private final Key identifier;
    private final Map<Class<? extends CorisComponent>, CorisComponent> components;
    private final Shape shape;

    /**
     * Constructs a new BaseRoom instance with the specified identifier and shape.
     *
     * @param identifier the unique identifier for the room
     * @param shape      the shape of the room
     */
    public BaseRoom(Key identifier, Shape shape) {
        this(identifier, new HashMap<>(), shape);
    }

    /**
     * Constructs a new BaseRoom instance with the specified identifier, components, and shape.
     *
     * @param identifier the unique identifier for the room
     * @param components a map of components where the key is the class of the component and the value is the corresponding RoomComponent
     * @param shape      the shape of the room
     */
    public BaseRoom(
            Key identifier,
            Map<Class<? extends CorisComponent>, CorisComponent> components,
            Shape shape
    ) {
        this.identifier = identifier;
        this.components = new HashMap<>(components);
        this.shape = shape;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> void add(Class<T> componentClass, T component) {
        this.components.computeIfAbsent(componentClass, k -> component);
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
     * {@inheritDoc}
     */
    @Override
    public Key identifier() {
        return identifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape shape() {
        return this.shape;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Key o1, Key o2) {
        return o1.compareTo(o2);
    }
}

package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.ComponentContainer;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@link BaseRoom} class is a basic implementation of the {@link Room} interface.
 * It provides a structure for rooms with a unique identifier, a shape, and a collection of components.
 *
 * @author theEvilReaper
 * @version 1.4.2
 * @since 0.1.0
 */
@ApiStatus.Experimental
public class BaseRoom implements Room {

    private final Key identifier;
    private final ComponentContainer components;
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
        this.components = new ComponentContainer(components);
        this.shape = shape;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> void add(Class<T> componentClass, T component) {
        this.components.add(componentClass, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> boolean has(Class<T> componentClass) {
        return this.components.has(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> @Nullable T get(Class<T> componentClass) {
        return this.components.get(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> @Nullable T remove(Class<T> componentClass) {
        return this.components.remove(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Key identifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Room room)) return false;
        return Objects.equals(this.identifier, room.identifier());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape shape() {
        return this.shape;
    }

    @Override
    public int compareTo(Room other) {
        return this.identifier.compareTo(other.identifier());
    }
}

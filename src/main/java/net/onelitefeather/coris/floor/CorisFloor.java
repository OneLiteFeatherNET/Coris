package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.ComponentContainer;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.room.Room;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@link CorisFloor<K>} is a default implementation of the {@link Floor} interface, which can be used to manage a collection of rooms.
 * If your use case doesn't require a custom implementation, you can use this class directly.
 *
 * @param <K> the type of room that the floor can hold, typically extending from {@link Room}.
 * @author theEvilReaper
 * @version 1.1.2
 * @since 0.1.0
 */
public final class CorisFloor<K extends Room> implements Floor<K> {

    private final Key identifier;
    private final Map<Key, K> data;
    private final ComponentContainer components;
    private final @Nullable Shape shape;

    /**
     * Master constructor that fully initializes the floor.
     * All other constructors delegate to this one.
     */
    public CorisFloor(
            Key identifier,
            Map<Key, K> data,
            Map<Class<? extends CorisComponent>, CorisComponent> components,
            @Nullable Shape shape
    ) {
        this.identifier = identifier;
        this.data = new HashMap<>(data);
        this.components = new ComponentContainer(components);
        this.shape = shape;
    }

    /**
     * Creates a new empty floor with the given identifier and shape.
     *
     * @param identifier the identifier of the floor
     * @param shape      the shape of the floor
     */
    public CorisFloor(Key identifier, Shape shape) {
        this(identifier, new HashMap<>(), new HashMap<>(), shape);
    }

    /**
     * Creates a new empty floor with the given identifier.
     */
    public CorisFloor(Key identifier) {
        this(identifier, new HashMap<>(), new HashMap<>(), null);
    }

    /**
     * Creates a new floor with the given identifier, data, and components, but without a shape.
     *
     * @param identifier the identifier of the floor
     * @param data       the data of the floor
     * @param components the components of the floor
     */
    public CorisFloor(
            Key identifier,
            Map<Key, K> data,
            Map<Class<? extends CorisComponent>, CorisComponent> components
    ) {
        this(identifier, data, components, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key objectId, K object) {
        this.data.put(objectId, object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Key id) {
        this.data.remove(id);
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
    public <T extends CorisComponent> @Nullable T remove(Class<T> componentClass) {
        return this.components.remove(componentClass);
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
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Key identifier() {
        return this.identifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable Shape shape() {
        return this.shape;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.data.clear();
        this.components.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CorisFloor<?> that = (CorisFloor<?>) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @UnmodifiableView Map<Key, K> getData() {
        return Collections.unmodifiableMap(this.data);
    }
}

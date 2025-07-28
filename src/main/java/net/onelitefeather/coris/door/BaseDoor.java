package net.onelitefeather.coris.door;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * The {@link BaseDoor} is an abstract class that implements the {@link Door} interface.
 * It provides a base implementation for doors in the system, including properties to handle different states and characteristics of a door.
 *
 * @author theEvilReaper
 * @version 1.1.0
 * @since 0.1.0
 */
public abstract non-sealed class BaseDoor implements Door {

    protected final UUID uuid;
    protected final Key key;
    protected final DoorFace face;
    protected final Shape shape;
    protected AnimationState animationState;

    private final Map<Class<? extends CorisComponent>, CorisComponent> componentMap;

    /**
     * Creates a new instance from the door class with the given values.
     *
     * @param uuid         the uuid for the door
     * @param key          the {@link Key} for the door
     * @param face         the face of the door
     * @param shape        the shape from the door
     * @param componentMap a map of components associated with the door
     */
    protected BaseDoor(
            @NotNull UUID uuid,
            @NotNull Key key,
            @NotNull DoorFace face,
            @NotNull Shape shape,
            @NotNull Map<Class<? extends CorisComponent>, CorisComponent> componentMap
    ) {
        this.uuid = uuid;
        this.key = key;
        this.face = face;
        this.shape = shape;
        this.animationState = AnimationState.IDLE;
        this.componentMap = componentMap;
    }

    /**
     * Creates a new instance from the door class with the given values.
     *
     * @param key   the {@link Key} for the door
     * @param face  the face of the door
     * @param shape the shape from the door
     */
    protected BaseDoor(@NotNull Key key, @NotNull DoorFace face, @NotNull Shape shape) {
        this(UUID.randomUUID(), key, face, shape, new HashMap<>());
    }

    @Override
    public <T extends CorisComponent> void add(@NotNull Class<T> componentClass, @NotNull T component) {
        this.componentMap.put(componentClass, component);
    }

    @Override
    public <T extends CorisComponent> boolean has(@NotNull Class<T> componentClass) {
        return this.componentMap.containsKey(componentClass);
    }

    @Override
    public <T extends CorisComponent> @Nullable T get(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.componentMap.get(componentClass));
    }

    @Override
    public <T extends CorisComponent> @Nullable T remove(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.componentMap.remove(componentClass));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BaseDoor baseDoor)) return false;
        return Objects.equals(uuid, baseDoor.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public @NotNull UUID id() {
        return this.uuid;
    }

    @Override
    public @NotNull Key key() {
        return this.key;
    }

    @Override
    public @NotNull DoorFace face() {
        return this.face;
    }

    @Override
    public @NotNull Shape shape() {
        return this.shape;
    }
}
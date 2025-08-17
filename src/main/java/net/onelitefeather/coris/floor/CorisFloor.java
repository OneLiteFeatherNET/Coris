package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@link CorisFloor<K>} is a default implementation of the {@link Floor} interface, which can be used to manage a collection of rooms.
 * If your use case doesn't require a custom implementation, you can use this class directly.
 *
 * @param <K> the type of room that the floor can hold, typically extending from {@link Room}.
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
public final class CorisFloor<K extends Room> implements Floor<K> {

    private final Key identifier;
    private final Map<Key, K> data;
    private final Map<Class<? extends CorisComponent>, CorisComponent> components;

    /**
     * Creates a new floor with the given identifier.
     *
     * @param identifier the identifier of the floor
     */
    public CorisFloor(@NotNull Key identifier) {
        this.identifier = identifier;
        this.data = new HashMap<>();
        this.components = new HashMap<>();
    }

    /**
     * Creates a new floor with the given identifier, data, and components.
     *
     * @param identifier the identifier of the floor
     * @param data       the data of the floor
     * @param components the components of the floor
     */
    public CorisFloor(
            @NotNull Key identifier,
            @NotNull Map<Key, K> data,
            @NotNull Map<Class<? extends CorisComponent>, CorisComponent> components
    ) {
        this.identifier = identifier;
        this.data = data;
        this.components = components;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(@NotNull Key objectId, @NotNull K object) {
        this.data.computeIfAbsent(objectId, k -> object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(@NotNull Key id) {
        this.data.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> void add(@NotNull Class<T> componentClass, @NotNull T component) {
        this.components.computeIfAbsent(componentClass, k -> component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> @Nullable T remove(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.components.remove(componentClass));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> boolean has(@NotNull Class<T> componentClass) {
        return this.components.containsKey(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends CorisComponent> @Nullable T get(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.components.get(componentClass));
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
    public @NotNull Key identifier() {
        return this.identifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        if (!this.data.isEmpty()) {
            this.data.clear();
        }
        if (this.components.isEmpty()) return;
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
    public @NotNull @UnmodifiableView Map<Key, K> getData() {
        return Map.copyOf(this.data);
    }
}

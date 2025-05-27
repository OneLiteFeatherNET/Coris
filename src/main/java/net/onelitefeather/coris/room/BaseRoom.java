package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link BaseRoom} class is a basic implementation of the {@link Room} interface.
 * It provides a structure for rooms with a unique identifier, a shape, and a collection of components.
 *
 * @author theEvilReaper
 * @version 1.2.0
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
    public BaseRoom(@NotNull Key identifier, @NotNull Shape shape) {
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
            @NotNull Key identifier,
            @NotNull Map<Class<? extends CorisComponent>, CorisComponent> components,
            @NotNull Shape shape
    ) {
        this.identifier = identifier;
        this.components = new HashMap<>();
        this.shape = shape;
    }


    @Override
    public <T extends CorisComponent> void add(@NotNull Class<T> componentClass, @NotNull T component) {
        this.components.computeIfAbsent(componentClass, k -> component);
    }

    @Override
    public <T extends CorisComponent> boolean has(@NotNull Class<T> componentClass) {
        return this.components.containsKey(componentClass);
    }

    @Override
    public <T extends CorisComponent> @Nullable T get(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.components.get(componentClass));
    }

    @Override
    public <T extends CorisComponent> @Nullable T remove(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.components.remove(componentClass));
    }

    @Override
    public @NotNull Key identifier() {
        return identifier;
    }

    @Override
    public @NotNull Shape shape() {
        return this.shape;
    }

    @Override
    public int compare(@NotNull Key o1, @NotNull Key o2) {
        return o1.compareTo(o2);
    }
}

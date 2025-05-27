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

public final class CorisFloor<K extends Room> implements Floor<K> {

    private final Key identifier;
    private final Map<Key, K> data;
    private final Map<Class<? extends CorisComponent>, CorisComponent> components;

    public CorisFloor(@NotNull Key identifier) {
        this.identifier = identifier;
        this.data = new HashMap<>();
        this.components = new HashMap<>();
    }

    public CorisFloor(
            @NotNull Key identifier,
            @NotNull Map<Key, K> data,
            @NotNull Map<Class<? extends CorisComponent>, CorisComponent> components
    ) {
        this.identifier = identifier;
        this.data = data;
        this.components = components;
    }

    @Override
    public void add(@NotNull Key objectId, @NotNull K object) {
        this.data.computeIfAbsent(objectId, k -> object);
    }

    @Override
    public void remove(@NotNull Key id) {
        this.data.remove(id);
    }

    @Override
    public <T extends CorisComponent> void add(@NotNull Class<T> componentClass, @NotNull T component) {
        this.components.computeIfAbsent(componentClass, k -> component);
    }

    @Override
    public <T extends CorisComponent> @Nullable T remove(@NotNull Class<T> componentClass) {
        return componentClass.cast(this.components.remove(componentClass));
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
    public boolean isEmpty() {
        System.out.println("Checking if floor is empty: " + this.data.isEmpty());
        data.forEach((corisComponent, key) -> {
            System.out.println("Key: " + corisComponent + ", Room: " + key);
        });
        return this.data.isEmpty();
    }

    @Override
    public @NotNull Key identifier() {
        return this.identifier;
    }

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

    @Override
    public @NotNull @UnmodifiableView Map<Key, K> getData() {
        return Map.copyOf(this.data);
    }
}

package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApiStatus.Experimental
public abstract class BaseRoom implements Room {

    private final Key identifier;
    private final Map<String, Object> metaData;
    private final Shape shape;

    protected BaseRoom(@NotNull Key identifier, @NotNull Shape shape) {
        this(identifier, new HashMap<>(), shape);
    }

    protected BaseRoom(
            @NotNull Key identifier,
            @NotNull Map<String, Object> metaData,
            @NotNull Shape shape
    ) {
        this.identifier = identifier;
        this.metaData = metaData;
        this.shape = shape;
    }

    @Override
    public void addMetaData(@NotNull String key, @NotNull Object value) {
        this.metaData.put(key, value);
    }

    @Override
    public void removeMetaData(@NotNull String key) {
        this.metaData.remove(key);
    }

    @Override
    public @NotNull Optional<@Nullable Object> getMetaData(@NotNull String key) {
        return Optional.ofNullable(this.metaData.get(key));
    }

    @Override
    public boolean hasMetaData(@NotNull String key) {
        return this.metaData.containsKey(key);
    }

    @Override
    public @NotNull @UnmodifiableView Map<String, Object> metaData() {
        return Collections.unmodifiableMap(this.metaData);
    }

    @Override
    public @NotNull Key identifier() {
        return identifier;
    }

    @Override
    public long creationDate() {
        return (long) this.metaData().getOrDefault(META_DATA_KEY_CREATION_DATE, -1);
    }

    @Override
    public long updateDate() {
        return (long) this.metaData().getOrDefault(META_DATA_KEY_UPDATE_DATE, -1);
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

package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.phoca.metadata.Metadata;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class CorisFloor<T extends Room> implements Floor<T> {

    private final Key identifier;
    private final Map<Key, T> data;
    private final Map<String, Object> metaData;

    public CorisFloor(@NotNull Key identifier) {
        this.identifier = identifier;
        this.data = new HashMap<>();
        this.metaData = new HashMap<>();
    }

    public CorisFloor(@NotNull Key identifier, @NotNull Map<Key, T> data, @NotNull Map<String, Object> metaData) {
        this.identifier = identifier;
        this.data = data;
        this.metaData = metaData;
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
    public boolean hasMetaData(@NotNull String key) {
        return this.metaData.containsKey(key);
    }

    @Override
    public @NotNull Optional<@Nullable Object> getMetaData(@NotNull String key) {
        return Optional.ofNullable(this.metaData.get(key));
    }

    @Override
    public @NotNull @UnmodifiableView Map<String, Object> metaData() {
        return Map.copyOf(this.metaData);
    }

    @Override
    public long creationDate() {
        return (long) this.metaData.getOrDefault(Metadata.META_DATA_KEY_CREATION_DATE, -1L);
    }

    @Override
    public long updateDate() {
        return (long) this.metaData.getOrDefault(Metadata.META_DATA_KEY_UPDATE_DATE, -1L);
    }

    @Override
    public void add(@NotNull Key objectId, @NotNull T object) {
        this.data.put(objectId, object);
    }

    @Override
    public void remove(@NotNull Key tId) {
        this.data.remove(tId);
    }

    @Override
    public @NotNull Key identifier() {
        return this.identifier;
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public void clear() {
        if (this.data.isEmpty()) return;
        this.data.clear();
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
    public @NotNull @UnmodifiableView Map<Key, T> getData() {
        return Map.copyOf(this.data);
    }
}

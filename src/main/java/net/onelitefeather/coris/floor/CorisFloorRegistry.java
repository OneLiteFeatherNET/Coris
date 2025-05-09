package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.stream.Collectors;

public class CorisFloorRegistry<T extends Floor<? extends Room>> implements FloorRegistry<T> {

    private final Map<Key, T> floors;

    public CorisFloorRegistry() {
        this.floors = new HashMap<>();
    }

    @Override
    public void add(@NotNull Key floorId, @NotNull T floor) {
        this.floors.put(floorId, floor);
    }

    @Override
    public void remove(@NotNull Key id) {
        this.floors.remove(id);
    }

    @Override
    public void clear() {
        if (this.floors.isEmpty()) return;
        this.floors.clear();
    }

    @Override
    public @NotNull Optional<@Nullable T> get(Key id) {
        return Optional.ofNullable(this.floors.get(id));
    }

    @Override
    public @UnmodifiableView @NotNull Map<Key, T> getFloors() {
        return Collections.unmodifiableMap(this.floors);
    }

    @Override
    public @UnmodifiableView @NotNull Map<Key, T> getFloors(@NotNull Comparator<T> comparator) {
        return this.floors.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }
}

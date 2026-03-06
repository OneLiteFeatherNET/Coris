package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A concrete implementation of {@link FloorRegistry} that provides floor management functionality
 * using a HashMap-based storage mechanism.
 * <p>
 * This registry allows for efficient storage, retrieval, and management of floor objects,
 * where each floor is uniquely identified by a {@link Key}. The implementation is designed
 * to handle any floor type that extends the base {@link Floor} class.
 * </p>
 * <p>
 * <strong>Thread Safety:</strong> This implementation is not thread-safe for write operations.
 * External synchronization is required when multiple threads modify the registry concurrently.
 * </p>
 *
 * @param <T> the type of floor managed by this registry, must extend {@link Floor}
 *           with a room type that extends {@link Room}
 * @author theEvilReaper
 * @version 1.1.0
 * @since 0.1.0
 * @see FloorRegistry
 * @see Floor
 * @see Room
 */
public class CorisFloorRegistry<T extends Floor<? extends Room>> implements FloorRegistry<T> {

    private final Map<Key, T> floors;

    /**
     * Creates a new instance of {@link CorisFloorRegistry}.
     */
    public CorisFloorRegistry() {
        this.floors = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key floorId, T floor) {
        this.floors.put(floorId, floor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Key id) {
        this.floors.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        if (this.floors.isEmpty()) return;
        this.floors.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<@Nullable T> get(Key id) {
        return Optional.ofNullable(this.floors.get(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @UnmodifiableView Map<Key, T> getFloors() {
        return Collections.unmodifiableMap(this.floors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @UnmodifiableView Map<Key, T> getFloors(Comparator<T> comparator) {
        return this.floors.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }
}

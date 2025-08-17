package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.*;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * The {@link FloorRegistry<T>} is an interface that defines a registry for managing floors.
 *
 * @param <T> the type of floor that the registry can hold, typically extending from {@link Floor}.
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
@ApiStatus.Experimental
public interface FloorRegistry<T extends Floor<? extends Room>> {

    /**
     * Adds a new floor to the registry.
     *
     * @param floorId the id of the floor
     * @param floor   the floor to add
     */
    void add(@NotNull Key floorId, @NotNull T floor);

    /**
     * Removes a floor from the registry.
     *
     * @param id the id of the floor
     */
    void remove(@NotNull Key id);

    /**
     * Clears the registry.
     */
    void clear();

    /**
     * Returns the floor by the given id.
     *
     * @param id the id of the floor
     * @return the floor
     */
    @NotNull Optional<@Nullable T> get(Key id);

    /**
     * Returns an unmodifiable view of the floors.
     *
     * @return the floors
     */
    @UnmodifiableView
    @NotNull Map<Key, T> getFloors();

    /**
     * Returns an unmodifiable view of the floors.
     *
     * @param comparator the comparator to sort the floors
     * @return the floors
     */
    @UnmodifiableView
    @NotNull Map<Key, T> getFloors(Comparator<T> comparator);
}

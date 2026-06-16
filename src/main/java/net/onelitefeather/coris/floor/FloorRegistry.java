package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * The {@link FloorRegistry<T>} is an interface that defines a registry for managing floors.
 *
 * @param <T> the type of floor that the registry can hold, typically extending from {@link Floor}.
 * @author theEvilReaper
 * @version 1.2.0
 * @since 0.1.0
 */
@ApiStatus.Experimental
public interface FloorRegistry<T extends Floor<Room>> {

    /**
     * Adds a new floor to the registry.
     *
     * @param floorId the id of the floor
     * @param floor   the floor to add
     */
    void add(Key floorId, T floor);

    /**
     * Removes a floor from the registry.
     *
     * @param id the id of the floor
     */
    void remove(Key id);

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
    Optional<T> get(Key id);

    /**
     * Returns an unmodifiable view of the floors.
     *
     * @return the floors
     */
    @UnmodifiableView
    Map<Key, T> getFloors();

    /**
     * Returns an unmodifiable view of the floors.
     *
     * @param comparator the comparator to sort the floors
     * @return the floors
     */
    @UnmodifiableView
    Map<Key, T> getFloors(Comparator<T> comparator);

    /**
     * Returns the floor containing the specified coordinate point, if any.
     *
     * @param point the coordinate point to check
     * @return an Optional containing the floor, or empty if not inside any floor
     */
    Optional<T> getFloorAt(Point point);

    /**
     * Finds the exact room containing the specified coordinate point across all registered floors.
     *
     * @param point the coordinate point to check
     * @return an Optional containing the room, or empty if not inside any room
     */
    default Optional<Room> getRoomAt(Point point) {
        return getFloorAt(point).flatMap(floor ->
                floor.getData().values().stream()
                        .filter(room -> room.shape().intersect(point))
                        .findFirst()
        );
    }
}
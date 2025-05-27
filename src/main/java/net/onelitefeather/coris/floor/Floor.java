package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.Componentable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * The {@link Floor} interface represents a structure that can hold different types of objects defined by the type parameter <T>.
 * It provides methods to add, remove, and manage these objects, as well as metadata associated with the floor.
 *
 * @param <T> the type of objects that the floor can hold, typically extending from a class.
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
@ApiStatus.Experimental
public interface Floor<T> extends Componentable {

    /**
     * Adds a new object to the floor.
     *
     * @param objectId the id of the object
     * @param object   the object
     */
    void add(@NotNull Key objectId, @NotNull T object);

    /**
     * Removes an object from the floor.
     *
     * @param id the id of the object
     */
    void remove(@NotNull Key id);

    /**
     * Returns the id from a floor object.
     *
     * @return the object id
     */
    @NotNull Key identifier();

    /**
     * Returns an indicator if the floor contains any kind of rooms or not.
     *
     * @return true if the floor is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Triggers a clear operation on the floor which depends on the implementation.
     * For example, it could remove all objects or reset the floor to its initial state.
     */
    void clear();

    /**
     * Returns an unmodifiable view of the data.
     *
     * @return the data
     */
    @UnmodifiableView
    @NotNull Map<Key, T> getData();
}

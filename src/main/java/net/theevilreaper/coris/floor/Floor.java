package net.theevilreaper.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.phoca.metadata.Metadata;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

@ApiStatus.Experimental
public interface Floor<T> extends Metadata {

    String METADATA_NAME_KEY = "name";

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

    boolean isEmpty();

    void clear();

    /**
     * Returns an unmodifiable view of the data.
     *
     * @return the data
     */
    @NotNull
    @UnmodifiableView
    Map<Key, T> getData();

}

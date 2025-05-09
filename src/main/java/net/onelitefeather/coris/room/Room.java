package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.onelitefeather.phoca.metadata.Metadata;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * The {@link Room} interface represents a room which describes a specific area.
 * If the area is a room in a world or building is up to the implementation.
 * <p>
 * A room must have a {@link Shape} which describes the area of the room itself.
 * There are several shapes available to use. If you need a custom shape you can implement it by yourself.
 * <p>
 * If the use case requires additional information to store, there is a metadata system included into the room.
 * The metadata system is a key-value store where you can store any kind of information.
 * Each data only available during the lifecycle of a room reference.
 * When you need to store them into a database, you will need to serialize them by your own.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiStatus.Experimental
public interface Room extends Metadata, Comparator<Key> {

    /**
     * Returns the identifier of the room.
     *
     * @return the identifier
     */
    @NotNull Key identifier();

    /**
     * Returns the shape of the room.
     *
     * @return the shape
     */
    @NotNull Shape<? extends Point> shape();
}

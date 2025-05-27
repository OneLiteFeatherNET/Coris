package net.onelitefeather.coris.door;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * The Door interface represents a door structure within the system that can be opened, locked and unlocked.
 * It defines the core functionality and properties that all door implementations must provide.
 * Each door has a unique identifier, a key, a face direction and a shape that defines its physical boundaries.
 * <p>
 * This interface is sealed and can only be implemented by the {@link BaseDoor} class and its subclasses,
 * ensuring type safety and controlled inheritance throughout the door system.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
public sealed interface Door permits BaseDoor {

    /**
     * Triggers the animation for the door.
     *
     * @param animationState the state of the animation
     */
    void playAnimation(@NotNull AnimationState animationState);

    /**
     * Triggers the open logic for the door.
     */
    void open();

    /**
     * Triggers the close logic for the door
     */
    void unlock();

    /**
     * Set's the new locked state.
     *
     * @param locked the state to set
     */
    void setLocked(boolean locked);

    /**
     * Returns an indication value if the door is locked or not.
     *
     * @return true when its locked otherwise false
     */
    boolean isLocked();

    /**
     * The unique identifier from the door which is a {@link UUID}.
     *
     * @return the given identifier
     */
    @NotNull UUID id();

    /**
     * Returns the given {@link Key} from the adventure library.
     * Note this key should not be used an identifier because they are not unique.
     *
     * @return the given key
     */
    @NotNull Key key();

    /**
     * Returns the given {@link DoorFace} from the door.
     *
     * @return the face reference.
     */
    @NotNull DoorFace face();

    /**
     * Returns the {@link Shape} reference from the door instance.
     *
     * @return the shape reference
     */
    @NotNull Shape shape();

}
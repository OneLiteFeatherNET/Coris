package net.theevilreaper.coris.door.event;

import net.minestom.server.event.trait.InstanceEvent;
import net.minestom.server.instance.Instance;
import net.theevilreaper.coris.door.Door;
import org.jetbrains.annotations.NotNull;

/**
 * The event can be used to indicates that a {@link Door} was closed.
 * You need to handle the close animation on your own.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
public class DoorCloseEvent implements InstanceEvent {

    private final Door door;
    private final Instance instance;

    /**
     * Creates a new event instance with the given door and instance.
     *
     * @param door     the door that was closed
     * @param instance the instance where the door was closed
     */
    public DoorCloseEvent(@NotNull Door door, @NotNull Instance instance) {
        this.door = door;
        this.instance = instance;
    }

    /**
     * The door that was closed.
     *
     * @return the door that was closed
     */
    public @NotNull Door getDoor() {
        return door;
    }

    @Override
    public @NotNull Instance getInstance() {
        return this.instance;
    }
}

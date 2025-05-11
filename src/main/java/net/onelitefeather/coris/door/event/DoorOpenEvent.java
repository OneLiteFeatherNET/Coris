package net.onelitefeather.coris.door.event;

import net.minestom.server.event.trait.CancellableEvent;
import net.minestom.server.event.trait.InstanceEvent;
import net.minestom.server.instance.Instance;
import net.onelitefeather.coris.door.Door;
import org.jetbrains.annotations.NotNull;

/**
 * The event can be used to indicates that a {@link Door} was opened.
 * This event is cancellable, which means that the event can be cancelled and the door will not be opened for example.
 * You need to implement the cancellation of the open animation on your own.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
public class DoorOpenEvent implements InstanceEvent, CancellableEvent {

    private final Door door;
    private final Instance instance;
    private boolean cancelled;

    /**
     * Creates a new event instance with the given door and instance.
     *
     * @param door     the door that was opened
     * @param instance the instance where the door was opened
     */
    public DoorOpenEvent(@NotNull Door door, @NotNull Instance instance) {
        this.door = door;
        this.instance = instance;
    }

    /**
     * Set the cancelled state of the event.
     *
     * @param cancelled the new state
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns if the event is cancelled or not.
     *
     * @return true if the event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * The door that was opened.
     *
     * @return the door that was opened
     */
    public @NotNull Door getDoor() {
        return door;
    }

    @Override
    public @NotNull Instance getInstance() {
        return this.instance;
    }
}

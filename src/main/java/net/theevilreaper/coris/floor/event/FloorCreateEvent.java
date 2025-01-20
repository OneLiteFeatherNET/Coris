package net.theevilreaper.coris.floor.event;

import net.minestom.server.event.trait.CancellableEvent;
import net.theevilreaper.coris.floor.Floor;
import net.theevilreaper.coris.room.Room;
import org.jetbrains.annotations.NotNull;

/**
 * The event will only be called when the floor has no invalid data.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
public final class FloorCreateEvent<T extends Floor<? extends Room>> implements CancellableEvent {

    private final T floor;
    private boolean cancelled;

    /**
     * Create a new instance of the {@link FloorCreateEvent}.
     *
     * @param floor   the floor who is involved into the event
     */
    public FloorCreateEvent(@NotNull T floor) {
        this.floor = floor;
    }

    /**
     * Create a new instance of the {@link FloorCreateEvent}.
     *
     * @param floor     the floor who is involved into the event
     * @param cancelled if the event is cancelled or not
     */
    public FloorCreateEvent(@NotNull T floor, boolean cancelled) {
        this.floor = floor;
        this.cancelled = cancelled;
    }

    /**
     * Set if the event should be cancelled or not
     *
     * @param cancel true if the event should be cancelled, false otherwise
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * Returns a boolean indicator if the event is cancelled.
     *
     * @return True if the event is cancelled otherwise false
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Returns the involved {@link Floor}.
     *
     * @return the given floor
     */
    @NotNull
    public T getFloor() {
        return this.floor;
    }

}

package net.theevilreaper.coris.floor.event;

import net.theevilreaper.coris.floor.Floor;
import net.theevilreaper.coris.room.Room;
import org.jetbrains.annotations.NotNull;

/**
 * The event will be called when a floor should be removed.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("java:S6206")
public record FloorRemoveEvent<T extends Floor<? extends Room>>(
        @NotNull T floor
) {
}

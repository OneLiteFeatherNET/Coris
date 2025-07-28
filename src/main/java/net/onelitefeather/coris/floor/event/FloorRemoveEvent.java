package net.onelitefeather.coris.floor.event;

import net.onelitefeather.coris.floor.Floor;
import net.onelitefeather.coris.room.Room;
import org.jetbrains.annotations.NotNull;

/**
 * The event will be called when a floor should be removed.
 *
 * @param <T>   the type of floor that is being removed, typically extending from {@link Floor}.
 * @param floor the floor that is being removed
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
@SuppressWarnings("java:S6206")
public record FloorRemoveEvent<T extends Floor<? extends Room>>(
        @NotNull T floor
) {
}

package net.theevilreaper.coris.door;

import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * The enum includes all possible directions where a room can have doors.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DoorFace {

    NORTH(0, Direction.NORTH, "north", "up"),
    EAST(1, Direction.EAST, "east", "right"),
    SOUTH(2, Direction.SOUTH, "south", "down"),
    WEST(3, Direction.WEST, "west", "left");

    private final int id;
    private final Direction direction;
    private final String name;
    private final String alias;

    private static final DoorFace[] VALUES = values();

    /**
     * Creates a reference from a DoorFace.
     *
     * @param id        the id for the door
     * @param direction the direction for the door
     * @param name      the name for the door
     * @param alias     the alias for the door
     */
    DoorFace(int id, @NotNull Direction direction, @NotNull String name, @NotNull String alias) {
        this.id = id;
        this.direction = direction;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Returns the id from the door.
     *
     * @return the given id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the direction from a door entry.
     *
     * @return the give direction
     */
    public @NotNull Direction getDirection() {
        return direction;
    }

    /**
     * Returns the name from a door entry.
     *
     * @return the give name
     */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Returns the alias from a door entry.
     *
     * @return the give alias
     */
    public @NotNull String getAlias() {
        return alias;
    }

    /**
     * Returns an enum entry from a door by his id.
     *
     * @param id the id from the door
     * @return the fetched entry or null
     */
    public static @NotNull Optional<DoorFace> getFace(int id) {
        if (id < 0 || id >= VALUES.length) return Optional.empty();
        return Optional.of(VALUES[id]);
    }

    /**
     * Returns an enum entry from a door by his name.
     *
     * @param name the name from the door
     * @return the fetched entry or null
     */
    public static @NotNull Optional<DoorFace> getFace(@NotNull String name) {
        DoorFace face = null;
        for (int i = 0; i < VALUES.length && face == null; i++) {
            DoorFace current = VALUES[i];
            if (current.getName().equalsIgnoreCase(name) || current.getAlias().equalsIgnoreCase(name)) {
                face = current;
            }
        }
        return Optional.ofNullable(face);
    }
}

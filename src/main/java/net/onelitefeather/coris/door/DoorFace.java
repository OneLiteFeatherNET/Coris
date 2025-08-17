package net.onelitefeather.coris.door;

import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Represents the possible directions where a room can have doors.
 * <p>
 * This enum provides a mapping between directional concepts and their corresponding
 * Minestom Direction objects, along with human-readable names and aliases for
 * easier identification and user input handling.
 * </p>
 * <p>
 * Each door face includes:
 * <ul>
 *   <li>A Minestom Direction reference for coordinate calculations</li>
 *   <li>A primary name for identification</li>
 *   <li>An alias for alternative naming (often relative directions)</li>
 * </ul>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
public enum DoorFace {

    /**
     * Represents a door facing north.
     * <p>
     * This corresponds to the negative Z direction in Minecraft coordinates.
     * The alias "up" refers to the upward direction on a traditional map view.
     * </p>
     */
    NORTH(Direction.NORTH, "north", "up"),

    /**
     * Represents a door facing east.
     * <p>
     * This corresponds to the positive X direction in Minecraft coordinates.
     * The alias "right" refers to the rightward direction on a traditional map view.
     * </p>
     */
    EAST(Direction.EAST, "east", "right"),

    /**
     * Represents a door facing south.
     * <p>
     * This corresponds to the positive Z direction in Minecraft coordinates.
     * The alias "down" refers to the downward direction on a traditional map view.
     * </p>
     */
    SOUTH(Direction.SOUTH, "south", "down"),

    /**
     * Represents a door facing west.
     * <p>
     * This corresponds to the negative X direction in Minecraft coordinates.
     * The alias "left" refers to the leftward direction on a traditional map view.
     * </p>
     */
    WEST(Direction.WEST, "west", "left");

    /**
     * The Minestom Direction associated with this door face.
     * Used for coordinate calculations and positioning.
     */
    private final Direction direction;

    /**
     * The primary name identifier for this door face.
     * Typically matches the cardinal direction name.
     */
    private final String name;

    /**
     * An alternative name for this door face.
     * Often represents relative directions (up, down, left, right).
     */
    private final String alias;

    /**
     * Cached array of all enum values for performance optimization.
     * Avoids repeated calls to {@code values()} method.
     */
    private static final DoorFace[] VALUES = values();

    /**
     * Constructs a DoorFace enum constant.
     *
     * @param direction the Minestom Direction associated with this door face
     * @param name      the primary name identifier for this door face
     * @param alias     the alternative name for this door face
     * @throws NullPointerException if any parameter is null
     */
    DoorFace(@NotNull Direction direction, @NotNull String name, @NotNull String alias) {
        this.direction = direction;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Returns the Minestom Direction associated with this door face.
     * <p>
     * This direction can be used for coordinate calculations, block placement,
     * and determining spatial relationships in the game world.
     * </p>
     *
     * @return the Minestom Direction, never null
     */
    public @NotNull Direction direction() {
        return direction;
    }

    /**
     * Returns the primary name of this door face.
     * <p>
     * The name is typically the cardinal direction (north, south, east, west)
     * and can be used for display purposes or configuration files.
     * </p>
     *
     * @return the primary name, never null
     */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Returns the alias of this door face.
     * <p>
     * The alias provides an alternative way to refer to the door face,
     * often using relative directions (up, down, left, right) that might
     * be more intuitive for users in certain contexts.
     * </p>
     *
     * @return the alias name, never null
     */
    public @NotNull String getAlias() {
        return alias;
    }

    /**
     * Retrieves a DoorFace by its ordinal position.
     * <p>
     * The ordinal corresponds to the declaration order of the enum constants:
     * <ul>
     *   <li>0 = NORTH</li>
     *   <li>1 = EAST</li>
     *   <li>2 = SOUTH</li>
     *   <li>3 = WEST</li>
     * </ul>
     *
     * @param id the ordinal position (0-based index)
     * @return an Optional containing the DoorFace if the id is valid,
     * or empty if the id is out of bounds
     */
    public static @NotNull Optional<DoorFace> getFace(int id) {
        if (id < 0 || id >= VALUES.length) return Optional.empty();
        return Optional.of(VALUES[id]);
    }

    /**
     * Retrieves a DoorFace by its name or alias.
     * <p>
     * This method performs a case-insensitive search through all door faces,
     * checking both the primary name and alias of each entry.
     * The first matching entry is returned.
     * </p>
     * <p>
     * Valid input examples:
     * <ul>
     *   <li>"north", "North", "NORTH" → NORTH</li>
     *   <li>"up", "Up", "UP" → NORTH</li>
     *   <li>"east", "right" → EAST</li>
     *   <li>"south", "down" → SOUTH</li>
     *   <li>"west", "left" → WEST</li>
     * </ul>
     *
     * @param name the name or alias to search for (case-insensitive)
     * @return an Optional containing the matching DoorFace, or empty if no match is found
     * @throws NullPointerException if the name is null
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
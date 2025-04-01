package net.theevilreaper.coris.door;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.theevilreaper.coris.shape.Shape;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public abstract non-sealed class BaseDoor implements Door {

    protected final UUID uuid;
    protected final Key key;
    protected final DoorFace face;
    protected final Shape<? extends Point> shape;

    protected boolean locked;
    protected AnimationState animationState;

    /**
     * Creates a new instance from the door class with the given values.
     * @param uuid the uuid for the door
     * @param key the {@link Key} for the door
     * @param face the face of the door
     * @param shape the shape from the door
     */
    BaseDoor(@NotNull UUID uuid, @NotNull Key key, @NotNull DoorFace face,  @NotNull Shape<? extends Point> shape) {
        this.uuid = uuid;
        this.key = key;
        this.face = face;
        this.shape = shape;
        this.animationState = AnimationState.IDLE;
    }

    /**
     * Creates a new instance from the door class with the given values.
     * @param key the {@link Key} for the door
     * @param face the face of the door
     * @param shape the shape from the door
     */
    BaseDoor(@NotNull Key key, @NotNull DoorFace face,  @NotNull Shape<? extends Point> shape) {
        this(UUID.randomUUID(), key, face, shape);
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BaseDoor baseDoor)) return false;
        return Objects.equals(uuid, baseDoor.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public @NotNull UUID id() {
        return this.uuid;
    }

    @Override
    public @NotNull Key key() {
        return this.key;
    }

    @Override
    public @NotNull DoorFace face() {
        return this.face;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public @NotNull Shape<? extends Point> shape() {
        return this.shape;
    }
}
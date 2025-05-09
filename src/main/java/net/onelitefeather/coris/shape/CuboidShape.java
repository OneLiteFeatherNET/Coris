package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Point;
import net.minestom.server.utils.validate.Check;
import net.onelitefeather.coris.util.PositionsCalculator;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @param <T>
 */
@ApiStatus.Experimental
public class CuboidShape<T extends Point> extends BaseShape<T> {

    private final PositionsCalculator<T> positionsCalculator;

    public CuboidShape(@NotNull T start, @NotNull T end, @NotNull PositionsCalculator<T> positionsCalculator) {
        super(start, end);
        this.positionsCalculator = positionsCalculator;
        double distance = start.distanceSquared(end);

        if (distance <= 0) {
            throw new IllegalArgumentException("The distance between the start and end point must be greater than 0");
        }
    }

    @Override
    public void calculatePositions() {
        Check.argCondition(this.positions.isEmpty(), "The positions are already calculated");
        this.positions.addAll(this.positionsCalculator.calculatePositions(start, end));
    }

    @Override
    public @NotNull CompletableFuture<Set<T>> calculatePositionsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            calculatePositions();
            return this.positions;
        });
    }

    @Override
    public boolean intersect2D(@NotNull T position) {
        int x = position.blockX();
        int z = position.blockZ();
        return x >= start.blockX() && x <= end.blockX() && z >= start.blockZ() && z <= end.blockZ();
    }

    @Override
    public boolean intersect3D(@NotNull T position) {
        int x = position.blockX();
        int z = position.blockZ();
        int y = position.blockY();
        return x >= start.blockX()
                && x <= end.blockX()
                && y >= start.blockY()
                && y <= end.blockY()
                && z >= start.blockZ()
                && z <= end.blockZ();
    }
}

package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Point;
import net.minestom.server.utils.validate.Check;
import net.onelitefeather.coris.util.DefaultPositionCalculator;
import net.onelitefeather.coris.util.PositionsCalculator;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @param <T>
 */
@ApiStatus.Experimental
public class CuboidShape<T extends Point> extends BaseShape<T> {

    public CuboidShape(@NotNull T start, @NotNull T end) {
        super(start, end, DefaultPositionCalculator::getCuboidPoints);
        this.distanceCheck();
    }

    public CuboidShape(@NotNull T start, @NotNull T end, @NotNull PositionsCalculator<T> calculator) {
        super(start, end, calculator);
        this.distanceCheck();
    }

    private void distanceCheck() {
        double distance = start.distanceSquared(end);
        if (distance <= 0) {
            throw new IllegalArgumentException("The distance between the start and end point must be greater than 0");
        }
    }

    @Override
    public void calculatePositions() {
        Check.argCondition(this.positions.isEmpty(), "The positions are already calculated");
        this.positions.addAll(calculator.calculatePositions(List.of(start, end)));
    }

    @Override
    public @NotNull CompletableFuture<Void> calculatePositionsAsync() {
        return CompletableFuture.runAsync(this::calculatePositions);
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

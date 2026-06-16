    package net.onelitefeather.coris.shape;

    import net.minestom.server.coordinate.Point;
    import net.minestom.server.coordinate.Vec;
    import org.jetbrains.annotations.ApiStatus;

    /**
     * The {@link CuboidShape} class represents a 3D cuboid shape defined by two points start and end.
     *
     * @param start the starting point of the cuboid
     * @param end   the ending point of the cuboid
     * @author theEvilReaper
     * @version 1.4.0
     * @since 0.1.0
     */
    @ApiStatus.Experimental
    public record CuboidShape(Vec start, Vec end) implements Shape {

        /**
         * Creates a new cuboid shape with the specified start and end points.
         *
         * @param start the starting point of the cuboid
         * @param end   the ending point of the cuboid
         * @throws IllegalArgumentException if the distance between start and end is less than or equal to 0
         */
        public CuboidShape {
            double distance = start.distanceSquared(end);
            if (distance <= 0) {
                throw new IllegalArgumentException("The distance between the start and end point must be greater than 0");
            }

            // Normalize coordinates and assign back to the parameter variables
            Vec min = new Vec(
                    Math.min(start.x(), end.x()),
                    Math.min(start.y(), end.y()),
                    Math.min(start.z(), end.z())
            );
            Vec max = new Vec(
                    Math.max(start.x(), end.x()),
                    Math.max(start.y(), end.y()),
                    Math.max(start.z(), end.z())
            );

            start = min;
            end = max;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Point min() {
            return start;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Point max() {
            return end;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(Shape o) {
            if (!(o instanceof CuboidShape(Vec start1, Vec end1))) {
                return -1;
            }
            int cmpStart = compareVec(this.start, start1);
            if (cmpStart != 0) return cmpStart;
            return compareVec(this.end, end1);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean intersect2D(Point position) {
            return position.blockX() >= start.blockX() && position.blockX() <= end.blockX() &&
                    position.blockZ() >= start.blockZ() && position.blockZ() <= end.blockZ();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean intersect3D(Point position) {
            return position.blockX() >= start.blockX() && position.blockX() <= end.blockX() &&
                    position.blockY() >= start.blockY() && position.blockY() <= end.blockY() &&
                    position.blockZ() >= start.blockZ() && position.blockZ() <= end.blockZ();
        }

        /**
         * Compares two vectors lexicographically.
         *
         * @param first  the first vector to compare
         * @param second the second vector to compare
         * @return -1 if v1 is less than v2, 0 if they are equal, and 1 if v1 is greater than v2
         */
        private int compareVec(Vec first, Vec second) {
            int cmpX = Double.compare(first.x(), second.x());
            if (cmpX != 0) return cmpX;

            int cmpY = Double.compare(first.y(), second.y());
            if (cmpY != 0) return cmpY;

            return Double.compare(first.z(), second.z());
        }
    }

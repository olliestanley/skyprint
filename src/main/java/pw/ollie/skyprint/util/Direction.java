package pw.ollie.skyprint.util;

import pw.ollie.skyprint.exception.InvalidRotationException;

/**
 * An enum containing base cardinal directions
 */
public enum Direction {
    /**
     * Represents north
     */
    NORTH(1, 0),
    /**
     * Represents south
     */
    SOUTH(-1, 0),
    /**
     * Represents east
     */
    EAST(0, 1),
    /**
     * Represents west
     */
    WEST(0, -1),
    /**
     * Represents north-east
     */
    NORTHEAST(1, 1),
    /**
     * Represents south-east
     */
    SOUTHEAST(-1, 1),
    /**
     * Represents north-west
     */
    NORTHWEST(1, -1),
    /**
     * Represents south-west
     */
    SOUTHWEST(-1, -1);

    /**
     * The change in x required to travel one meter in this Direction
     */
    public final int x;
    /**
     * The change in z required to travel one meter in this Direction
     */
    public final int z;

    /**
     * Constructor
     * 
     * @param x Change in x required to travel a block in this direction
     * @param z Change in z required to travel a block in this direction
     */
    private Direction(final int x, final int z) {
        this.x = x;
        this.z = z;
    }

    /**
     * Gets a cardinal direction from the yaw value of the given location
     * 
     * @param loc The location to get the cardinal direction from
     * @return A cardinal direction from the yaw value of the given location
     * @throws InvalidRotationException If the location has an invalid yaw value
     */
    public static Direction fromYaw(final float yaw) throws InvalidRotationException {
        // convert to a 'real' yaw value
        double rot = Utilities.toRealYaw(yaw);

        // get the cardinal direction from the rotation
        if (0 <= rot && rot < 22.5) {
            return Direction.SOUTH;
        } else if (22.5 <= rot && rot < 67.5) {
            return Direction.SOUTHWEST;
        } else if (67.5 <= rot && rot < 112.5) {
            return Direction.WEST;
        } else if (112.5 <= rot && rot < 157.5) {
            return Direction.NORTHWEST;
        } else if (157.5 <= rot && rot < 202.5) {
            return Direction.NORTH;
        } else if (202.5 <= rot && rot < 247.5) {
            return Direction.NORTHEAST;
        } else if (247.5 <= rot && rot < 292.5) {
            return Direction.EAST;
        } else if (292.5 <= rot && rot < 337.5) {
            return Direction.SOUTHEAST;
        } else if (337.5 <= rot && rot < 360.0) {
            return Direction.SOUTH;
        } else {
            // rot value = > 360 or < 0, therefore invalid
            throw new InvalidRotationException("Invalid rotation: " + rot);
        }
    }
}

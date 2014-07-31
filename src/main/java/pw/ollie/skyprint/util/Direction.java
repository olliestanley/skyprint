package pw.ollie.skyprint.util;

import org.bukkit.Location;

import pw.ollie.skyprint.exception.InvalidRotationException;

/**
 * An enum containing all base cardinal directions
 */
public enum Direction {
	/**
	 * North
	 */
	NORTH,
	/**
	 * East
	 */
	EAST,
	/**
	 * South
	 */
	SOUTH,
	/**
	 * West
	 */
	WEST;

	/**
	 * Gets a cardinal direction from the yaw value of the given location
	 * 
	 * @param loc
	 *            The location to get the cardinal direction from
	 * @return A cardinal direction from the yaw value of the given location
	 * @throws InvalidRotationException
	 *             If the location has an invalid yaw value
	 */
	public static Direction fromLocation(final Location loc)
			throws InvalidRotationException {
		double rot = (loc.getYaw() - 90) % 360;
		if (rot < 0) {
			rot += 360.0;
		}

		if (0 <= rot && rot < 45) {
			return NORTH;
		} else if (45 <= rot && rot < 135) {
			return EAST;
		} else if (135 <= rot && rot < 225) {
			return SOUTH;
		} else if (225 <= rot && rot < 315) {
			return WEST;
		} else if (315 <= rot && rot < 360.0) {
			return NORTH;
		}

		throw new InvalidRotationException("Invalid rotation: " + rot);
	}
}

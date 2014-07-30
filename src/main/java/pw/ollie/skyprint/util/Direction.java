package pw.ollie.skyprint.util;

import org.bukkit.Location;

import pw.ollie.skyprint.exception.InvalidRotationException;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST;

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

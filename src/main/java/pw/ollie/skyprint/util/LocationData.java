package pw.ollie.skyprint.util;

import org.bukkit.Location;

public final class LocationData {
	public final int x, y, z;

	public LocationData(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public LocationData(final Location loc) {
		this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	public LocationData(final LocationData data) {
		this(data.x, data.y, data.z);
	}

	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}
}

package pw.ollie.skyprint.util;

import org.bukkit.Location;

/**
 * Contains an x, y and z value and can be constructed from a {@link Location}
 */
public final class LocationData {
    /**
     * The x co-ordinate for this location
     */
    public final int x;
    /**
     * The y co-ordinate for this location
     */
    public final int y;
    /**
     * The z-co-ordinate for this location
     */
    public final int z;

    /**
     * Constructs a LocationData from raw x, y and z values
     * 
     * @param x The x co-ordinate for this LocationData
     * @param y The y co-ordinate for this LocationData
     * @param z The z co-ordinate for this LocationData
     */
    public LocationData(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a LocationData from the values in a {@link Location}
     * 
     * @param loc The {@link Location} to get co-ordinates from
     */
    public LocationData(final Location loc) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    /**
     * Constructs a new LocationData with the same values as the given
     * LocationData
     * 
     * @param data The LocationData object to get co-ordinates from
     */
    public LocationData(final LocationData data) {
        this(data.x, data.y, data.z);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}

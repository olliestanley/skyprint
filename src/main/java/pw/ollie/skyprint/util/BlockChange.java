package pw.ollie.skyprint.util;

import java.util.UUID;

import org.bukkit.Material;

/**
 * Represents a change in the type of the block at a specific location
 */
public final class BlockChange {
    /**
     * The unique ID of the player who initiated the change
     */
    public final UUID player;
    /**
     * The location at which the change took place
     */
    public final LocationData loc;
    /**
     * The material type of the block before the change
     */
    public final Material before;
    /**
     * The material type of the block after the change
     */
    public final Material after;

    /**
     * Constructs a new BlockChange
     * 
     * @param player The unique ID of the player who caused the change
     * @param loc The location where the change took place
     * @param before The material type of the block before the change
     * @param after The material type of the block after the change
     */
    public BlockChange(final UUID player, final LocationData loc, final Material before, final Material after) {
        this.player = player;
        this.loc = loc;
        this.before = before;
        this.after = after;
    }
}

package pw.ollie.skyprint.util;

import java.util.UUID;

import org.bukkit.Material;

public final class BlockChange {
	public final UUID player;
	public final LocationData loc;
	public final Material before, after;

	public BlockChange(final UUID player, final LocationData loc,
			final Material before, final Material after) {
		this.player = player;
		this.loc = loc;
		this.before = before;
		this.after = after;
	}
}

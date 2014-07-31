package pw.ollie.skyprint.edit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import pw.ollie.skyprint.exception.UnsupportedCharacterException;
import pw.ollie.skyprint.util.BlockChange;
import pw.ollie.skyprint.util.Direction;
import pw.ollie.skyprint.util.LocationData;

/**
 * A class which allows for the writing out of character and therefore words in
 * the Minecraft sky
 */
public final class Edit {
	private final Plugin plugin;
	private final UUID player;
	private final LocationData start;
	private final Material material;
	private final Direction direction;
	private final String world;

	private final SkyCharacter[] characters;
	private final List<BlockChange> changed;

	public Edit(final Plugin plugin, final UUID player,
			final LocationData start, final Material material,
			final Direction direction, final String world,
			final char... characters) throws UnsupportedCharacterException {
		this.plugin = plugin;
		this.player = player;
		this.start = start;
		this.material = material;
		this.direction = direction;
		this.world = world;

		this.characters = new SkyCharacter[characters.length];
		for (int cur = 0; cur < characters.length; cur++) {
			this.characters[cur] = SkyCharacter.fromChar(characters[cur]);
			if (this.characters[cur] == null) {
				throw new UnsupportedCharacterException(
						String.valueOf(characters[cur]));
			}
		}

		changed = new ArrayList<BlockChange>();
	}

	/**
	 * Writes the characters this Editor was provided upon instantiation, in the
	 * provided material and direction, starting from the provided starting
	 * location. This is probably slow as fuck. Oh well
	 */
	public void write() {
		new BukkitRunnable() {
			@Override
			public void run() {
				final List<LocationData> list = new ArrayList<>();

				int modX = 0;
				final int modY = 0;
				int modZ = 0;
				for (final SkyCharacter sc : characters) {
					final LocationData cur = new LocationData(start.x + modX,
							start.y + modY, start.z + modZ);
					final int[][] raw = sc.getRaw();
					for (int y = 0; y < raw.length; y++) {
						final int[] row = raw[y];
						for (int x = 0; x < row.length; x++) {
							if (raw[y][x] == 1) {
								final int[] mods = calcMod(x);
								final int xMod = mods[0];
								final int zMod = mods[1];

								list.add(new LocationData(cur.x + xMod, cur.y
										- y, cur.z + zMod));
							}
						}
					}

					final int[] mods = calcMod(raw[0].length + 1);
					modX += mods[0];
					modZ += mods[1];
				}

				new BukkitRunnable() {
					@Override
					public void run() {
						final World w = Bukkit.getWorld(world);
						for (final LocationData loc : list) {
							final Block block = w.getBlockAt(loc.x, loc.y,
									loc.z);
							final Material before = block.getType();
							block.setType(material);
							changed.add(new BlockChange(player,
									new LocationData(loc), before, material));
						}
					}
				}.runTask(plugin);
			}
		}.runTaskAsynchronously(plugin);
	}

	/**
	 * Undoes the edit performed by this editor
	 */
	public void undo() {
		final World wrld = Bukkit.getWorld(world);
		for (final BlockChange change : changed) {
			final LocationData loc = change.loc;
			wrld.getBlockAt(loc.x, loc.y, loc.z).setType(change.before);
		}
	}

	public UUID getPlayerId() {
		return player;
	}

	private int[] calcMod(final int distance) {
		switch (direction) {
		case NORTH:
			return new int[] { 0, distance };
		case EAST:
			return new int[] { distance, 0 };
		case SOUTH:
			return new int[] { 0, -distance };
		case WEST:
			return new int[] { -distance, 0 };
		default:
			return null;
		}
	}
}

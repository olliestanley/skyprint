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
 * A class which allows for the writing out of characters and therefore words in
 * the Minecraft sky
 */
public final class Edit {
	/**
	 * The plugin this Edit is related to. Used for task scheduling
	 */
	private final Plugin plugin;
	/**
	 * The unique identifier of the player who initiated the Edit
	 */
	private final UUID player;
	/**
	 * The starting location for this Edit
	 */
	private final LocationData start;
	/**
	 * The material to write the character blocks in
	 */
	private final Material material;
	/**
	 * The direction to write the characters in
	 */
	private final Direction direction;
	/**
	 * The name of the world this Edit takes place in
	 */
	private final String world;
	/**
	 * An array of the characters to be written out in this Edit
	 */
	private final SkyCharacter[] characters;
	/**
	 * A List of block changes which this Edit caused
	 */
	private final List<BlockChange> changed;

	/**
	 * Constructs a new Edit with the given parameters
	 * 
	 * @param plugin
	 *            The plugin the Edit is related to, used for tasks
	 * @param player
	 *            The player who initiated the Edit
	 * @param start
	 *            The start location for the Edit
	 * @param material
	 *            The material to write characters in
	 * @param direction
	 *            The direction to write characters in
	 * @param world
	 *            The world the edit takes place in
	 * @param characters
	 *            All of the characters to be written out
	 * @throws UnsupportedCharacterException
	 *             If a given character isn't supported by SkyPrint
	 */
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
	 * location.
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

	/**
	 * Gets the unique identifier of the player who intiated the Edit
	 * 
	 * @return The unique identifier of the player who initiated the Edit
	 */
	public UUID getPlayerId() {
		return player;
	}

	/**
	 * Calculates the modifiers for x and z needed with this Edit's direction
	 * for the given distance value
	 * 
	 * @param distance
	 *            The distance to calculate modifiers for
	 * @return An int[] where the first index has the x modifier and the second
	 *         has the z modifier required over the given distance in the
	 *         direction for this Edit
	 */
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

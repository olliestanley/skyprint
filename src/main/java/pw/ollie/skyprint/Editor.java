package pw.ollie.skyprint;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import pw.ollie.skyprint.exception.UnsupportedCharacterException;
import pw.ollie.skyprint.util.Direction;
import pw.ollie.skyprint.util.LocationData;

/**
 * A class which allows for the writing out of character and therefore words in
 * the Minecraft sky
 * 
 * TODO: Support different directions + add Direction.fromVector
 */
public final class Editor {
	private final Plugin plugin;
	private final LocationData start;
	private final Material material;
	private final Direction direction;
	private final String world;

	private SkyCharacter[] characters;
	private boolean ready;

	public Editor(final Plugin plugin, final LocationData start,
			final Material material, final Direction direction,
			final String world, final char... characters)
			throws UnsupportedCharacterException {
		this.plugin = plugin;
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

				int modX = 0, modY = 0, modZ = 0;
				for (final SkyCharacter sc : characters) {
					final LocationData cur = new LocationData(start.x + modX,
							start.y + modY, start.z + modZ);
					final int[][] raw = sc.getRaw();
					for (int y = 0; y < raw.length; y++) {
						final int[] row = raw[y];
						for (int x = 0; x < row.length; x++) {
							if (raw[y][x] == 1) {
								// TODO: Compute the below based on direction
								final int xMod = x;
								final int zMod = 0;

								list.add(new LocationData(cur.x + xMod, cur.y
										- y, cur.z));
							}
						}
					}

					// TODO: Support different directions
					modX += raw[0].length + 1;
				}

				new BukkitRunnable() {
					@Override
					public void run() {
						final World w = Bukkit.getWorld(world);
						for (final LocationData loc : list) {
							w.getBlockAt(loc.x, loc.y, loc.z).setType(material);
						}
					}
				}.runTask(plugin);
			}
		}.runTaskAsynchronously(plugin);
	}
}

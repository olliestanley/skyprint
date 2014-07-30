package pw.ollie.skyprint;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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

	private SkyChar[] characters;
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

		this.characters = new SkyChar[characters.length];
		for (int cur = 0; cur < characters.length; cur++) {
			this.characters[cur] = SkyChar.fromChar(characters[cur]);
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
				for (final SkyChar sc : characters) {
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

	public static enum SkyChar {
		A(
				'A',
				new int[][] { new int[] { 0, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
		B(
				'B',
				new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 } }),
		C(
				'C',
				new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 0, 1, 1 } }),
		D(
				'D',
				new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 } }),
		E(
				'E',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
		F(
				'F',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 } }),
		G(
				'G',
				new int[][] { new int[] { 0, 1, 1, 1 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 1 } }),
		H(
				'H',
				new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
		I(
				'I',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 1, 1, 1 } }),
		J(
				'J',
				new int[][] { new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 } }),
		K(
				'K',
				new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
		L(
				'L',
				new int[][] { new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
		M(
				'M',
				new int[][] { new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 1, 0, 1, 1 }, new int[] { 1, 0, 1, 0, 1 }, new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1 } }),
		N(
				'N',
				new int[][] { new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 1, 0, 0, 1 }, new int[] { 1, 0, 1, 0, 1 }, new int[] { 1, 0, 0, 1, 1 }, new int[] { 1, 0, 0, 0, 1 } }),
		O(
				'O',
				new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
		P(
				'P',
				new int[][] { new int[] { 1, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 } }),
		Q(
				'Q',
				new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 1 } }),
		R(
				'R',
				new int[][] { new int[] { 1, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 1, 0, 0 }, new int[] { 1, 0, 1, 0 }, new int[] { 1, 0, 0, 1 } }),
		S(
				'S',
				new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 1 }, new int[] { 1, 1, 0 } }),
		T(
				'T',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 } }),
		U(
				'U',
				new int[][] { new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
		V(
				'V',
				new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 } }),
		W(
				'W',
				new int[][] { new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1 }, new int[] { 1, 0, 1, 0, 1 }, new int[] { 1, 1, 0, 1, 1 }, new int[] { 1, 1, 0, 1, 1 } }),
		X(
				'X',
				new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 } }),
		Y(
				'Y',
				new int[][] { new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 } }),
		Z(
				'Z',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
		SPACE(
				' ',
				new int[][] { new int[] { 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 0 } }),
		ZERO(
				'0',
				new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
		ONE(
				'1',
				new int[][] { new int[] { 1, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 1, 1, 1 } }),
		TWO(
				'2',
				new int[][] { new int[] { 0, 1, 1, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 1, 1, 1, 1 } }),
		THREE(
				'3',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 1, 1, 1 } }),
		FOUR(
				'4',
				new int[][] { new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 1, 0 }, new int[] { 1, 1, 1, 1 }, new int[] { 0, 0, 1, 0 } }),
		FIVE(
				'5',
				new int[][] { new int[] { 1, 1, 1, 1 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 1, 1, 0 }, new int[] { 0, 0, 0, 1 }, new int[] { 1, 1, 1, 1 } }),
		SIX(
				'6',
				new int[][] { new int[] { 0, 1, 1, 1 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
		SEVEN(
				'7',
				new int[][] { new int[] { 1, 1, 1, 1 }, new int[] { 0, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 1, 0, 0, 0 } }),
		EIGHT(
				'8',
				new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 0 } }),
		NINE(
				'9',
				new int[][] { new int[] { 0, 1, 1, 0 }, new int[] { 1, 0, 0, 1 }, new int[] { 0, 1, 1, 1 }, new int[] { 0, 0, 0, 1 }, new int[] { 0, 0, 0, 1 } }),
		FULLSTOP(
				'.',
				new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
		COMMA(
				',',
				new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
		EXCLAMATION(
				'!',
				new int[][] { new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
		QUESTION(
				'?',
				new int[][] { new int[] { 1, 1, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 } }),
		PLUS(
				'+',
				new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 } }),
		MINUS(
				'-',
				new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 } }),
		APOSTROPHE('\'', new int[][] { new int[] { 1 } }),
		POUND(
				'£',
				new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 1, 1 } }),
		SPEECH('"', new int[][] { new int[] { 1, 0, 1 } }),
		BRACKET_OPEN(
				'(',
				new int[][] { new int[] { 0, 1, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 1, 0, 0 }, new int[] { 0, 1, 0 } }),
		BRACKET_CLOSE(
				')',
				new int[][] { new int[] { 0, 1, 0 }, new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 0, 1 }, new int[] { 0, 1, 0 } }),
		UNDERSCORE(
				'_',
				new int[][] { new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 } }),
		EQUALS(
				'=',
				new int[][] { new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 0, 0 }, new int[] { 1, 1, 1 }, new int[] { 0, 0, 0 } }),
		SLASH(
				'/',
				new int[][] { new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 1, 0, 0, 0 } }),
		BACKSLASH(
				'\\',
				new int[][] { new int[] { 0, 0, 0, 0 }, new int[] { 1, 0, 0, 0 }, new int[] { 0, 1, 0, 0 }, new int[] { 0, 0, 1, 0 }, new int[] { 0, 0, 0, 1 } });

		private final int[][] binary;
		private final char character;

		SkyChar(char character, int[][] binary) {
			this.character = character;
			this.binary = binary;
		}

		public char getCharacter() {
			return character;
		}

		public int[][] getRaw() {
			return binary;
		}

		public static SkyChar fromChar(char character) {
			for (SkyChar sc : values()) {
				if (sc.getCharacter() == (character)) {
					return sc;
				}
			}
			return null;
		}
	}

	public static final class LocationData {
		private final int x, y, z;

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

		public String toString() {
			return x + " " + y + " " + z;
		}
	}

	public static enum Direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;

		public static Direction fromVector(final Vector vector) {
			
			return Direction.NORTH;
		}
	}

	public static final class UnsupportedCharacterException extends Exception {
		private static final long serialVersionUID = 436428331881957006L;

		public UnsupportedCharacterException() {
			super();
		}

		public UnsupportedCharacterException(String c, Throwable t) {
			super(c, t);
		}

		public UnsupportedCharacterException(String c) {
			super(c);
		}

		public UnsupportedCharacterException(Throwable t) {
			super(t);
		}
	}
}

package pw.ollie.skyprint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pw.ollie.skyprint.exception.InvalidRotationException;
import pw.ollie.skyprint.exception.UnsupportedCharacterException;
import pw.ollie.skyprint.util.Direction;
import pw.ollie.skyprint.util.LocationData;

public final class SkyPrint extends JavaPlugin implements CommandExecutor {
	private final List<Edit> edits = new ArrayList<Edit>();

	@Override
	public void onEnable() {
		getCommand("skywrite").setExecutor(this);
		getCommand("skyundo").setExecutor(this);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd,
			final String lbl, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("skywrite")) {
			if (!(sender.hasPermission("skyprint.use"))) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permission to do that!");
				return true;
			}

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You must be a player to do that!");
				return true;
			}

			if (args.length < 2) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "Usage: /skywrite <blocktype> <words to write>");
				return true;
			}

			final String blockType = args[0].toUpperCase();
			final String words;
			final StringBuilder builder = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				builder.append(args[i]).append(" ");
			}
			builder.setLength(builder.length() - 1);
			words = builder.toString().toUpperCase();

			final Material mat = Material.getMaterial(blockType);
			if (mat == null) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "That material doesn't exist!");
				return true;
			}

			final Player player = (Player) sender;
			final UUID id = player.getUniqueId();

			Direction dir = null;
			try {
				dir = Direction.fromLocation(player.getLocation());
			} catch (final InvalidRotationException e) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "Failed to generate characters!");
				return true;
			}

			Edit edit;
			try {
				edit = new Edit(this, id, new LocationData(
						player.getEyeLocation()), mat, dir, player.getWorld()
						.getName(), words.toCharArray());

				edits.add(0, edit);
			} catch (final UnsupportedCharacterException e) {
				sender.sendMessage(ChatColor.DARK_RED + "Character: "
						+ e.getMessage() + " isn't supported!");
				return true;
			}

			sender.sendMessage(ChatColor.GRAY + "Writing message...");

			try {
				edit.write();
			} catch (final Exception e) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "Error writing message!");
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("skyundo")) {
			if (!(sender.hasPermission("skyprint.use"))) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permission to do that!");
				return true;
			}

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You must be a player to do that!");
				return true;
			}

			final UUID id = ((Player) sender).getUniqueId();
			for (final Edit edit : edits) {
				if (edit.getPlayerId().equals(id)) {
					edit.undo();
					edits.remove(edit);
					sender.sendMessage(ChatColor.GRAY + "Undid last edit!");
					return true;
				}
			}

			sender.sendMessage(ChatColor.DARK_RED
					+ "You don't have an edit to undo!");
			return true;
		}

		return true;
	}
}

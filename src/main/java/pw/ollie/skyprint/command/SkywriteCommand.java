package pw.ollie.skyprint.command;

import java.util.UUID;

import pw.ollie.skyprint.SkyPrint;
import pw.ollie.skyprint.edit.Edit;
import pw.ollie.skyprint.edit.EditManager;
import pw.ollie.skyprint.exception.InvalidRotationException;
import pw.ollie.skyprint.exception.UnsupportedCharacterException;
import pw.ollie.skyprint.util.Direction;
import pw.ollie.skyprint.util.LocationData;
import pw.ollie.skyprint.util.Utilities;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

/**
 * Contains functionality for the SkyPrint /skywrite command, which allows the
 * 'writing' of characters using blocks in Minecraft
 */
public class SkywriteCommand implements CommandExecutor {
    /**
     * The SkyPrint plugin instance
     */
    private final SkyPrint plugin;
    /**
     * The {@link EditManager} instance for management of {@link Edit} objects
     */
    private final EditManager editMgr;

    /**
     * Constructs a new SkyundoCommand using the {@link EditManager} from the
     * given plugin
     * 
     * @param plugin The SkyPrint instance to use for editing
     * @param editMgr The SkyPrint {@link EditManager} instance
     */
    public SkywriteCommand(final SkyPrint plugin, final EditManager editMgr) {
        this.plugin = plugin;
        this.editMgr = editMgr;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
            final String label, final String[] args) {
        if (!sender.hasPermission("skyprint.use")) {
            sender.sendMessage(DARK_RED + "You don't have permission to do that!");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(DARK_RED + "You must be a player to do that!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(DARK_RED + "Usage: /skywrite <blocktype> <words to write>");
            return true;
        }

        final String words;
        final StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        builder.setLength(builder.length() - 1);
        words = builder.toString().toUpperCase();

        final String blockType = args[0].toUpperCase();
        final Material mat = Utilities.getMaterial(blockType);
        if (mat == null) {
            sender.sendMessage(DARK_RED + "That material doesn't exist!");
            return true;
        }

        final Player player = (Player) sender;
        final UUID id = player.getUniqueId();

        final Direction dir;
        try {
            dir = Direction.fromYaw(player.getLocation().getYaw());
        } catch (final InvalidRotationException e) {
            sender.sendMessage(DARK_RED + "Failed to generate characters!");
            return true;
        }

        try {
            final Edit e = Utilities.newEdit(plugin, player, mat, dir, words);
            editMgr.add(e);
            e.write();
            sender.sendMessage(GRAY + "Writing message...");
        } catch (final UnsupportedCharacterException e) {
            sender.sendMessage(DARK_RED + "Character: " + e.getMessage() + " isn't supported!");
        } catch (final Exception e) {
            e.printStackTrace();
            sender.sendMessage(DARK_RED + "Error writing message!");
        }

        return true;
    }
}

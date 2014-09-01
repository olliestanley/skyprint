package pw.ollie.skyprint.command;

import java.util.UUID;

import pw.ollie.skyprint.SkyPrint;
import pw.ollie.skyprint.edit.Edit;
import pw.ollie.skyprint.edit.EditManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Contains functionality for the SkyPrint /skyundo command, which allows the
 * undoing of block changes made using SkyPrint
 */
public class SkyundoCommand implements CommandExecutor {
    /**
     * The SkyPrint {@link EditManager} instance
     */
    private final EditManager editMgr;

    /**
     * Constructs a new SkyundoCommand using the {@link EditManager} from the
     * given plugin
     * 
     * @param editMgr The SkyPrint {@link EditManager} instance
     */
    public SkyundoCommand(final EditManager editMgr) {
        this.editMgr = editMgr;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
            final String label, final String[] args) {
        if (!sender.hasPermission("skyprint.use")) {
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
        for (final Edit edit : editMgr.getEdits()) {
            if (edit.getPlayerId().equals(id)) {
                edit.undo();
                editMgr.remove(edit);
                sender.sendMessage(ChatColor.GRAY + "Undid last edit!");
                return true;
            }
        }

        sender.sendMessage(ChatColor.DARK_RED
                + "You don't have an edit to undo!");
        return true;
    }
}

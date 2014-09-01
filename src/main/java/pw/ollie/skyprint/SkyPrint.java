package pw.ollie.skyprint;

import pw.ollie.skyprint.command.SkyundoCommand;
import pw.ollie.skyprint.command.SkywriteCommand;
import pw.ollie.skyprint.edit.Edit;
import pw.ollie.skyprint.edit.EditManager;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main plugin class for SkyPrint, a Bukkit plugin which allows words to be
 * written in blocks in any material the user wishes, just by the usage of one
 * simple command
 */
public final class SkyPrint extends JavaPlugin {
    @Override
    public void onEnable() {
        final EditManager editMgr = new EditManager();
        getCommand("skywrite").setExecutor(new SkywriteCommand(this, editMgr));
        getCommand("skyundo").setExecutor(new SkyundoCommand(editMgr));
    }
}

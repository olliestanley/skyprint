package pw.ollie.skyprint;

import org.bukkit.plugin.java.JavaPlugin;

import pw.ollie.skyprint.command.SkyundoCommand;
import pw.ollie.skyprint.command.SkywriteCommand;
import pw.ollie.skyprint.edit.EditManager;

/**
 * The main plugin class for SkyPrint, a Bukkit plugin which allows words to be
 * written in blocks in any material the user wishes, just by the usage of one
 * simple command
 */
public final class SkyPrint extends JavaPlugin {
	/**
	 * The {@link EditManager} instance, which deals with {@link Edit} objects
	 * in the plugin by storing them and providing some methods to deal with
	 * them
	 */
	private EditManager editMgr;

	@Override
	public void onEnable() {
		editMgr = new EditManager();

		// Register commands with Bukkit
		getCommand("skywrite").setExecutor(new SkywriteCommand(this));
		getCommand("skyundo").setExecutor(new SkyundoCommand(this));
	}

	/**
	 * Gets the plugin's {@link EditManager} instance, which manages
	 * {@link Edit} objects
	 * 
	 * @return The plugin's {@link EditManager} instance
	 */
	public EditManager getEditManager() {
		return editMgr;
	}
}

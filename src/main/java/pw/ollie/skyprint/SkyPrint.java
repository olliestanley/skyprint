package pw.ollie.skyprint;

import org.bukkit.plugin.java.JavaPlugin;

import pw.ollie.skyprint.command.SkyundoCommand;
import pw.ollie.skyprint.command.SkywriteCommand;
import pw.ollie.skyprint.edit.EditManager;

public final class SkyPrint extends JavaPlugin {
	private EditManager editMgr;

	@Override
	public void onEnable() {
		editMgr = new EditManager();

		getCommand("skywrite").setExecutor(new SkywriteCommand(this));
		getCommand("skyundo").setExecutor(new SkyundoCommand(this));
	}

	public EditManager getEditManager() {
		return editMgr;
	}
}

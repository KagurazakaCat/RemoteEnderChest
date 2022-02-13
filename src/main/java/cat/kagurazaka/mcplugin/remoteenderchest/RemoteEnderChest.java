package cat.kagurazaka.mcplugin.remoteenderchest;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RemoteEnderChest extends JavaPlugin {

	public static FileConfiguration pluginConfig;

	//Method processing the enabling.
	@Override
	public void onEnable() {

		//Get the config file. If the config file does not exist, then generate with the default config file.
		saveDefaultConfig();
		pluginConfig = getConfig();

		//Register for the CommandHandler
		if (Bukkit.getPluginCommand("rec") != null) {
			Objects.requireNonNull(Bukkit.getPluginCommand("rec")).setTabCompleter(new TabFullFiller());
			Bukkit.getPluginCommand("rec").setExecutor(new CommandHandler());
		} else {
			throw new IllegalArgumentException("This command is not activated! Plugin would die soon!");
		}

		//Show SERVER the hint that the plugin has been activated.
		this.getServer().getLogger().info(pluginConfig.getString("PluginPrefix", "§a§lRemoteEnderChest §7>> §e") + pluginConfig.getString("PluginEnableMessage","Plugin has been enabled successfully!"));
	}

	//Method processing the disabling
	@Override
	public void onDisable() {
		this.getServer().getLogger().info(pluginConfig.getString("PluginPrefix", "§a§lRemoteEnderChest §7>> §e") + pluginConfig.getString("PluginDisableMessage","Plugin has been disabled successfully!"));
	}
}

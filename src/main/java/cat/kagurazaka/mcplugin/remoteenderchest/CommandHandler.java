package cat.kagurazaka.mcplugin.remoteenderchest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cat.kagurazaka.mcplugin.remoteenderchest.RemoteEnderChest.pluginConfig;

public class CommandHandler implements CommandExecutor {
	//The method that process ALL commands
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		//Initialize the sender to player
		if(sender instanceof Player) {
			Player player = ((Player)sender);

			//Too much or less parameters
			if(args.length == 0 || args.length > 2) {
				return false;
			}

			//Open their's ender chest
			if(args[0].equals("open") && args.length == 1 && player.hasPermission("rec.openself")) {
				player.openInventory(player.getEnderChest());
				sender.sendMessage(pluginConfig.getString("PluginPrefix", "§a§lRemoteEnderChest §7>> §e") + pluginConfig.getString("OpenSuccessfully","§eCommand successfully executed!"));
				return true;
			}

			//Open others' ender chest
			if(args[0].equals("open") && args.length == 2 && player.hasPermission("rec.openother")) {
				Player playerToOpen =  Bukkit.getPlayer(args[1]);
				if(playerToOpen != null) {
					player.openInventory(playerToOpen.getEnderChest());
					sender.sendMessage(pluginConfig.getString("PluginPrefix", "§a§lRemoteEnderChest §7>> §e") + pluginConfig.getString("OpenSuccessfully","§eCommand successfully executed!"));
					return true;
				} else {

				}
 			}
		} else {
			sender.sendMessage(pluginConfig.getString("PluginPrefix", "§a§lRemoteEnderChest §7>> §e") + pluginConfig.getString("OpenUnsuccessfully","§cPlayer you wish to open is not available!"));
			return true;
		}

		//By default, show the usage of command
		return false;
	}
}

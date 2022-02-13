package cat.kagurazaka.mcplugin.remoteenderchest;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabFullFiller implements TabCompleter {

	//If it is console, then
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Server) {
			return null;
		}
		if(args.length == 1 && sender.hasPermission("rec.open")) {
			List<String> arguments = new ArrayList<>();
			arguments.add("open");
			return arguments;
		} else if(args.length == 2 && args[0].equals("open") && sender.hasPermission("rec.openself")) {
			List<String> arguments = new ArrayList<>();
			for (Player p : Bukkit.getOnlinePlayers()) {
				arguments.add(p.getName());
			}
			return arguments;
		}
		return null;
	}
}
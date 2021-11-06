package net.heavin.StaffEssentials.Commands.General.Warning;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.Managers.Methods;

public class Warning implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("warn")) {
				if (player.hasPermission("admincore.warn")) {
					if (args.length == 0) {
						player.sendMessage(Methods.color(Config.warnNoPlayerArg()));
					} else if (args.length == 1) {
						player.sendMessage(Methods.color(Config.warnNoReason()));
					} else {
						Player target = Bukkit.getPlayer(args[0]);
						for (int i = 1; i < args.length; i++) {
							player.sendMessage(args.length+" ");
							if (args[args.length-1].equalsIgnoreCase("false")) {
								
							} else if (args[args.length-1].equalsIgnoreCase("true")) {
								for (Player truepl : Bukkit.getOnlinePlayers()) {
									truepl.sendMessage(Methods.color("&c" + target.getName() + " has been warned!"));
								}
							}
	                }
					}
				}
			}
		} else {System.out.println("This command cannot be sent on the console");}
		return false;
	}
	
	

}

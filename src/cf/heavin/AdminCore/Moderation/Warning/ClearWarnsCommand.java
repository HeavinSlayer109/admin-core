package cf.heavin.AdminCore.Moderation.Warning;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Config;
import cf.heavin.AdminCore.Files.Warns;
import cf.heavin.AdminCore.Managers.Methods;

public class ClearWarnsCommand implements CommandExecutor{
	Warns warnConfig = AdminCore.getWarnsConfig();
	
	/**
	 * Clear target's warns
	 * @param target
	 */
	public void clearWarns(Player target) {
		Player player = target;
		if (warnConfig.getConfig().contains(target.getName())) {
			warnConfig.getConfig().set(target.getName(), null);
			warnConfig.save();
			player.sendMessage(Methods.color("&7Successfully cleared warns for &b" + target.getName() + ""));
		} else {
			player.sendMessage(Methods.color("&b" + target.getName() + "&c cannot be found on warning database"));
		}
	}
	/**
	 * Clear target's warn from number
	 * @param target
	 * @param number
	 */
	public void clearWarnNumber(Player target, String number) {
		if (warnConfig.getConfig().contains(target + "." + number)) {
			warnConfig.getConfig().set(target.getName() + "." + number, null);
			warnConfig.save();
		} else {
			target.sendMessage("Warn Number " + number + " for " + target + " is not found");
		}
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("clearwarns") || label.equalsIgnoreCase("cwarns") || label.equalsIgnoreCase("clearwarnings")) {
			if (sender.hasPermission("admincore.warns.clear") || sender.hasPermission("admincore.*")) {
				if (args.length == 0) {
					sender.sendMessage(Methods.color("&cMissing arguments: /clearwarns [target]"));
				} else {
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(Methods.color("&b" + args[0] + "&c is not online or there's a typo"));
						return true;
					}
					clearWarns(target);
					
				}
			} else sender.sendMessage(Methods.color(Config.noPermission()));
		}
		return false;
	}
	
}

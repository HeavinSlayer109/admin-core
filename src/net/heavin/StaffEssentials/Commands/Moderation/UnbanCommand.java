package net.heavin.StaffEssentials.Commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.DataManagers.DataManager;
import net.heavin.StaffEssentials.Managers.Methods;
 
public class UnbanCommand implements CommandExecutor {
	// IF YOU'RE WONDERING WHY I DIDNT REGISTER THIS, ITS BECAUSE ITS NOT SUPPOSED TO BE USED IN THE PLUGIN
	private DataManager data;
	public UnbanCommand(DataManager data) {
		this.data = data;
	}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("unban")) {
            if (args.length == 0) {
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "Please specify a player!"));
            } else {
            	String target = args[0].toString();
                String unbanner = "Console";
 
                if (sender instanceof Player) {
                    unbanner = sender.getName();
                }
                if (data.getConfig().getString("banned_players." + target) == null) {
                	sender.sendMessage(Methods.color(Config.pluginPrefix() + "This player isnt banned"));
                }
                data.getConfig().set("banned_players."+target+".banner", null);
                data.getConfig().set("banned_players."+target+".reason", null);
                data.getConfig().set("banned_players."+target, null);
                data.saveConfig();
                Bukkit.getServer().getConsoleSender().sendMessage(Methods.color("&c&l" + unbanner + " &ehas unbanned " + "" + args[0].toString()));
                
                for (Player onlineplr : Bukkit.getOnlinePlayers()) {
                	if (onlineplr.hasPermission("admincore.staff")) {
                onlineplr.sendMessage(Methods.color("&c&l" + unbanner + " &ehas unbanned " + "" + args[0].toString()));
                	}
            }
            }
        }
 
        return true;
    }
}
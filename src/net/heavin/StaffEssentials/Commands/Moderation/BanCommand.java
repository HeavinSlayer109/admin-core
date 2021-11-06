package net.heavin.StaffEssentials.Commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.DataManagers.DataManager;
import net.heavin.StaffEssentials.Managers.Methods;

 
public class BanCommand implements CommandExecutor {
	// IF YOU'RE WONDERING WHY I DIDNT REGISTER THIS, ITS BECAUSE ITS NOT SUPPOSED TO BE USED IN THE PLUGIN
	private DataManager data;
	public BanCommand(DataManager data) {
		this.data = data;
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
 
        if (label.equalsIgnoreCase("ban")) {
            if (args.length == 0) {
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "Please specify a player and reason!"));
            } else if (args.length == 1) {
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "&cPlease specify a player!"));
            } else {
            	Player target = Bukkit.getPlayer(args[0]);
            	@SuppressWarnings("deprecation")
                OfflinePlayer t = Bukkit.getServer().getOfflinePlayer(args[0]);
				
 
                if (target == null) {
                	StringBuilder x = new StringBuilder();
                	 
                    for (int i = 1; i < args.length; i++) {
                        x.append(args[i]+" ");
                    }
                	String banner = "Console";
                	 
                    if (sender instanceof Player) {
                        banner = sender.getName();
                    }
                    data.getConfig().set("banned_players."+t.getName()+".banner", banner);
                    data.getConfig().set("banned_players."+t.getName()+".reason", x.toString().trim());
                    data.saveConfig();

                }
 
                StringBuilder x = new StringBuilder();
 
                for (int i = 1; i < args.length; i++) {
                    x.append(args[i]+" ");
                }
 
                String banner = "Console";
 
                if (sender instanceof Player) {
                    banner = sender.getName();
                }
 
                target.kickPlayer(ChatColor.RED + "You have been banned from the server!\nBy: "+banner+"\nReason: "+x.toString().trim());
                data.getConfig().set("banned_players."+target.getName()+".banner", banner);
                data.getConfig().set("banned_players."+target.getName()+".reason", x.toString().trim());
                data.saveConfig();

 
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "&aSuccessfully banned: &e"+target.getName()));
            }
        }
 
        return true;
    }
}
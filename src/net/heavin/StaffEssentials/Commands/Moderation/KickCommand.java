package net.heavin.StaffEssentials.Commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.Managers.Methods;
 
public class KickCommand implements CommandExecutor {
 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
 
        if (label.equalsIgnoreCase("kick")) {
            if (args.length == 0) {
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "Please specify a player and reason!"));
            } else if (args.length == 1) {
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "Please specify a reason!"));
            } else {
                Player target = Bukkit.getPlayer(args[0]);
 
                if (target == null) {
                    sender.sendMessage(Methods.color(Config.pluginPrefix()+args[0]+" is not online!"));
                    return true;
                }
 
                StringBuilder x = new StringBuilder();
 
                for (int i = 1; i < args.length; i++) {
                    x.append(args[i]+" ");
                }
 
                String kicker = "Server";
 
                if (sender instanceof Player) {
                    kicker = sender.getName();
                }
 
                target.kickPlayer(ChatColor.RED+"You have been kicked from the server!\nBy: "+kicker+"\nReason: "+x.toString().trim());
                sender.sendMessage(Methods.color(Config.pluginPrefix() + "Successfully kicked: "+target.getName()));
            }
 
 
        }
 
        return true;
    }
}

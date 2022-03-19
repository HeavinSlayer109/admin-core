package cf.heavin.AdminCore.Moderation.Warning;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Config;
import cf.heavin.AdminCore.Files.Warns;
import cf.heavin.AdminCore.Managers.Methods;


public class WarnCommand implements CommandExecutor{
	Warns warnConfig = AdminCore.getWarnsConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("warn")) {
			if (sender.hasPermission("admincore.warn") || sender.hasPermission("admincore.*")) {
				if (args.length == 0) {
					sender.sendMessage(Methods.color(Config.warnNoPlayerArg()));
				} else if (args.length == 1) {
					sender.sendMessage(Methods.color(Config.warnNoReason()));
				} else {
					Player player = (Player)sender;
					String target = args[0];
					if (target == null) {
			            String notonline = Config.warnPlayerNotOnline();
			            notonline = notonline.replace("%reported%", args[0]);
			            sender.sendMessage(Methods.color(notonline));
			            return true;
					}
					if (args.length == 2 && args[1].equalsIgnoreCase("true")) {
						sender.sendMessage(Methods.color(Config.pluginPrefix() + "&cInvalid argument! /warn (player) (reason) (silent true:false)"));
						return true;
					} else if (args.length == 2 && args[1].equalsIgnoreCase("false")) {
						sender.sendMessage(Methods.color(Config.pluginPrefix() + "&cInvalid argument! /warn (player) (reason) (silent true:false)"));
						return true;
					} else if (args.length == 2) {
						sender.sendMessage(Methods.color(Config.pluginPrefix() + "&cInvalid argument! /warn (player) (reason) (silent true:false)"));
						return true;
					}
					
			          StringBuilder x = new StringBuilder();
			          for (int y = 1; y < args.length - 1; y++)
			            x.append(String.valueOf(args[y]) + " "); 
			          
			          String reason = x.toString().trim();
			          String msg = Config.warnMessage();
			          String msgSilent = Config.warnMessageSilent();
			          
			          int list;
			          if (warnConfig.getConfig().getConfigurationSection(target + ".") == null) {
			        	  list = 1;
			          } else list = warnConfig.getConfig().getConfigurationSection(target + ".").getKeys(false).size() + 1;
			          
			          
			          
			          
			          if (args[args.length - 1].equalsIgnoreCase("true")) { // If silent
			            for (Player ol : Bukkit.getOnlinePlayers()) {
			              if (ol.hasPermission("admincore.staff") || ol.hasPermission("admincore.*")) {
			            	  msgSilent = msgSilent.replace("%target%", target);
			            	  msgSilent = msgSilent.replace("%reason%", reason);
			            	  ol.sendMessage(Methods.color(msgSilent));
			              }
			            }
			            Methods.ding(player);
			            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			            LocalDateTime now = LocalDateTime.now(); // get current time
			            warnConfig.getConfig().set(target + "." + list + ".warner", sender.getName());
			            warnConfig.getConfig().set(target + "." + list + ".reason", reason);
			            warnConfig.getConfig().set(target + "." + list + ".silent", true);
			            warnConfig.getConfig().set(target + "." + list + ".time", dtf.format(now));
			            warnConfig.save();
			            
			          } else if (args[args.length - 1].equalsIgnoreCase("false")) { // If not silent
			            for (Player ol : Bukkit.getOnlinePlayers()) {
			            	msg = msg.replace("%target%", target);
			            	msg = msg.replace("%reason%", reason);
			            	ol.sendMessage(Methods.color(msg));
			            }
			            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			            LocalDateTime now = LocalDateTime.now();
			            warnConfig.getConfig().set(target + "." + list + ".warner", sender.getName());
			            warnConfig.getConfig().set(target + "." + list + ".reason", reason);
			            warnConfig.getConfig().set(target + "." + list + ".silent", false);
			            warnConfig.getConfig().set(target + "." + list + ".time", dtf.format(now));
			            warnConfig.save();
			          } else {
			            sender.sendMessage(Methods.color("&cInvalid argument! /warn (player) (reason) (silent true:false)"));
			          }
			        }
			}
		}
		return false;
	}

}

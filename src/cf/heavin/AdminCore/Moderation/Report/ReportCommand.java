package cf.heavin.AdminCore.Moderation.Report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Config;
import cf.heavin.AdminCore.Files.Reports;
import cf.heavin.AdminCore.Managers.Methods;

public class ReportCommand implements CommandExecutor{
	Reports reportConfig = AdminCore.getReportsConfig();
	HashMap<Player, Long> cooldown = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("report")) {
			if (sender.hasPermission("admincore.report") || sender.hasPermission("admincore.*")) {
				if (args.length == 0) {
					sender.sendMessage(Methods.color(Config.reportNoPlayerArg()));
				} else if (args.length == 1) {
					sender.sendMessage(Methods.color(Config.reportNoReason()));
				} else if (args.length >= 2) {
					
					if (cooldown.containsKey(player)) {
	        			if (cooldown.get(player) > System.currentTimeMillis()) {
	        				long timeleft = (cooldown.get(player) - System.currentTimeMillis()) / 1000 + 1;
	        				player.sendMessage(Methods.color("&cReporting is still on cooldown! " + timeleft + "s"));
	        				return true;
	        			}
	        		}
					int cd = Config.getConfig().getInt("report-cooldown");
	        		cooldown.put(player, System.currentTimeMillis() + (cd * 1000));
	        		
	        		
	    			
					String target = args[0];
					if (target == null) {
			            String notonline = Config.warnPlayerNotOnline();
			            notonline = notonline.replace("%reported%", args[0]);
			            sender.sendMessage(Methods.color(notonline));
			            return true;
					}
					
					
			          StringBuilder x = new StringBuilder();
			          for (int y = 1; y < args.length - 1; y++)
			            x.append(String.valueOf(args[y]) + " "); 
			          
			          String reason = x.toString().trim();
			          String msg = Config.reportSuccess();
			          
			          int list;
			          if (reportConfig.getConfig().getConfigurationSection(target + ".") == null) {
			        	  list = 1;
			          } else list = reportConfig.getConfig().getConfigurationSection(target + ".").getKeys(false).size() + 1;
			          
			          
			          for (Player ol : Bukkit.getOnlinePlayers()) {
			              if (ol.hasPermission("admincore.staff") || ol.hasPermission("admincore.*")) {
			            	  msg = msg.replace("%target%", target);
			            	  msg = msg.replace("%reason%", reason);
			            	  ol.sendMessage(Methods.color(msg));
			              }
			          }
			          
			            Methods.ding(player);
			            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			            LocalDateTime now = LocalDateTime.now(); // get current time
			            reportConfig.getConfig().set(target + "." + list + ".reporter", sender.getName());
			            reportConfig.getConfig().set(target + "." + list + ".reason", reason);
			            reportConfig.getConfig().set(target + "." + list + ".time", dtf.format(now));
			            reportConfig.save();
			        }
				}
			}
		return false;
	}
}

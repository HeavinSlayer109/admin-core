package net.heavin.StaffEssentials.Commands.General.Report;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.heavin.StaffEssentials.Commands.General.Report.ReportManager.ReportGUI;
import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.Managers.Methods;

public class Report implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("report")) {
				if (args.length == 0) {
					player.sendMessage(Methods.color(Config.reportNoPlayerArg()));
				} else if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
	                if (target == null) {
	                	String notonline = Config.reportPlayerNotOnline();
	                	notonline = notonline.replace("%reported%", args[0]);
	                    player.sendMessage(Methods.color(notonline));
	                    return true;
	                }
					new ReportGUI(player, target.getName());
				} else {
	                Player target = Bukkit.getPlayer(args[0]);
	                if (target == null) {
	                	String notonline = Config.reportPlayerNotOnline();
	                	notonline = notonline.replace("%reported%", args[0]);
	                    player.sendMessage(Methods.color(notonline));
	                    return true;
	                }
	                StringBuilder x = new StringBuilder();
	                for (int i = 1; i < args.length; i++) {
	                    x.append(args[i]+" ");
	                }
	                String sucessmessage = Config.reportSuccess();
	                String reason = x.toString().trim();
	                
	                sucessmessage = sucessmessage.replace("%reporter%", player.getName());
	                sucessmessage = sucessmessage.replace("%reported%", target.getName());
	                sucessmessage = sucessmessage.replace("%reason%", reason);
	                String recievemessage = Config.reportRecieveMessage();
	                
	                recievemessage = recievemessage.replace("%reporter%", player.getName());
	                recievemessage = recievemessage.replace("%reported%", target.getName());
	                recievemessage = recievemessage.replace("%reason%", reason);
	                
	                for (Player onlinestaff : Bukkit.getOnlinePlayers()) {
	                	if (onlinestaff.hasPermission("admincore.staff")) {
	                		
	                		onlinestaff.sendMessage(Methods.color(recievemessage));
	                	}
	                }
	                
	                player.sendMessage(Methods.color(sucessmessage));
				}
		} else {System.out.println("This command cannot be sent on the console");}
	}
		return false;
  }
}

package cf.heavin.AdminCore.Moderation.Report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Files.Reports;
import cf.heavin.AdminCore.Managers.Methods;

public class ReportsCommand implements CommandExecutor{
	Reports reportConfig = AdminCore.getReportsConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("reports")) {
			    if (player.hasPermission("admincore.reports") || player.hasPermission("admincore.*")) {
			    	if (args.length == 0) {
						player.sendMessage(Methods.color("&cPlease specify a player you want to warn"));
					} else {
						String target = args[0];
						if (!reportConfig.getConfig().contains(target)) {
							player.sendMessage(Methods.color("&b" + target + "&c has not been reported yet"));
						} else {
							int list = reportConfig.getConfig().getConfigurationSection(target + ".").getKeys(false).size() +1;
							player.sendMessage("");
							player.sendMessage(Methods.color("&7==========[&6Report History&7]=========="));
							for (int i = 1; i < list; i++) {
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // format which the LocalDateTime will use
							    LocalDateTime now = LocalDateTime.now(); // get current time
							    String dateStart = reportConfig.getConfig().getString(target + "." + i + ".time"); // what time the player has been warned
							    String dateStop = dtf.format(now); // time now
							    
							    // Custom date format
							    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							    Date d1 = null;
							    Date d2 = null;
							    try {
							        d1 = format.parse(dateStart);
							        d2 = format.parse(dateStop);
							    } catch (ParseException e) {
							        e.printStackTrace();
							    }
							    
							    long diff = d2.getTime() - d1.getTime();
							    long days = TimeUnit.MILLISECONDS.toDays(diff);
							    long remainingHoursInMillis = diff - TimeUnit.DAYS.toMillis(days);
							    long hours = TimeUnit.MILLISECONDS.toHours(remainingHoursInMillis);
							    long remainingMinutesInMillis = remainingHoursInMillis - TimeUnit.HOURS.toMillis(hours);
							    long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMinutesInMillis);
							    long remainingSecondsInMillis = remainingMinutesInMillis - TimeUnit.MINUTES.toMillis(minutes);
							    long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingSecondsInMillis);
								
								
								String reason = reportConfig.getConfig().getString(target + "." + i + ".reason");
								String reporter = reportConfig.getConfig().getString(target + "." + i + ".reporter");
								
								player.sendMessage(Methods.color("&b" + target + " &7was reported by &c" + reporter + " &7for &b" + reason +
										"&c " + days + "d " + hours + "h " + minutes + "m " + seconds + "s" + " ago."));
								
							}
						}
						
					}
			    }
			}
		}
		
		return false;
	}
}

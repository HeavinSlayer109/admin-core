package cf.heavin.AdminCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import cf.heavin.AdminCore.Files.Data;
import cf.heavin.AdminCore.Files.Mutes;
import cf.heavin.AdminCore.Files.Reports;
import cf.heavin.AdminCore.Files.Warns;
import cf.heavin.AdminCore.Managers.MainListeners;
import cf.heavin.AdminCore.Managers.Methods;
import cf.heavin.AdminCore.Managers.Moderation.ModerationListener;
import cf.heavin.AdminCore.Managers.Staff.SettingsListener;
import cf.heavin.AdminCore.Managers.Staff.StaffListListener;
import cf.heavin.AdminCore.Moderation.Report.ReportCommand;
import cf.heavin.AdminCore.Moderation.Report.ReportsCommand;
import cf.heavin.AdminCore.Moderation.Warning.ClearWarnsCommand;
import cf.heavin.AdminCore.Moderation.Warning.WarnCommand;
import cf.heavin.AdminCore.Moderation.Warning.WarningsCommand;


public class AdminCore extends JavaPlugin{
	
	private static Data data;
	private static Warns warns;
	private static Reports reports;
	private static Mutes mutes;
	
	public void onEnable() {
		data = new Data(this);
		warns = new Warns(this);
		reports = new Reports(this);
			
		
		new Config(this);
		startup();
	}
	
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player == null) {
				return;
			}
			if (Methods.playercheck.containsKey(player.getName())) {
				player.sendMessage(Methods.color("&cThe server has restarted so you have been forcefully removed from staff mode!"));
				Methods.disableStaff(player);
			}
		}
	}
	
	public static Data getDataConfig() {return data;}
	public static Warns getWarnsConfig() {return warns;}
	public static Reports getReportsConfig() {return reports;}
	public static Mutes getMutesConfig() {return mutes;}
	
	public void startup() {
		Bukkit.getConsoleSender().sendMessage(Methods.color("&7---------- &7[&6AdminCore&7] ----------"));
		Bukkit.getConsoleSender().sendMessage(Methods.color(""));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&b AdminCore has been successfully enabled"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&7          -This plugin is made by Heavin"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&&- &fWebsite&7: https://heavin.cf"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&&- &fGithub&7: https://heavin.cf/github"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&c"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&7---------------------------------------"));
		getCommand("admincore").setExecutor(new Commands());
		
		getCommand("warn").setExecutor(new WarnCommand());
		getCommand("warns").setExecutor(new WarningsCommand());
		getCommand("clearwarns").setExecutor(new ClearWarnsCommand());
		
		getCommand("report").setExecutor(new ReportCommand());
		getCommand("reports").setExecutor(new ReportsCommand());
		
		Bukkit.getPluginManager().registerEvents(new MainListeners(this), this);
		Bukkit.getPluginManager().registerEvents(new SettingsListener(), this);
		Bukkit.getPluginManager().registerEvents(new ModerationListener(), this);
		Bukkit.getPluginManager().registerEvents(new StaffListListener(), this);
		
	}
	
}

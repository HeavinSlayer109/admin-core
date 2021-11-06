package net.heavin.StaffEssentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.heavin.StaffEssentials.Commands.Commands;
import net.heavin.StaffEssentials.Commands.PlayerJoin;
import net.heavin.StaffEssentials.Commands.General.Report.Report;
import net.heavin.StaffEssentials.Commands.General.Report.ReportManager.ReportListener;
import net.heavin.StaffEssentials.Commands.General.Warning.Warning;
import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.DataManagers.DataManager;
import net.heavin.StaffEssentials.GUIs.StaffSpyGui;
import net.heavin.StaffEssentials.GUIs.Ban.BanGUI;
import net.heavin.StaffEssentials.Managers.GameListener;
import net.heavin.StaffEssentials.Managers.Methods;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class AdminCore extends JavaPlugin implements Listener {
	public DataManager data;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		new Config(this);
		this.data = new DataManager(this);
		Bukkit.getConsoleSender().sendMessage(Methods.color("&7---------- &7[&6AdminCore&7] ----------"));
		Bukkit.getConsoleSender().sendMessage(Methods.color(""));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&b AdminCore has been successfully enabled"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&7          -This plugin is made by Heavin"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&&- &fWebsite&7: https://www.heavin.cf"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&&- &fGithub&7: https://www.heavin.cf"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&c"));
		Bukkit.getConsoleSender().sendMessage(Methods.color("&7---------------------------------------"));
		getCommand("admincore").setExecutor(new Commands());
		getCommand("report").setExecutor(new Report());
		getCommand("warn").setExecutor(new Warning());
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(data), this);
	    Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
	    Bukkit.getPluginManager().registerEvents(new ReportListener(), this);
	    Bukkit.getPluginManager().registerEvents(new StaffSpyGui(), this);
	    Bukkit.getPluginManager().registerEvents(new BanGUI(this), this);
	}
	
	
	
	
	
	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player == null) {
				return;
			}
			if (Methods.playercheck.containsKey(player.getName())) {
				player.sendMessage(Methods.color(Config.pluginPrefix() + "&cThe server has restarted so you have been forcefully removed from staff mode!"));
				Methods.disableStaff(player);
			} else if (StaffSpyGui.cmdspycheck.containsKey(player)) {
				StaffSpyGui.cmdspycheck.remove(player);
			}
		}
		
	}
	
	
	public String getProgressBar(int current, int max, int totalBars, String symbol, String completedColor, String notCompletedColor){

        float percent = (float) current / max;

        int progressBars = (int) ((int) totalBars * percent);

        int leftOver = (totalBars - progressBars);

        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.translateAlternateColorCodes('&', completedColor));
        for (int i = 0; i < progressBars; i++) {
            sb.append(symbol);
        }
        sb.append(ChatColor.translateAlternateColorCodes('&', notCompletedColor));
        for (int i = 0; i < leftOver; i++) {
            sb.append(symbol);
        }
        return sb.toString();
    }
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		TextComponent reportmsg = new TextComponent(Methods.color("&aReport Bugs here &7: "));
		TextComponent report = new TextComponent(Methods.color("&7[&aClick Me&7]"));
		report.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/HeavinSlayer109/admin-core/issues"));
		report.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Report bugs!")));
		
		for (Player pc : Bukkit.getOnlinePlayers()) {
			if (pc.isOp() == true) {
				pc.sendMessage(Methods.color("&c&lAdmin-Core is currently in &e&lBETA-MODE"));
				pc.sendMessage(Methods.color("&eMost features will be working, and some features"));
				pc.sendMessage(Methods.color("&ewill have bugs, and some are still in development"));
				pc.sendMessage(Methods.color("&eIf you find bugs on the plugin, report it by clicking"));
				pc.sendMessage(Methods.color("&ethe report button below"));
				pc.sendMessage(Methods.color(""));
				int percent = 40 * 100 / 100; //The first 20 is my current amount, the first 100 is just to make it bigger, the second 100 is the maximum you can get, so 100 in this case
                double b = Math.round(percent * 10.0) / 10.0 ;
				pc.sendMessage(Methods.color("&aProgress Bar &7: &8[&r" + getProgressBar(40, 100, 40, "|", "&a", "&7") + "&8] &a" + b + "&a%"));
				pc.spigot().sendMessage(reportmsg, report);
				
			}
		}
	}
}


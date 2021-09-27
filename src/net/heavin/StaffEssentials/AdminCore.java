package net.heavin.StaffEssentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.heavin.StaffEssentials.Commands.Commands;
import net.heavin.StaffEssentials.Commands.PlayerJoin;
import net.heavin.StaffEssentials.Commands.Moderation.BanCommand;
import net.heavin.StaffEssentials.Commands.Moderation.KickCommand;
import net.heavin.StaffEssentials.Commands.Moderation.UnbanCommand;
import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.DataManagers.DataManager;
import net.heavin.StaffEssentials.GUIs.StaffSpyGui;
import net.heavin.StaffEssentials.GUIs.Ban.BanGUI;
import net.heavin.StaffEssentials.Managers.GameListener;
import net.heavin.StaffEssentials.Managers.Methods;

public class AdminCore extends JavaPlugin{
	public DataManager data;
	
	@Override
	public void onEnable() {

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
		getCommand("staff").setExecutor(new Commands());
		getCommand("kick").setExecutor(new KickCommand());
		getCommand("unban").setExecutor(new UnbanCommand(data));
		getCommand("ban").setExecutor(new BanCommand(data));
		getCommand("staff").setTabCompleter(new Commands());
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(data), this);
	    Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
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
				Methods.disableStaff(player);
			} else if (StaffSpyGui.cmdspycheck.containsKey(player)) {
				StaffSpyGui.cmdspycheck.remove(player);
			}
		}
		
	}
}


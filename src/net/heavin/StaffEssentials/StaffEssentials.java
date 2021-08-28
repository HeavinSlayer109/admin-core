package net.heavin.StaffEssentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.heavin.StaffEssentials.Commands.Commands;
import net.heavin.StaffEssentials.GUIs.Ban.BanGUI;
import net.heavin.StaffEssentials.Managers.GameListener;
import net.heavin.StaffEssentials.Managers.Methods;

public class StaffEssentials extends JavaPlugin{
	
	@Override
	public void onEnable() {
		new Config(this);
		System.out.println(ChatColor.RED + "Staff Essentials has successfully started!");
		getCommand("staff").setExecutor(new Commands());
		getCommand("staff").setTabCompleter(new Commands());
	    Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
	    Bukkit.getPluginManager().registerEvents(new BanGUI(this), this);
	}
	
	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Methods.playercheck.containsKey(player.getName())) {
				Methods.disableStaff(player);
			}
		}
   }
}


package net.heavin.StaffEssentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.heavin.StaffEssentials.DataManagers.DataManager;
 
public class PlayerJoin implements Listener {
	
	private DataManager data;
	public PlayerJoin(DataManager data) {
		this.data = data;
	}
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
 
        if (data.getConfig().getConfigurationSection("banned_players") != null) {
            for (String section : data.getConfig().getConfigurationSection("banned_players").getKeys(false)) {
                if (section.equals(player.getName())) {
                    player.kickPlayer(ChatColor.RED + "You are banned from the server!\nBy: " + data.getConfig().getString("banned_players." + player.getName() + ".banner") + "\nReason: " + data.getConfig().getString("banned_players." + player.getName() + ".reason"));
                }
            }
        }
    }
 
}
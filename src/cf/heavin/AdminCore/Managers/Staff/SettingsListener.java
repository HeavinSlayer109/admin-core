package cf.heavin.AdminCore.Managers.Staff;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Files.Data;
import cf.heavin.AdminCore.Managers.Methods;

public class SettingsListener implements Listener{ 
	Data playerConfig = AdminCore.getDataConfig();
	HashMap<Player, String> lastMsg = new HashMap<>();
	
	/**
	 * Gets players staff chat settings from the config
	 * 
	 * @param Player
	 * @return StaffChat true/false
	 */
	public boolean staffChat(Player player) {
		return playerConfig.getConfig().getBoolean("settings." + player.getName() + ".staff-chat");
	}
	public boolean vanishOnJoin(Player player) {
		return playerConfig.getConfig().getBoolean("settings." + player.getName() + ".vanish-on-join");
	}
	
	@EventHandler
	public void staffChatListener(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (staffChat(player) == true) {
			if(e.getMessage().charAt(0) == '@') {
				e.setCancelled(true);
				for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
					if (onlineStaff.hasPermission("admincore.staffchat")) {
						onlineStaff.sendMessage(Methods.color("&7[&6StaffChat&7] " + e.getPlayer().getDisplayName() + ":&2 " + e.getMessage().substring(1)));
					}}}}
	}
	
	
	@EventHandler
	public void onVanishListener(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (vanishOnJoin(player) == true && Methods.lov.contains(player.getUniqueId())) {
			Methods.enableVanish(player);
		}
	}

}

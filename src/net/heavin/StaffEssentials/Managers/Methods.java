package net.heavin.StaffEssentials.Managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.heavin.StaffEssentials.Config;

public class Methods {
	
	public static HashMap<String, Player> playercheck = new HashMap<String, Player>();
	public static HashMap<String, Player> vanishCheck = new HashMap<String, Player>();
	public static HashMap<String, Player> nvisionCheck = new HashMap<String, Player>();
	public static HashMap<String, Player> flightCheck = new HashMap<String, Player>();

	
	public static void enableStaff(Player player) {
		if (player.hasPermission("se.staff")) {
			if (playercheck.containsKey(player.getName())) { 
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.staffAlreadyEnabled()));
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.staffEnabled()));
				InventoryManager.saveInventory(player);
				playercheck.put(player.getName(), player);
				}
		}
		
	}
	@SuppressWarnings("deprecation")
	public static void disableStaff(Player player) {
		if (player.hasPermission("se.staff")) {
			if (playercheck.containsKey(player.getName())) {
				if(!playercheck.containsKey(player.getName())) {player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.staffAlreadyDisabled()));}
				if (vanishCheck.containsKey(player.getName())) { vanishCheck.remove(player.getName()); player.showPlayer(player); }
				if (nvisionCheck.containsKey(player.getName())) { nvisionCheck.remove(player.getName()); player.removePotionEffect(PotionEffectType.NIGHT_VISION); }
				if (player.getGameMode() == GameMode.SURVIVAL) { if(flightCheck.containsKey(player.getName())) { if(flightCheck.containsKey(player.getName())) { flightCheck.remove(player.getName()); player.setAllowFlight(false); player.setFlying(false);}}}
				playercheck.remove(player.getName(), player);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.staffDisabled()));
				InventoryManager.restoreInventory(player);
			} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode())); }
        } else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.noPermission())); }
	}
	@SuppressWarnings("deprecation")
	public static void enableVanish(Player player) {
		if (player.hasPermission("se.vanish")) {
			if (playercheck.containsKey(player.getName())) {
				if (vanishCheck.containsKey(player.getName())) {
				return;
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.vanishEnabledMessage()));
				for (Player onlineplayers : Bukkit.getOnlinePlayers()) { onlineplayers.hidePlayer(player); }
				vanishCheck.put(player.getName(), player); 
				}
				} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode()));}
		}
		
		//if (player.hasPermission("se.vanish")) {
		//	if (!vanishCheck.containsValue(player)) {
        //		player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.vanishEnabledMessage()));
        //		for(Player onlineplayers : Bukkit.getOnlinePlayers()) {
        //                onlineplayers.hidePlayer(player);}
        //		
		//	} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are already in vanish mode")); }
//
        //	} else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.notInStaffMode()));}
            	
	} 
	@SuppressWarnings("deprecation")
	public static void disableVanish(Player player) {
		if (player.hasPermission("se.vanish")) {
			if (playercheck.containsKey(player.getName())) {
				if (vanishCheck.containsKey(player.getName())) {
					vanishCheck.remove(player.getName(), player);
					for (Player onlineplayers : Bukkit.getOnlinePlayers()) {
						onlineplayers.showPlayer(player);
					}
			         player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.vanishDisabledMessage()));
			} else { return; }
        } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode()));}
	} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.noPermission())); }
}
		//if (player.hasPermission("se.vanish")) {
			//if (vanishCheck.containsValue(player)) {
				//if (playercheck.containsKey(player.getName())) {
	        		//player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.vanishEnabledMessage()));
	        		//vanishCheck.remove(player.getName());
	        		//for(Player onlineplayers : Bukkit.getOnlinePlayers()) {
	                  //      onlineplayers.hidePlayer(player); } //deprecated I know 		
			//} else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.notInStaffMode()));}
//        	}     	
 //      }
	
	public static void enableNightVision(Player player) {
		if (player.hasPermission("se.nightvision")) {
			if(playercheck.containsKey(player.getName())) {
				if (nvisionCheck.containsKey(player.getName())) {
					return;
				} else {
					player.addPotionEffect(new PotionEffect (PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true));
					nvisionCheck.put(player.getName(), player);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.nvisionEnabled()));
				}
				
			} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode())); }
		} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.noPermission())); }
	}

	public static void disableNightVision(Player player) {
		if (player.hasPermission("se.vanish")) {
			if (playercheck.containsKey(player.getName())) {
				if (nvisionCheck.containsKey(player.getName())) {
					nvisionCheck.remove(player.getName(), player);
					player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.nvisionDisabled()));
			} else { return; }
        } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode()));}
	} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.noPermission())); }
}
	public static void enableFlight(Player player) {
		if (player.hasPermission("se.fly")) {
			if (playercheck.containsKey(player.getName())) {
				if (flightCheck.containsKey(player.getName())) {
					return;
				} else {
					flightCheck.put(player.getName(), player);
					player.setAllowFlight(true);
           	        player.setFlying(true);
           	        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.flightEnabled()));
			}
		} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode())); }

	} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.noPermission())); }
 }
	
	public static void disableFlight(Player player) {
		if (player.hasPermission("se.fly")) {
			if (playercheck.containsKey(player.getName())) {
				if (flightCheck.containsKey(player.getName())) {
					flightCheck.remove(player.getName(), player);
					player.setAllowFlight(false);
           	        player.setFlying(false);
			        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.flightDisabled()));
			} else { player.sendMessage("debug line 151 methods.java");}
        } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.pluginPrefix() + Config.notInStaffMode()));}
	} else { player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.noPermission())); }
		
	}
}

package net.heavin.StaffEssentials.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

import net.heavin.StaffEssentials.AdminCore;
import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.GUIs.AdminGUI;
import net.heavin.StaffEssentials.GUIs.PlayerList;
import net.heavin.StaffEssentials.GUIs.StaffGUI;

public class GameListener implements Listener{
	
	
	@SuppressWarnings("unused")
	private AdminCore plugin;
	public GameListener(AdminCore plugin) {
		this.plugin = plugin; }
    
	private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

	

	/*
	 * STAFF GUI
	 * 
	 */
	@EventHandler
	public void staffGUIListener(InventoryClickEvent e) {
		Player player = (Player)e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Staff GUI") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			    	if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle StaffMode On"))) {
			    		if (e.getClick() == ClickType.MIDDLE) {return;}
			    		Methods.enableStaff(player);
			    		new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle StaffMode Off")) ) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableStaff(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle Vanish On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableVanish(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle Vanish Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableVanish(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle Night-Vision On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableNightVision(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle Night-Vision Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableNightVision(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle Flight On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableFlight(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle Flight Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableFlight(player);
		            	new StaffGUI(player);
		            } 
		}
	}

	
	/*
	 * ADMIN GUI
	 * 
	 */
	
	@EventHandler
	public void adminGUIListener(InventoryClickEvent e) {
		Player player = (Player)e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Admin GUI") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			    	if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle StaffMode On"))) {
			    		if (e.getClick() == ClickType.MIDDLE) {return;}
			    		Methods.enableStaff(player);
			    		new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle StaffMode Off")) ) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableStaff(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle Vanish On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableVanish(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle Vanish Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableVanish(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle Night-Vision On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableNightVision(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle Night-Vision Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableNightVision(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aToggle Flight On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableFlight(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cToggle Flight Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableFlight(player);
		            	new AdminGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCurrent Online Player List"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	new PlayerList(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cBan Gui"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	player.sendMessage(Methods.color(Config.pluginPrefix() + "&c&lThis feature is only available in the future"));
		            }
		}
	}
	
	/*
	 * ONLINE PLAYER GUI LISTENER
	 * 
	 */

	@EventHandler
	public void onlinePlayers(InventoryClickEvent e) {
		Player player = (Player)e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Online Players") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			if (e.getClick() == ClickType.MIDDLE) {return;}
			e.setCancelled(true);
			player.sendMessage("debug");
			Player targetplr = Bukkit.getPlayer(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
			Location targetloc = targetplr.getLocation();
			
			String tpmsg = ChatColor.translateAlternateColorCodes('&', Config.teleportedToPlayer());
			tpmsg = tpmsg.replaceAll("%target%", e.getCurrentItem().getItemMeta().getDisplayName());
			player.sendMessage(tpmsg);
            player.teleport(targetloc);
		}
	}
	
	@EventHandler
	public void staffOnLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (player.hasPermission("se.staff")) { Methods.disableStaff(player); }
		if (isVanished(player) == true) {
			for (Player playercheck : Bukkit.getOnlinePlayers()) {
			if (playercheck.hasPermission("se.staff")) {
				if (Methods.playercheck.containsValue(player)) {
					Methods.disableVanish(player);
					Methods.disableStaff(player);
					
					
					
				String staffleavemessage= ChatColor.translateAlternateColorCodes('&', Config.vanishedStaffLeaveMsg());
				
				e.setQuitMessage(staffleavemessage);
				playercheck.sendMessage(staffleavemessage);
				}
			}
		}
	  }
	}

}

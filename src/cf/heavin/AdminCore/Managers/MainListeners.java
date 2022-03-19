package cf.heavin.AdminCore.Managers;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Config;
import cf.heavin.AdminCore.Files.Data;
import cf.heavin.AdminCore.GraphicalUserInterface.Playerlist;
import cf.heavin.AdminCore.GraphicalUserInterface.Settings;
import cf.heavin.AdminCore.GraphicalUserInterface.Admin.AdminGUI;
import cf.heavin.AdminCore.GraphicalUserInterface.Moderation.Moderation;
import cf.heavin.AdminCore.GraphicalUserInterface.Staff.StaffGUI;


public class MainListeners implements Listener{
	
	
	@SuppressWarnings("unused")
	private AdminCore plugin;
	public MainListeners(AdminCore plugin) {
		this.plugin = plugin; }
	

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
			    	if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&aToggle StaffMode On"))) {
			    		if (e.getClick() == ClickType.MIDDLE) {return;}
			    		Methods.enableStaff(player);
			    		new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&cToggle StaffMode Off")) ) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableStaff(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&aToggle Vanish On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableVanish(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&cToggle Vanish Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableVanish(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&aToggle Night-Vision On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableNightVision(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&cToggle Night-Vision Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableNightVision(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&aToggle Flight On"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.enableFlight(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&cToggle Flight Off"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	Methods.disableFlight(player);
		            	new StaffGUI(player);
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&eSettings"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	new Settings(player);
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
		            	if (player.hasPermission("admincore.playerlist")) {
		            		new Playerlist(player);
		            	} else {player.sendMessage(Methods.color(Config.pluginPrefix() + Config.noPermission()));}
		            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cBan Gui"))) {
		            	if (e.getClick() == ClickType.MIDDLE) {return;}
		            	player.sendMessage(Methods.color(Config.pluginPrefix() + "&c&lThis feature is only available in the future"));
		            }
		}
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Staff Settings") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			Data playerConfig = AdminCore.getDataConfig();
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&cToggle StaffChat"))) {
	    		if (e.getClick() == ClickType.MIDDLE) {return;}
	    			if (Settings.staffChat.get(player) == true && playerConfig.getConfig().getBoolean("settings." + player.getName() + ".staff-chat") == true) {
	    				Settings.staffChat.remove(player);
	    				Settings.staffChat.put(player, false);
		    			player.sendMessage("on click staffchat " + Settings.staffChat.get(player));
		    			playerConfig.getConfig().set("settings." + player.getName() + ".staff-chat", false);
	    				playerConfig.save();
		    			} else {
		    				Settings.staffChat.remove(player);
		    				Settings.staffChat.put(player, true);
		    				player.sendMessage("on click staffchat " + Settings.staffChat.get(player));
		    				playerConfig.getConfig().set("settings." + player.getName() + ".staff-chat", true);
		    				playerConfig.save();
		    			}
	    		new Settings(player);
	    	} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Methods.color("&cToggle Vanish On Join"))) {
	    		if (e.getClick() == ClickType.MIDDLE) {return;}
    			if (Settings.voj.get(player) == true && playerConfig.getConfig().getBoolean("settings." + player.getName() + ".vanish-on-join") == true) {
    				Settings.voj.remove(player);
    				Settings.voj.put(player, false);
	    			playerConfig.getConfig().set("settings." + player.getName() + ".vanish-on-join", false);
    				playerConfig.save();
	    			} else {
	    				Settings.voj.remove(player);
	    				Settings.voj.put(player, true);
	    				playerConfig.getConfig().set("settings." + player.getName() + ".vanish-on-join", true);
	    				playerConfig.save();
	    			}
    		new Settings(player);
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
			if (e.getClick() == ClickType.RIGHT) {
				e.setCancelled(true);
				Player targetplr = Bukkit.getPlayer(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
				Location targetloc = targetplr.getLocation();
				
				String tpmsg = ChatColor.translateAlternateColorCodes('&', Config.teleportedToPlayer());
				tpmsg = tpmsg.replaceAll("%target%", e.getCurrentItem().getItemMeta().getDisplayName());
				player.sendMessage(tpmsg);
	            player.teleport(targetloc);
			}
			if (e.getClick() == ClickType.LEFT) {
				new Moderation(player, ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
			}
			
		}
	}
	
	@EventHandler
	public void staffList(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Staff List") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			
		}
	}
	
	@EventHandler
	public void staffOnLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (Methods.playercheck.containsValue(player))
			Methods.disableStaff(player);
		Data playerConfig = AdminCore.getDataConfig();
		if (playerConfig.getConfig().getBoolean("settings." + player.getName() + ".vanish-on-join") == true) {
			e.setQuitMessage("");
			if (Methods.vanishCheck.containsKey(player.getName())) {
				Methods.leftOnVanish(player);
				for (Player ol : Bukkit.getOnlinePlayers()) {
					if (ol.hasPermission("admincore.staff")) {
						ol.sendMessage(Methods.color("&a" + player.getName() + "left in vanish"));
					}
				}
			} else return;
		} else return;
		
		//if (Methods.vanishCheck.containsKey(player.getName())) { // if player that left is vanished
		//	for (Player ol : Bukkit.getOnlinePlayers()) { // Loop thru all online players
		//		if (ol.hasPermission("admincore.staff") || ol.hasPermission("admincore.*")) { // if the current looped online player has the permissions
		//			if (playerConfig.getConfig().getBoolean("settings." + player.getName() + ".vanish-on-join") == false) {
		//				Methods.disableVanish(player);
		//			} else {return;}
		//			
		//			String staffleavemessage= Methods.color(Config.vanishedStaffLeaveMsg());
		//			ol.sendMessage(staffleavemessage);
		//		} else {e.setQuitMessage("");}
		//	}
		//	
		//	
		//}
	}
}

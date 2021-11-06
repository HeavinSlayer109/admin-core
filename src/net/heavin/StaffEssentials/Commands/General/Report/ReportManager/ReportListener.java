package net.heavin.StaffEssentials.Commands.General.Report.ReportManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.Managers.Methods;


public class ReportListener implements Listener {
	HashMap<Player, Long> cooldown = new HashMap<>();
	@EventHandler
	public void staffGUIListener(InventoryClickEvent e) {
		Player player = (Player)e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Report Reason") && e.getRawSlot() < 27 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			    	if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Hacking")) {
			    		if (e.getClick() == ClickType.MIDDLE) {return;}
			    		if (cooldown.containsKey(player)) {
	            			if (cooldown.get(player) > System.currentTimeMillis()) {
	            				long timeleft = (cooldown.get(player) - System.currentTimeMillis()) / 1000 + 1;
	            				
	            				player.sendMessage(Methods.color("&cYou reported already in the past minute! Wait for: " + timeleft + " seconds!"));
	            				return;
	            			}
	            		}
			    		cooldown.put(player, System.currentTimeMillis() + (60 * 1000));
			    		String recievemessage = Config.reportRecieveMessage();
		                String[] arr = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
		                
		                recievemessage = recievemessage.replace("%reporter%", player.getName());
		                recievemessage = recievemessage.replace("%reported%", arr[1]);
		                recievemessage = recievemessage.replace("%reason%", Methods.color(arr[3]));
			    		for (Player onlinestaff : Bukkit.getOnlinePlayers()) {
		                	if (onlinestaff.hasPermission("admincore.staff")) {
		                		onlinestaff.sendMessage(Methods.color(recievemessage));
		                	}
		                }
			    		String sucessmessage = Config.reportSuccess();
		                
		                sucessmessage = sucessmessage.replace("%reporter%", player.getName());
		                sucessmessage = sucessmessage.replace("%reported%", arr[1]);
		                sucessmessage = sucessmessage.replace("%reason%", Methods.color(arr[3]));
		                player.sendMessage(Methods.color(sucessmessage));
		                player.closeInventory();
			    	} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Exploiting")) {
			    		if (e.getClick() == ClickType.MIDDLE) {return;}
			    		if (cooldown.containsKey(player)) {
	            			if (cooldown.get(player) > System.currentTimeMillis()) {
	            				long timeleft = (cooldown.get(player) - System.currentTimeMillis()) / 1000 + 1;
	            				
	            				player.sendMessage(Methods.color("&cYou reported already in the past minute! Wait for: " + timeleft + " seconds!"));
	            				return;
	            			}
	            		}
			    		cooldown.put(player, System.currentTimeMillis() + (60 * 1000));
			    		String recievemessage = Config.reportRecieveMessage();
		                String[] arr = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
		                
		                recievemessage = recievemessage.replace("%reporter%", player.getName());
		                recievemessage = recievemessage.replace("%reported%", arr[1]);
		                recievemessage = recievemessage.replace("%reason%", Methods.color(arr[3]));
			    		for (Player onlinestaff : Bukkit.getOnlinePlayers()) {
		                	if (onlinestaff.hasPermission("admincore.staff")) {
		                		onlinestaff.sendMessage(Methods.color(recievemessage));
		                	}
		                }
			    		String sucessmessage = Config.reportSuccess();
		                
		                sucessmessage = sucessmessage.replace("%reporter%", player.getName());
		                sucessmessage = sucessmessage.replace("%reported%", arr[1]);
		                sucessmessage = sucessmessage.replace("%reason%", Methods.color(arr[3]));
		                player.sendMessage(Methods.color(sucessmessage));
		                player.closeInventory();
			    	} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Rule Breaking")) {
			    		if (e.getClick() == ClickType.MIDDLE) {return;}
			    		if (cooldown.containsKey(player)) {
	            			if (cooldown.get(player) > System.currentTimeMillis()) {
	            				long timeleft = (cooldown.get(player) - System.currentTimeMillis()) / 1000 + 1;
	            				
	            				player.sendMessage(Methods.color("&cYou reported already in the past minute! Wait for: " + timeleft + " seconds!"));
	            				return;
	            			}
	            		}
			    		cooldown.put(player, System.currentTimeMillis() + (60 * 1000));
			    		String recievemessage = Config.reportRecieveMessage();
		                String[] arr = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
		                
		                recievemessage = recievemessage.replace("%reporter%", player.getName());
		                recievemessage = recievemessage.replace("%reported%", arr[1]);
		                recievemessage = recievemessage.replace("%reason%", Methods.color("&cRule Breaking"));
			    		for (Player onlinestaff : Bukkit.getOnlinePlayers()) {
		                	if (onlinestaff.hasPermission("admincore.staff")) {
		                		onlinestaff.sendMessage(Methods.color(recievemessage));
		                	}
		                }
			    		String sucessmessage = Config.reportSuccess();
		                
		                sucessmessage = sucessmessage.replace("%reporter%", player.getName());
		                sucessmessage = sucessmessage.replace("%reported%", arr[1]);
		                sucessmessage = sucessmessage.replace("%reason%", Methods.color("&cRule Breaking"));
		                player.sendMessage(Methods.color(sucessmessage));
		                player.closeInventory();
			    	}
			}
	}

}

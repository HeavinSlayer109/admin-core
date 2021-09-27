package net.heavin.StaffEssentials.GUIs;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.heavin.StaffEssentials.AdminCore;
import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.Managers.Methods;

public class StaffSpyGui implements Listener {
	
	@SuppressWarnings("unused")
	private static AdminCore plugin;
	public StaffSpyGui(AdminCore plugin) {
		StaffSpyGui.plugin = plugin; }
	public StaffSpyGui() {}
	public static HashMap<Player, String> cmdspycheck = new HashMap<Player, String>();
	public static HashMap<Player, String> msgspycheck = new HashMap<Player, String>();
	public HashMap<String, String> msgtarget = new HashMap<String, String>();
	
	public StaffSpyGui(Player player) {
		Inventory z = Bukkit.getServer().createInventory(null, 27, ChatColor.BLACK + "Staff Social-Spy");
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = filler.getItemMeta();
		
		ItemStack a = new ItemStack(Material.STONE);
		ItemMeta am = a.getItemMeta();
		
		ItemStack b = new ItemStack(Material.PAPER);
		ItemMeta bm = b.getItemMeta();
		
		for (int i = 0; i <z.getSize(); i++) {
			if (z.getItem(i) == null) {
			fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			filler.setItemMeta(fillerMeta);
			z.setItem(i, filler);
			}
			}
		
		am.setDisplayName(Methods.color("&7Toggle Staff Social-Spy"));
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(Methods.color("&7This feature will turn on"));
		lore1.add(Methods.color("social-spy which will let you"));
		lore1.add(Methods.color("see other Staff's Private Messages to"));
		lore1.add(Methods.color("other players."));
		a.setItemMeta(am);
		z.setItem(11, a);
		
		bm.setDisplayName(Methods.color("&7Toggle Staff Command-Spy"));
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(Methods.color("&7This feature will turn on"));
		lore2.add(Methods.color("command-spy which will let you"));
		lore2.add(Methods.color("see commands ran by other staff members"));
		b.setItemMeta(bm);
		z.setItem(15, b);
		player.openInventory(z);
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equals(ChatColor.BLACK + "Staff Social-Spy") && e.getRawSlot() < 27 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true); 
			// Command Spy
			if (e.getCurrentItem().getType().equals(Material.PAPER) && e.getCurrentItem().getItemMeta().getDisplayName().contains("Toggle Staff Command-Spy")) {
				if (player.hasPermission("se.admin.socialspy")) {
					if (!cmdspycheck.containsKey(player)) {
						cmdspycheck.put(player, player.getName());
	                    player.sendMessage(ChatColor.GREEN + "You have enabled command spy");
	                } else if (cmdspycheck.containsKey(player)) {
	                    	cmdspycheck.remove(player);
	                        player.sendMessage(ChatColor.RED + "You have disabled commandspy");
	                }
	                }
				} else if (e.getCurrentItem().getType().equals(Material.STONE) && e.getCurrentItem().getItemMeta().getDisplayName().contains("Toggle Staff Social-Spy")) {
					if (player.hasPermission("se.admin.socialspy")) {
						if (!msgspycheck.containsKey(player)) {
							msgspycheck.put(player, player.getName());
		                    player.sendMessage(ChatColor.GREEN + "You have enabled message spy");
		                } else if (msgspycheck.containsKey(player)) {
		                    	msgspycheck.remove(player);
		                        player.sendMessage(ChatColor.RED + "You have disabled message spy");
		                }
		                }
				}
		}
	}

	
	@EventHandler
    public void commandSee(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String format = "&6%sender% &7has sent the command %command%";
        format = format.replace("%sender%", p.toString());
        format = format.replace("%command%", e.getMessage());
        for(Player ol : cmdspycheck.keySet()) {
        	if(ol.hasPermission("se.commandspy")) {
                if(e.getMessage().contains("/")) {
                    ol.sendMessage(Methods.color(Config.pluginPrefix() + format));
                }
            }
        }
    }
	
	@EventHandler
    public void onPrivateMessage(PlayerCommandPreprocessEvent e) {
		try {
        Player player = e.getPlayer();
        String message = e.getMessage();
        String format = "&7[&c%sender% &7-> &e%target%&7] %message%";
        String senderFinal = player.getName();
        
        
        String[] msgSplit = message.split(" ", 3);
        String[] targetSplit = message.split(" ", 3);
        String[] cmdSplit = message.split(" ", 3);
        
        
        String messageFinal = msgSplit[2];
        String targetFinal = targetSplit[1];
        String cmddetector = cmdSplit[0];
        
        format = format.replace("%sender%", senderFinal);
        format = format.replace("%target%", targetFinal);
        format = format.replace("%message%", messageFinal);
        
        //if block consisting of known "private messsage commands"
        /*(command.equals("/w") || command.equals("/m") || command.equals("/msg") || command.equals("/whisper") || command.equals("/r"))
         *\ /reply /
         */
        
        for(Player ol : msgspycheck.keySet()) {
        	if(ol.hasPermission("se.messagespy")) {
                	if (cmddetector.equalsIgnoreCase("/w")
                	 || cmddetector.equalsIgnoreCase("/whisper")
                     || cmddetector.equalsIgnoreCase("/pm") 
                	 || cmddetector.equalsIgnoreCase("/msg")
                	 || cmddetector.equalsIgnoreCase("/tell")) {
                		msgtarget.put(targetFinal, player.getName());
                		ol.sendMessage(Methods.color(Config.pluginPrefix() + format));
                	} 
            }
        }
        
        /*
         * REPLY
         */
        for(Player ol : msgspycheck.keySet()) {
        	if (ol.hasPermission("se.messagespy")) {
        		if (cmddetector.equalsIgnoreCase("/r")) {
        			targetFinal = msgtarget.get(targetFinal);
        			ol.sendMessage(Methods.color(Config.pluginPrefix() + format));
        		}
        	}
        }
        
        
        
    } catch(ArrayIndexOutOfBoundsException ignored) {
    	
    }
}
	
	@EventHandler
	public void onReplyMessage(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
        String message = e.getMessage();
        String format = Config.msgSpyFormat();
        String senderFinal = player.getName();
        
        
        String[] msgSplit = message.split(" ", 3);
        String[] targetSplit = message.split(" ", 3);
        String[] cmdSplit = message.split(" ", 3);
        
        
        String messageFinal = msgSplit[2];
        String targetFinal = targetSplit[1];
        String cmddetector = cmdSplit[0];
        
        
        format = format.replace("%sender%", senderFinal);
        format = format.replace("%target%", targetFinal);
        format = format.replace("%message%", messageFinal);
		
        
        for(Player ol : msgspycheck.keySet()) {
		if (cmddetector.equalsIgnoreCase("/reply")
    	 || cmddetector.equalsIgnoreCase("/r")) {
    		targetFinal = msgtarget.get(player.getName());
    		
    		ol.sendMessage(Methods.color(Config.pluginPrefix() + format));
		}
	  }
	}
}


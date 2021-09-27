package net.heavin.StaffEssentials.GUIs.Ban;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.heavin.StaffEssentials.AdminCore;
import net.heavin.StaffEssentials.Managers.Methods;

public class BanGUI implements Listener{
	
	public static HashMap<UUID, String> banreason = new HashMap<UUID, String>();
	public static HashMap<Player, Integer> timelength = new HashMap<Player, Integer>();
	public static HashMap<Player, String> target = new HashMap<Player, String>();
	
	public static boolean making = false;

	
	private static AdminCore plugin;
	public BanGUI(AdminCore plugin) {
		BanGUI.plugin = plugin; }
	public static ItemStack getHead(String textureURL) {
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);

        if (textureURL.isEmpty()) {
            return skullItem;
        }

        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", textureURL));
        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        skullItem.setItemMeta(skullMeta);
        return skullItem;
    }
	
	
	
	@SuppressWarnings("deprecation")
	public BanGUI(Player player) {
		Inventory gui = Bukkit.getServer().createInventory(null, 45, ChatColor.BLACK + "Ban GUI");
		ArrayList<Player> list = new ArrayList<>(player.getServer().getOnlinePlayers());
	    for (int i = 0; i < list.size(); i++) {
	      ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
	      SkullMeta meta = (SkullMeta) item.getItemMeta();
	      
	      meta.setOwner(list.get(i).getName());
	      meta.setDisplayName(ChatColor.stripColor(list.get(i).getDisplayName()));
	      ArrayList<String> lore = new ArrayList<>();
	      lore.add(ChatColor.GRAY + "Player Health: " + ChatColor.RED + list.get(i).getHealth());
	      lore.add(ChatColor.GRAY + "Player Exp: " + ChatColor.GOLD + list.get(i).getLevel());
	      lore.add("");
	      lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click this player to ban them"));
	      meta.setLore(lore);
	      item.setItemMeta(meta);
	      
	      gui.addItem(item);
	    }
	    player.openInventory(gui);
	}
	
	@SuppressWarnings("deprecation")
	public static void confirmMenu(Player player) {
        Inventory lgui = Bukkit.getServer().createInventory(null, 27, ChatColor.BLACK + "Confirm Ban");
		
		ItemStack Confirm = new ItemStack(Material.GREEN_WOOL);
		ItemMeta ConfirmMeta = Confirm.getItemMeta();
		ItemStack Deny = new ItemStack(Material.RED_WOOL);
		ItemMeta DenyMeta = Deny.getItemMeta();
		ItemStack Info = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta InfoMeta = (SkullMeta) Info.getItemMeta();
		
		
		ConfirmMeta.setDisplayName(Methods.color("&a&lConfirm Ban"));
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(Methods.color("&7Click to confirm the ban"));
		ConfirmMeta.setLore(lore1);
		Confirm.setItemMeta(ConfirmMeta);
		lgui.setItem(11, Confirm);
		
		
		DenyMeta.setDisplayName(Methods.color("&c&lCancel Ban"));
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(Methods.color("&7Click to cancel the ban"));
		DenyMeta.setLore(lore2);
		Deny.setItemMeta(DenyMeta);
		lgui.setItem(15, Deny);
		
		
		InfoMeta.setOwner(target.get(player));
		InfoMeta.setDisplayName(target.get(player));
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add(Methods.color("&7Target Name: &6" + target.get(player)));
		lore3.add(Methods.color("&7Time Length: &6" + timelength.get(player) + " day/s"));
		lore3.add(Methods.color("&7Ban reason: &6" + banreason.get(player.getUniqueId())));
		InfoMeta.setLore(lore3);
		Info.setItemMeta(InfoMeta);
		lgui.setItem(4, Info);
		
		player.openInventory(lgui);
		
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	@EventHandler
	public static void banGUI(InventoryClickEvent e) {
		
		
		// BAN REASON METHOD
		String player = e.getWhoClicked().toString();
		player = player.replace("CraftPlayer{name=", "");
		player = player.replace("}", "");
		if (e.getView().getTitle().contains("Give a ban reason for ")) {
			e.setCancelled(true);
			if (e.getCurrentItem().equals(null)) {return;}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Set a custom reason")) {
				String bantargetstring = ChatColor.stripColor(e.getView().getTitle());
				bantargetstring = bantargetstring.replace("Give a ban reason for ", "");
				making = true;
				ConversationFactory cf = new ConversationFactory(plugin);
				Conversation conv = cf.withFirstPrompt(new Responder()).withLocalEcho(false).buildConversation((Player) Bukkit.getPlayer(player));
				conv.begin();
				Bukkit.getPlayer(player).closeInventory();
			}
		}
		if (e.getView().getTitle().contains("eeee") && e.getRawSlot() < 27 && e.getCurrentItem().getItemMeta() !=null) {
			if (e.getCurrentItem().getType() == Material.GREEN_WOOL) {
				
			}
			if (e.getCurrentItem().getType() == Material.RED_WOOL) {
				Bukkit.getPlayer(player).closeInventory();
				Bukkit.getPlayer(player).sendMessage("ayyyyyyyy");
				banreason.remove(Bukkit.getPlayer(player).getUniqueId());
				target.remove(Bukkit.getPlayer(player));
			}
		}
		// PLAYER BANLIST
		if (e.getView().getTitle().contains("Ban GUI") && e.getRawSlot() < 45 && e.getCurrentItem().getItemMeta() !=null) {
			if (e.getCurrentItem().equals(null)) {return;}
			e.setCancelled(true);
			Player bantarget = Bukkit.getPlayer(player).getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
			String bantargetstring = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
			target.put(Bukkit.getPlayer(player), bantargetstring);
			/*
			 *GETS IF A PLAYER HEAD IS CLICKED
			 */
			if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
				e.setCancelled(true);
				//CREATES A GUI FOR THE PLAYER TARGET
				Inventory lgui = Bukkit.getServer().createInventory(null, 27, ChatColor.BLACK + "Currently banning " + bantargetstring);
				ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta fillerMeta = filler.getItemMeta();
			    ItemStack oneday = new ItemStack(Material.GREEN_WOOL);
			    ItemMeta onedaymeta = oneday.getItemMeta();
			    ItemStack fivedays = new ItemStack(Material.LIME_WOOL);
			    ItemMeta fivedaysmeta = fivedays.getItemMeta();
			    ItemStack tendays = new ItemStack(Material.YELLOW_WOOL);
			    ItemMeta tendaysmeta = tendays.getItemMeta();
			    ItemStack fifteendays = new ItemStack(Material.ORANGE_WOOL);
			    ItemMeta fifteendaysmeta = fifteendays.getItemMeta();
			    ItemStack twentydays = new ItemStack(Material.RED_WOOL);
			    ItemMeta twentydaysmeta = twentydays.getItemMeta();
			      
			    
			    for (int i = 0; i <lgui.getSize(); i++) {
					if (lgui.getItem(i) == null) {
					fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
					filler.setItemMeta(fillerMeta);
					lgui.setItem(i, filler);
					}
					}
			    
			    onedaymeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Ban &c" + bantargetstring + "&7 for 1 day"));
				oneday.setItemMeta(onedaymeta);
				lgui.setItem(9, oneday);
				  
				fivedaysmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Ban &c" + bantargetstring + "&7 for 5 days"));
				fivedays.setItemMeta(fivedaysmeta);
				lgui.setItem(11, fivedays);
			      
			    tendaysmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Ban &c" + bantargetstring + "&7 for 10 days"));
			    tendays.setItemMeta(tendaysmeta);
				lgui.setItem(13, tendays);
				  
				fifteendaysmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Ban &c" + bantargetstring + "&7 for 15 days"));
				fifteendays.setItemMeta(fifteendaysmeta);
				lgui.setItem(15, fifteendays);
				  
		        twentydaysmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Ban &c" + bantargetstring + "&7 for 20 days"));
				twentydays.setItemMeta(twentydaysmeta);
				lgui.setItem(17, twentydays);
				
				Bukkit.getPlayer(player).openInventory(lgui);
			    }
			
		}
		
		
		
		
		
		
		
		// BAN TIME FOR TARGET
		if (e.getView().getTitle().contains("Currently banning ") && e.getRawSlot() < 27 && e.getCurrentItem().getItemMeta() !=null) {
			if (e.getCurrentItem().equals(null)) {return;}
			e.setCancelled(true);
			String bantargetstring = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
			bantargetstring = bantargetstring.replace("Ban ", "");
			bantargetstring = bantargetstring.replace(" for 1 day", "");
			bantargetstring = bantargetstring.replace(" for 5 days", "");
			bantargetstring = bantargetstring.replace(" for 10 days", "");
			bantargetstring = bantargetstring.replace(" for 15 days", "");
			bantargetstring = bantargetstring.replace(" for 20 days", "");
			//if admin clicked green wool, returns 1 day
		if (e.getCurrentItem().getType() == Material.GREEN_WOOL) {
			e.setCancelled(true);
			
			
			if (timelength.containsKey(Bukkit.getPlayer(bantargetstring))) {
				timelength.replace(Bukkit.getPlayer(bantargetstring), 1);
			} else { timelength.put(Bukkit.getPlayer(bantargetstring), 1);}
			
			
			
			Inventory firstbangui = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Give a ban reason for " + bantargetstring);
			ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = filler.getItemMeta();
			
			ItemStack ph = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
			SkullMeta phmeta = (SkullMeta)ph.getItemMeta();
			
			ItemStack a = new ItemStack(Material.PAPER);
			ItemMeta ameta = a.getItemMeta();
			
			ItemStack b = new ItemStack(Material.OAK_SAPLING);
			ItemMeta bmeta = b.getItemMeta();
			
			ItemStack c = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
			ItemMeta cmeta = c.getItemMeta();
			
			for (int i = 0; i <firstbangui.getSize(); i++) {
				if (firstbangui.getItem(i) == null) {
				fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
				filler.setItemMeta(fillerMeta);
				firstbangui.setItem(i, filler);
				}
				}
			
			phmeta.setOwner(bantargetstring);
			phmeta.setDisplayName(Methods.color("&cBan " + bantargetstring + " for 1 day"));
			ph.setItemMeta(phmeta);
			firstbangui.setItem(4, ph);
			
			
			ameta.setDisplayName(Methods.color("&cSet a custom reason"));
			a.setItemMeta(ameta);
			firstbangui.setItem(31, a);
			
			bmeta.setDisplayName(Methods.color("&cHacking/Exploiting"));
			ArrayList<String> blore = new ArrayList<String>();
			blore.add(Methods.color("&7Set the ban reason as &cHacking"));
			blore.add(Methods.color("&7for &e" + bantargetstring));
			bmeta.setLore(blore);
			b.setItemMeta(bmeta);
			firstbangui.setItem(20, b);
			
			
			Bukkit.getPlayer(player).openInventory(firstbangui);
		}
		
		
		
		
		
		
		
		//if admin clicked lime wool, returns 5 days
		if (e.getCurrentItem().getType() == Material.LIME_WOOL) {
			e.setCancelled(true);
			Inventory secondbangui = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Give a ban reason for " + bantargetstring);
			ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = filler.getItemMeta();
			
			for (int i = 0; i <secondbangui.getSize(); i++) {
				if (secondbangui.getItem(i) == null) {
				fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
				filler.setItemMeta(fillerMeta);
				secondbangui.setItem(i, filler);
				}
				}
			Bukkit.getPlayer(player).openInventory(secondbangui);
		}
		
		
		
		
		
		
		
		
		//if admin clicked yellow wool, returns 10 days
		if (e.getCurrentItem().getType() == Material.YELLOW_WOOL) {
			e.setCancelled(true);
			Inventory thirdbangui = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Give a ban reason for " + bantargetstring);
			ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = filler.getItemMeta();

			
			for (int i = 0; i <thirdbangui.getSize(); i++) {
				if (thirdbangui.getItem(i) == null) {
				fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
				filler.setItemMeta(fillerMeta);
				thirdbangui.setItem(i, filler);
				}
				}
			Bukkit.getPlayer(player).openInventory(thirdbangui);
		}
		
		
		
		
		
		
		
		
		
		//if admin clicked orange wool, returns 15 days
		if (e.getCurrentItem().getType() == Material.ORANGE_WOOL) {
			e.setCancelled(true);
			Inventory fourthbangui = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Give a ban reason for " + bantargetstring);
			ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = filler.getItemMeta();

			
			for (int i = 0; i <fourthbangui.getSize(); i++) {
				if (fourthbangui.getItem(i) == null) {
				fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
				filler.setItemMeta(fillerMeta);
				fourthbangui.setItem(i, filler);
				}
				}
			Bukkit.getPlayer(player).openInventory(fourthbangui);
		}
		
		
		
		
		
		
		
		
		//if admin clicked red wool, returns 20 days
		if (e.getCurrentItem().getType() == Material.RED_WOOL) {
			e.setCancelled(true);
			Inventory fifthbangui = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Give a ban reason for " + bantargetstring);
			ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = filler.getItemMeta();
			
			for (int i = 0; i <fifthbangui.getSize(); i++) {
				if (fifthbangui.getItem(i) == null) {
				fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
				filler.setItemMeta(fillerMeta);
				fifthbangui.setItem(i, filler);
				}
				}
			Bukkit.getPlayer(player).openInventory(fifthbangui);
		}
		}
		
		}
}
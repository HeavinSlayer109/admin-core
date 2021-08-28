package net.heavin.StaffEssentials.GUIs.Ban;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

import net.heavin.StaffEssentials.StaffEssentials;

public class BanGUI implements Listener{
	
	@SuppressWarnings("unused")
	private StaffEssentials plugin;
	public BanGUI(StaffEssentials plugin) {
		this.plugin = plugin; }
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
	
	
	@SuppressWarnings("unused")
	@EventHandler
	public void banGUI(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getView().getTitle().contains("Ban GUI") && e.getRawSlot() < 45 && e.getCurrentItem().getItemMeta() !=null) {
			if (e.getCurrentItem().equals(null)) {return;}
			e.setCancelled(true);
			Player bantarget = player.getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
			String bantargetstring = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
			/*
			 * 
			 */
			if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
				e.setCancelled(true);
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
				  
				player.openInventory(lgui);
			    }
			/*
			 * 
			 */
			if (e.getCurrentItem().getType() == Material.GREEN_WOOL) {
				Inventory rgui = Bukkit.getServer().createInventory(null, 27, ChatColor.BLACK + "Give ban reason for " + bantargetstring);
				ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta fillerMeta = filler.getItemMeta();
				
				
				for (int i = 0; i <rgui.getSize(); i++) {
					if (rgui.getItem(i) == null) {
					fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
					filler.setItemMeta(fillerMeta);
					rgui.setItem(i, filler);
					}
					}
			}
			}
	}
}

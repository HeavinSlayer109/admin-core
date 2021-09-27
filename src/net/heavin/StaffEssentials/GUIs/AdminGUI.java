package net.heavin.StaffEssentials.GUIs;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.heavin.StaffEssentials.Managers.Methods;


public class AdminGUI {
	
	
	public Inventory inv = Bukkit.getServer().createInventory(null, 54, "Staff GUI");
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
	
	
	
	public AdminGUI(Player player) {
		Inventory gui = Bukkit.createInventory(null, 54, ChatColor.BLACK + "Admin GUI");
		ItemStack guis = new ItemStack(Material.GRAY_STAINED_GLASS, 1);
		ItemMeta isMeta = guis.getItemMeta();
		isMeta.setDisplayName("Character Selection");
		
		
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = filler.getItemMeta();
		
		ItemStack bangui = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ=="));
		ItemMeta banguiMeta = bangui.getItemMeta();
		
		ItemStack estaffmode = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE4NjQxMDE2ZDFkN2NiNDA2YTg2M2RkZTgxZjZiYmQzMDM3MWNmNDZkOGYxNDIwY2Q1ZWM2Y2RiNjRlZjcifX19"));
		ItemMeta estaffmodeMeta = estaffmode.getItemMeta();
		
		ItemStack dstaffmode = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQ2OGFlMzE1MGQ4MWU0YzBhOWQxNzJiZDg0YzRmZjczY2RjMGI4N2ZlZThlYzY2MTIxMzQ2OGQ1NDQ0ODMifX19"));
		ItemMeta dstaffmodeMeta = dstaffmode.getItemMeta();
		
		ItemStack evanish = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgyNmIzNGE3YmNkYTI4OWNiZDY5N2IyZDlhYzk3ODg1YjM2MzQ4ZWJiMmVlYjBmNDY2NjI0MTlmOTdlMiJ9fX0="));
		ItemMeta evanishMeta = evanish.getItemMeta();
		
		ItemStack dvanish = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MyYzU5ZmNkOTI2MjVlYzRkNTc4MTU5YTVmZDViZDQyNDdlMzgyZDQ5NDcyODRjZjUwZjk5OWM4NDExNmMwIn19fQ=="));
		ItemMeta dvanishMeta = dvanish.getItemMeta();
		
		ItemStack enightvision = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDExMzdiOWJmNDM1YzRiNmI4OGZhZWFmMmU0MWQ4ZmQwNGUxZDk2NjNkNmY2M2VkM2M2OGNjMTZmYzcyNCJ9fX0="));
		ItemMeta enightvisionMeta = enightvision.getItemMeta();
		
		ItemStack dnightvision = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMzYTViZmM4YTJhM2ExNTJkNjQ2YTViZWE2OTRhNDI1YWI3OWRiNjk0YjIxNGYxNTZjMzdjNzE4M2FhIn19fQ=="));
		ItemMeta dnightvisionMeta = dnightvision.getItemMeta();
		
		ItemStack playerList = new ItemStack(getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFmNDgzMjYyZDczMTUwMWZmMzBkMTczYzRjMTc5NTg2NGY1NzljNDliMWEyMDEwOGMzNGE5Y2Q5MGRkZTk4YyJ9fX0="));
		ItemMeta playerListMeta = playerList.getItemMeta();
		
		
		ItemStack eflight = new ItemStack(Material.FEATHER);
		ItemMeta eflightMeta = eflight.getItemMeta();
		
		ItemStack dflight = new ItemStack(Material.FEATHER);
		ItemMeta dflightMeta = dflight.getItemMeta();
		
		
		/*
		 * 
		 *  FILLER
		 * 
		 */
		for (int i = 0; i <gui.getSize(); i++) {
			if (gui.getItem(i) == null) {
			fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			filler.setItemMeta(fillerMeta);
			gui.setItem(i, filler);
			}
			}
		
		
		if (!Methods.playercheck.containsValue(player)) {
			estaffmodeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aToggle StaffMode On"));
			List<String> estafflore = new ArrayList<>();
			estafflore.add(ChatColor.GRAY + "Enabling staff mode will let");
			estafflore.add(ChatColor.GRAY + "you enter staff mode so you");
			estafflore.add(ChatColor.GRAY + "can moderate.");
			estaffmodeMeta.setLore(estafflore);
			estaffmode.setItemMeta(estaffmodeMeta);
			gui.setItem(23, estaffmode);
			player.updateInventory();
		} else if (Methods.playercheck.containsValue(player)) {
			dstaffmodeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cToggle StaffMode Off"));
			List<String> dstafflore = new ArrayList<>();
			dstafflore.add(ChatColor.GRAY + "Disabling staff mode will take");
			dstafflore.add(ChatColor.GRAY + "you out of staff mode and will");
			dstafflore.add(ChatColor.GRAY + "put you out of spectator mode forcefully");
			dstafflore.add("");
			dstafflore.add(ChatColor.RED + "Warning" + ChatColor.GRAY + ": Items acquired while in staff mode");
			dstafflore.add(ChatColor.GRAY + "will be reset, your old inventory will be restored");
			dstafflore.add(ChatColor.GRAY + "back to your inventory");
			dstaffmodeMeta.setLore(dstafflore);
			dstaffmode.setItemMeta(dstaffmodeMeta);
			gui.setItem(23, dstaffmode);
			
		}
		
		if (!Methods.vanishCheck.containsValue(player)) {
		evanishMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aToggle Vanish On"));
		List<String> evanishlore = new ArrayList<>();
		evanishlore.add(ChatColor.translateAlternateColorCodes('&', "&6Vanish Mode &7currently &cOff"));
		evanishlore.add("");
		evanishlore.add(ChatColor.translateAlternateColorCodes('&', "&7Turning &aon &7vanish mode will &ehide"));
		evanishlore.add(ChatColor.translateAlternateColorCodes('&', "&7you from other players that are currently"));
		evanishlore.add(ChatColor.translateAlternateColorCodes('&', "&7online on the server that you are currently on"));
		evanishMeta.setLore(evanishlore);
		evanish.setItemMeta(evanishMeta);
		gui.setItem(11, evanish);
		} else if (Methods.vanishCheck.containsValue(player)) {
		dvanishMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cToggle Vanish Off"));
		List<String> dvanishlore = new ArrayList<>();
		dvanishlore.add(ChatColor.translateAlternateColorCodes('&', "&6Vanish Mode &7currently &aOn"));
		dvanishlore.add("");
		dvanishlore.add(ChatColor.translateAlternateColorCodes('&', "&7Turning &coff &7vanish mode will &eshow"));
		dvanishlore.add(ChatColor.translateAlternateColorCodes('&', "&7you from other players that are currently"));
		dvanishlore.add(ChatColor.translateAlternateColorCodes('&', "&7online on the server that you are currently on"));
		dvanishMeta.setLore(dvanishlore);
		dvanish.setItemMeta(dvanishMeta);
		gui.setItem(11, dvanish);
		}
		
		
		
		
		if (!Methods.nvisionCheck.containsValue(player)) {
			enightvisionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aToggle Night-Vision On"));
			enightvision.setItemMeta(enightvisionMeta);
			gui.setItem(20, enightvision);			
		} else if (Methods.nvisionCheck.containsValue(player)) {
			dnightvisionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cToggle Night-Vision Off"));
			dnightvision.setItemMeta(dnightvisionMeta);
			gui.setItem(20, dnightvision);
		}
		
		
		
		playerListMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCurrent Online Player List"));
		List<String> playerListLore = new ArrayList<>();
		playerListLore.add(ChatColor.translateAlternateColorCodes('&', "&7This will show a list of all the online players"));
		playerListLore.add(ChatColor.translateAlternateColorCodes('&', "&eClick a player to teleport to them"));
		playerListLore.add(ChatColor.translateAlternateColorCodes('&', ""));
		playerListLore.add(ChatColor.translateAlternateColorCodes('&', "&cWarning&7: This may cause lag to the whole server"));
		playerListLore.add(ChatColor.translateAlternateColorCodes('&', "&7when opening for the first time"));
		playerListMeta.setLore(playerListLore);
		playerList.setItemMeta(playerListMeta);
		gui.setItem(21, playerList);
		
		
		
		if (!Methods.flightCheck.containsValue(player)) {
			eflightMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aToggle Flight On"));
			eflight.setItemMeta(eflightMeta);
			gui.setItem(12, eflight);
		} else if (Methods.flightCheck.containsValue(player)) {
			dflightMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cToggle Flight Off"));
			dflight.setItemMeta(dflightMeta);
			gui.setItem(12, dflight);
		}

		banguiMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBan Gui"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Methods.color("&c&lThis feature is only currently being reworked!"));
		lore.add(Methods.color("&c&lIt will be available for use in the future"));
		banguiMeta.setLore(lore);
		bangui.setItemMeta(banguiMeta);
		gui.setItem(29, bangui);
		
		
		player.openInventory(gui);
	}
	

}

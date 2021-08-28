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
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class PlayerList {
	
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
	public PlayerList(Player player) {
		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Online Players");
	    for (Player online : Bukkit.getServer().getOnlinePlayers())
	    {
	      ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
	      SkullMeta meta = (SkullMeta)item.getItemMeta();
	     
	      meta.setOwner(online.getName());
	      meta.setDisplayName(online.getName());
	      List<String> lore = new ArrayList<>();
	      lore.add(ChatColor.translateAlternateColorCodes('&', "&7Status: &aOnline"));
	      lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click this player to teleport to"));
	      lore.add(ChatColor.translateAlternateColorCodes('&', "&7them"));
	      meta.setLore(lore);
	     
	      item.setItemMeta(meta);
	      inv.addItem(new ItemStack[] { item });
	    }
	    player.openInventory(inv);
	}
	

}

package cf.heavin.AdminCore.GraphicalUserInterface;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import cf.heavin.AdminCore.Managers.Methods;

public class Playerlist {
	
	
	@SuppressWarnings("deprecation")
	public Playerlist(Player player) {
		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Online Players");
	    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
	      ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
	      SkullMeta meta = (SkullMeta)item.getItemMeta();
	      
	      
	      
	      
	      meta.setOwningPlayer(Bukkit.getOfflinePlayer(online.getName()));
	      meta.setDisplayName(Methods.color("&a" + online.getName()));
	      List<String> lore = new ArrayList<>();
	      lore.add(Methods.color("&7Right-Click to teleport to player"));
	      lore.add(Methods.color("&7Left-Click to open moderation menu"));
	      meta.setLore(lore);
	     
	      item.setItemMeta(meta);
	      inv.addItem(new ItemStack[] { item });
	      
	      ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		  ItemMeta fillerMeta = filler.getItemMeta();
		  for (int i = 0; i <inv.getSize(); i++) {
				if (inv.getItem(i) == null) {
				fillerMeta.setDisplayName(" ");
				filler.setItemMeta(fillerMeta);
				inv.setItem(i, filler);
				}
		  }
	    }
	    player.openInventory(inv);
	}
	

}

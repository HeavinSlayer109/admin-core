package cf.heavin.AdminCore.GraphicalUserInterface.Moderation;

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

public class Moderation {
	
	@SuppressWarnings("deprecation")
	public Moderation(Player player, String target) {
		Methods.ding(player);
		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + target + "'s Moderation Menu");
		
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = filler.getItemMeta();
		ItemStack warns = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
	    SkullMeta warnsMeta = (SkullMeta)warns.getItemMeta();
		
		
		for (int i = 0; i <inv.getSize(); i++) {
			if (inv.getItem(i) == null) {
			fillerMeta.setDisplayName(Methods.color(""));
			filler.setItemMeta(fillerMeta);
			inv.setItem(i, filler);
			}
		}
		
		warnsMeta.setOwningPlayer(Bukkit.getOfflinePlayer(target));
		warnsMeta.setDisplayName(Methods.color("&cView " + target + "'s warnings"));
		List<String> wlore = new ArrayList<>();
		wlore.add(Methods.color("&7Will view " + target + "'s warning list"));
		warns.setItemMeta(warnsMeta);
		inv.setItem(22, warns);
		
		player.openInventory(inv);
	}
}

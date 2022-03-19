package cf.heavin.AdminCore.GraphicalUserInterface;

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

import cf.heavin.AdminCore.Managers.Methods;

public class StaffList {
	
	ArrayList<UUID> list = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	public StaffList(Player player) {
		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Staff List");
	    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
	    	ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
		      SkullMeta meta = (SkullMeta)item.getItemMeta();
	      if (online.hasPermission("admincore.staff")) {
	    	  String displayName = online.getName();
	    	  if (Methods.vanishCheck.containsKey(online.getName()))
	    		  displayName = "&7[&8&lVanished&7]&r " + online.getName();
		      meta.setOwningPlayer(Bukkit.getOfflinePlayer(online.getName()));
		      meta.setDisplayName(Methods.color("&c" + displayName));
		      List<String> lore = new ArrayList<>();
		      
		      boolean pc = Methods.playercheck.containsKey(player.getName());
		      boolean vc = Methods.vanishCheck.containsKey(player.getName());
		      boolean fc = Methods.flightCheck.containsKey(player.getName());
		      boolean nvc = Methods.nvisionCheck.containsKey(player.getName());
		      
		      String pcFi;
		      String pcT = Methods.color("&a" + pc);
		      String pcF = Methods.color("&c" + pc);
		      if (pc) {
		    	  pcFi = pcT;
		      } else pcFi = pcF;
		      
		      String vcFi;
		      String vcT = Methods.color("&a" + pc);
		      String vcF = Methods.color("&c" + pc);
		      if (vc) {
		    	  vcFi = vcT;
		      } else vcFi = vcF;
		      
		      String fcFi;
		      String fcT = Methods.color("&a" + pc);
		      String fcF = Methods.color("&c" + pc);
		      if (fc) {
		    	  fcFi = fcT;
		      } else fcFi = fcF;
		      
		      String nvcFi;
		      String nvcT = Methods.color("&a" + pc);
		      String nvcF = Methods.color("&c" + pc);
		      if (nvc) {
		    	  nvcFi = nvcT;
		      } else nvcFi = nvcF;
		      
		      lore.add(Methods.color("&7Staff Mode: " + pcFi));
		      lore.add(Methods.color("&7Vanish Mode: " + vcFi));
		      lore.add(Methods.color("&7Flight Mode: " + fcFi));
		      lore.add(Methods.color("&7Night-Vision Mode: " + nvcFi));
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
	    }
	    player.openInventory(inv);
	}

}

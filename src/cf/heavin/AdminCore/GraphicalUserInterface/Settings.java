package cf.heavin.AdminCore.GraphicalUserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Files.Data;
import cf.heavin.AdminCore.Managers.Methods;

public class Settings implements Listener{
	public static HashMap<Player, Boolean> staffChat = new HashMap<>();

	public static HashMap<Player, Boolean> voj = new HashMap<>();
	
	@SuppressWarnings("static-access")
	public Settings(Player player) {	
		Data playerConfig = AdminCore.getDataConfig();
		playerConfig.getConfig().getBoolean("settings." + player.getName() + ".staff-chat");
		if (staffChat.containsKey(player)) {
			staffChat.remove(player);
			staffChat.put(player, playerConfig.getConfig().getBoolean("settings." + player.getName() + ".staff-chat"));
		} else {staffChat.put(player, playerConfig.getConfig().getBoolean("settings." + player.getName() + ".staff-chat"));}
		if (voj.containsKey(player)) {
			voj.remove(player);
			voj.put(player, playerConfig.getConfig().getBoolean("settings." + player.getName() + ".vanish-on-join"));
		} else {voj.put(player, playerConfig.getConfig().getBoolean("settings." + player.getName() + ".vanish-on-join"));}
		
		Inventory inv = Bukkit.getServer().createInventory(null, 27, ChatColor.BLACK + "Staff Settings");
		
		
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = filler.getItemMeta();
		
		
		Material scItem = Material.RED_WOOL;
		Material scItemOn = Material.LIME_WOOL;
		Material scItemOff = Material.RED_WOOL;
		
		Material vojItem = Material.RED_WOOL;
		Material vojItemOn = Material.LIME_WOOL;
		Material vojItemOff = Material.RED_WOOL;
		
		
		if (this.staffChat.get(player).equals(true)) {
			scItem = scItemOn;
		} else {scItem = scItemOff;}
		
		ItemStack sc = new ItemStack(scItem);
		ItemMeta scMeta = sc.getItemMeta();
		
		
		
		if (this.voj.get(player).equals(true)) {
			vojItem = vojItemOn;
		} else {vojItem = vojItemOff;}
		
		ItemStack voj = new ItemStack(vojItem);
		ItemMeta vojMeta = voj.getItemMeta();
		
		
		
		
		
		scMeta.setDisplayName(Methods.color("&cToggle StaffChat"));
		List<String> scLore = new ArrayList<>();
		scLore.add(Methods.color("&7Description: When turned on,"));
		scLore.add(Methods.color("&7  staff chat will be enabled"));
		scLore.add(Methods.color("&7  and you will be able to use"));
		scLore.add(Methods.color("&7  staff chat. When turned off"));
		scLore.add(Methods.color("&7  staff chat will be hidden"));
		scLore.add(Methods.color("&aGreen &7= &aOn"));
		scLore.add(Methods.color("&cRed &7= &cOff"));
		scMeta.setLore(scLore);
		sc.setItemMeta(scMeta);
		inv.setItem(11, sc);
		
		
		
		vojMeta.setDisplayName(Methods.color("&cToggle Vanish On Join"));
		List<String> vanishLore = new ArrayList<>();
		vanishLore.add(Methods.color("&7Description: When turned on,"));
		vanishLore.add(Methods.color("&7  you will be hidden after you joined"));
		vanishLore.add(Methods.color("&7  if were in vanish on your last"));
		vanishLore.add(Methods.color("&7  session"));
		vanishLore.add(Methods.color("&aGreen &7= &aOn"));
		vanishLore.add(Methods.color("&cRed &7= &cOff"));
		vojMeta.setLore(vanishLore);
		voj.setItemMeta(vojMeta);
		inv.setItem(13, voj);
		player.openInventory(inv);
		
		for (int i = 0; i <inv.getSize(); i++) {
			if (inv.getItem(i) == null) {
			fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ""));
			filler.setItemMeta(fillerMeta);
			inv.setItem(i, filler);
			}
		}
	}
	
	
}

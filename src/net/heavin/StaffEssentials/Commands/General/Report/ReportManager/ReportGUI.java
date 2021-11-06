package net.heavin.StaffEssentials.Commands.General.Report.ReportManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.heavin.StaffEssentials.Managers.Methods;

public class ReportGUI {
	
	public ReportGUI(Player player, String target) {
		Inventory gui = Bukkit.createInventory(null, 27, ChatColor.BLACK + "Report Reason");
		
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = filler.getItemMeta();
		
		for (int i = 0; i <gui.getSize(); i++) {
			if (gui.getItem(i) == null) {
			fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			filler.setItemMeta(fillerMeta);
			gui.setItem(i, filler);
			}
		}
		
		ItemStack hacking = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta hackingmeta = hacking.getItemMeta();
		hackingmeta.setDisplayName(Methods.color("&eReport " + target + " for &cHacking"));
		hacking.setItemMeta(hackingmeta);
		gui.setItem(11, hacking);
		
		ItemStack exploiting = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta exploitingmeta = exploiting.getItemMeta();
		exploitingmeta.setDisplayName(Methods.color("&eReport " + target + " for &cExploiting"));
		exploiting.setItemMeta(exploitingmeta);
		gui.setItem(13, exploiting);
		
		ItemStack rulebreaking = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta rulebreakingmeta = rulebreaking.getItemMeta();
		rulebreakingmeta.setDisplayName(Methods.color("&eReport " + target + " for &cRule Breaking"));
		rulebreaking.setItemMeta(rulebreakingmeta);
		gui.setItem(15, rulebreaking);
		
		player.openInventory(gui);
	}
}

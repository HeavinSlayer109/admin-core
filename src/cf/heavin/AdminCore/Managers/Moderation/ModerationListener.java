package cf.heavin.AdminCore.Managers.Moderation;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import cf.heavin.AdminCore.GraphicalUserInterface.Moderation.Warnings.Warnings;
import cf.heavin.AdminCore.Managers.Methods;
import net.md_5.bungee.api.ChatColor;

public class ModerationListener implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player)e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("'s Moderation Menu") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains(Methods.color("'s warnings"))) {
	    		if (e.getClick() == ClickType.MIDDLE) {return;}
	    		String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
	    		String[] item = itemName.split(" ");
	    		String target = item[1];
	    		target = target.substring(0, target.length() - 2);
	    		new Warnings(player, target);
            }
		}
	}
	
	
	@EventHandler
	public void onClickList(InventoryClickEvent e) {
		Player player = (Player)e.getWhoClicked();
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Warns List") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
			if (e.getInventory().contains(Material.OAK_SIGN)) {
				if (e.getCurrentItem().getType().equals(Material.OAK_SIGN)) {
					String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
		    		String[] item = itemName.split(" ");
		    		String number = item[2].substring(1);
		    		int numberFinal = Integer.parseInt(number);
		    		String target = ChatColor.stripColor(item[0]);
		    		if (e.getClick() == ClickType.MIDDLE) {return;}
		    		Warnings.showReason(player, target, numberFinal);
		    		player.closeInventory();
		    		Methods.ding(player);
				}
			}
			

		}
	}

}

package cf.heavin.AdminCore.Managers.Staff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StaffListListener implements Listener{
	
	@EventHandler
	public void staffGUIListener(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) {return;}
		if (e.getView().getTitle().contains("Vanished Staff List") && e.getRawSlot() < 54 && e.getCurrentItem().getItemMeta() !=null) {
			e.setCancelled(true);
		}
	}

}

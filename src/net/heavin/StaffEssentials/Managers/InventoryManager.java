package net.heavin.StaffEssentials.Managers;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
 
public class InventoryManager {
 
    private static HashMap<String, ItemStack[]> armourContents = new HashMap<String, ItemStack[]>();
    private static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
    private static HashMap<String, Integer> xplevel = new HashMap<String, Integer>();
 
    public static void saveInventory(Player player){
        armourContents.put(player.getName(), player.getInventory().getArmorContents());
        inventoryContents.put(player.getName(), player.getInventory().getContents());
        xplevel.put(player.getName(), player.getLevel());
        player.getInventory().clear();
        player.setExp(0);
        player.setLevel(0);
    }
 
    public static void restoreInventory(Player player){
        player.getInventory().clear();
        player.getInventory().setContents(inventoryContents.get(player.getName()));
        player.getInventory().setArmorContents(armourContents.get(player.getName()));
        player.setLevel(xplevel.get(player.getName()));
 
        xplevel.remove(player.getName());
        armourContents.remove(player.getName());
        inventoryContents.remove(player.getName());
    }
}
 
package cf.heavin.AdminCore.GraphicalUserInterface.Moderation.Warnings;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Files.Warns;
import cf.heavin.AdminCore.Managers.Methods;

public class Warnings {
	
	
	public ItemStack getHead(String textureURL) {
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
	
	public static void showReason(Player player, String target, int reasonInt) {
		Warns warnConfig = AdminCore.getWarnsConfig();
		String reason = warnConfig.getConfig().getString(target + "." + reasonInt + ".reason");
		player.sendMessage(Methods.color("&7===============&7[&b" + target + "'s&c warn &b#" + reasonInt + "&c reason&7]==============="));
		player.sendMessage(Methods.color("&c" + reason));
	}
	
	public Warnings(Player player, String target) {
		Methods.ding(player);
		Warns warnConfig = AdminCore.getWarnsConfig();
		
		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLACK + "Warns List");
		
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = filler.getItemMeta();
		ItemStack warn = new ItemStack(Material.OAK_SIGN);
		ItemMeta warnMeta = warn.getItemMeta();
		int list;
        if (warnConfig.getConfig().getConfigurationSection(target + ".") == null) {
      	  list = 1;
        } else list = warnConfig.getConfig().getConfigurationSection(target + ".").getKeys(false).size();
		
        
        for (int i = 1; i <=list; i++) {
			String warner = warnConfig.getConfig().getString(target + "." + i + ".warner");
			boolean silentBool = warnConfig.getConfig().getBoolean(target + "." + i + ".silent");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // format which the LocalDateTime will use
		    LocalDateTime now = LocalDateTime.now(); // get current time
		    String dateStart = warnConfig.getConfig().getString(target + "." + i + ".time"); // what time the player has been warned
		    String dateStop = dtf.format(now); // time now
		    
		    // Custom date format
		    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    Date d1 = null;
		    Date d2 = null;
		    try {
		        d1 = format.parse(dateStart);
		        d2 = format.parse(dateStop);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		    
		    long diff = d2.getTime() - d1.getTime();
		    
		    long days = TimeUnit.MILLISECONDS.toDays(diff);
		    long remainingHoursInMillis = diff - TimeUnit.DAYS.toMillis(days);
		    long hours = TimeUnit.MILLISECONDS.toHours(remainingHoursInMillis);
		    long remainingMinutesInMillis = remainingHoursInMillis - TimeUnit.HOURS.toMillis(hours);
		    long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMinutesInMillis);
		    long remainingSecondsInMillis = remainingMinutesInMillis - TimeUnit.MINUTES.toMillis(minutes);
		    long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingSecondsInMillis);
		    
			warnMeta.setDisplayName(Methods.color("&7" + target +" Warn #" + i));
			List<String> wlore = new ArrayList<>();
			wlore.add(Methods.color("&7Warned By: " + warner));
			wlore.add(Methods.color("&7Silent: " + silentBool));
			wlore.add(Methods.color("&7Time Warned: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s" + " &cago"));
			wlore.add(Methods.color("&7Reason: &cClick to show"));
			warnMeta.setLore(wlore);
			warn.setItemMeta(warnMeta);
			inv.setItem(i-1, warn);
		}
	    
	    
	    
		
		for (int i = 0; i <inv.getSize(); i++) {
			if (inv.getItem(i) == null) {
			fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ""));
			filler.setItemMeta(fillerMeta);
			inv.setItem(i, filler);
			}
		}
		player.openInventory(inv);
	}
}

package cf.heavin.AdminCore.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Config;

public class Methods {
	
  private static JavaPlugin plugin = JavaPlugin.getPlugin(AdminCore.class);
  
  public static HashMap<String, Player> playercheck = new HashMap<>();
  
  public static HashMap<String, Player> vanishCheck = new HashMap<>();
  
  public static HashMap<String, Player> nvisionCheck = new HashMap<>();
  
  public static HashMap<String, Player> flightCheck = new HashMap<>();
  
  public static ArrayList<UUID> lov = new ArrayList<>();
  
  
  public static void ding(Player player) {
	  player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
  }
  
  
  public static String color(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
  /**
   * Can be used to store players that left in vanish
   * @param player
   */
  public static void leftOnVanish(Player player) {
	  if (lov.contains(player.getUniqueId())) {
		  lov.remove(player.getUniqueId());
		  lov.add(player.getUniqueId());
	  } else lov.add(player.getUniqueId());
  }

  
  /**
   * Creates a string based progress bar with colors. 
   * Currently used for the plugins current development progress
   * @param current current progress
   * @param max Maximum number for the progress bar
   * @param totalBars Total bars showed
   * @param symbol Symlbol for progress bar
   * @param completedColor Color for completed bars
   * @param notCompletedColor Color for not completed bars
   * @return String
   */
  
  public String getProgressBar(int current, int max, int totalBars, String symbol, String completedColor, String notCompletedColor){

      float percent = (float) current / max;

      int progressBars = (int) ((int) totalBars * percent);

      int leftOver = (totalBars - progressBars);

      StringBuilder sb = new StringBuilder();
      sb.append(ChatColor.translateAlternateColorCodes('&', completedColor));
      for (int i = 0; i < progressBars; i++) {
          sb.append(symbol);
      }
      sb.append(ChatColor.translateAlternateColorCodes('&', notCompletedColor));
      for (int i = 0; i < leftOver; i++) {
          sb.append(symbol);
      }
      return sb.toString();
  }
  
  
  
  /**
   * Enables staff Mode on the player
   * 
   * @param player
   */
  public static void enableStaff(Player player) {
    if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*"))
      if (playercheck.containsKey(player.getName())) {
        player.sendMessage(color(Config.staffAlreadyEnabled()));
      } else {
        player.sendMessage(color(Config.staffEnabled()));
        playercheck.put(player.getName(), player);
      }  
  }
  
  
  
  
  /**
   * Disables staff Mode on the player
   * 
   * @param player
   */
public static void disableStaff(Player player) {
    if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (!playercheck.containsKey(player.getName()))
          player.sendMessage(color(Config.staffAlreadyDisabled())); 
        if (vanishCheck.containsKey(player.getName())) {
          vanishCheck.remove(player.getName(), player);
          player.showPlayer(plugin, player);
        } 
        if (nvisionCheck.containsKey(player.getName())) {
          nvisionCheck.remove(player.getName());
          player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        } 
        
        if (player.getGameMode() == GameMode.SURVIVAL && flightCheck.containsKey(player.getName())) {
          flightCheck.remove(player.getName());
          player.setAllowFlight(false);
          player.setFlying(false);
        } 
        playercheck.remove(player.getName(), player);
        player.sendMessage(color(Config.staffDisabled()));
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      } 
    } else {
      player.sendMessage(color(Config.noPermission()));
    } 
  }




/**
 * Enables vanish mode for the player
 * 
 * @param player
 */
@SuppressWarnings("deprecation")
public static void enableVanish(Player player) {
    if (player.hasPermission("admincore.vanish") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (vanishCheck.containsKey(player.getName()))
          return; 
        player.sendMessage(color(Config.vanishEnabledMessage()));
        for (Player onlineplayers : Bukkit.getOnlinePlayers()) {
        	if (!onlineplayers.hasPermission("admincore.cansee") || player.hasPermission("admincore.*")) {
        		onlineplayers.hidePlayer(player);
        	}
        }
        vanishCheck.put(player.getName(), player);
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      }  
  }
  }




/**
 * Disables vanish mode for the player
 * 
 * @param player
 */
@SuppressWarnings("deprecation")
public static void disableVanish(Player player) {
    if (player.hasPermission("admincore.vanish") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (vanishCheck.containsKey(player.getName())) {
          vanishCheck.remove(player.getName());
          for (Player onlineplayers : Bukkit.getOnlinePlayers())
            onlineplayers.showPlayer(player); 
          player.sendMessage(color(Config.vanishDisabledMessage()));
        } else {
          return;
        } 
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      } 
    } else {
      player.sendMessage(color(Config.noPermission()));
    } 
}




/**
 * Enables Night Vision effect to player
 * 
 * @param player
 */
  public static void enableNightVision(Player player) {
    if (player.hasPermission("admincore.nightvision") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (nvisionCheck.containsKey(player.getName()))
          return; 
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true));
        nvisionCheck.put(player.getName(), player);
        player.sendMessage(color(Config.nvisionEnabled()));
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      } 
    } else {
      player.sendMessage(color(Config.noPermission()));
    } 
  }
  
  
  
  
  /**
   * Disables Night Vision effect to player
   * 
   * @param player
   */
  public static void disableNightVision(Player player) {
    if (player.hasPermission("admincore.nightvision") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (nvisionCheck.containsKey(player.getName())) {
          nvisionCheck.remove(player.getName(), player);
          player.removePotionEffect(PotionEffectType.NIGHT_VISION);
          player.sendMessage(color(Config.nvisionDisabled()));
        } else {
          return;
        }
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      } 
    } else {
      player.sendMessage(color(Config.noPermission()));
    } 
  }
  
  
  
  
  /**
   * Toggles Flight Mode for player
   * 
   * @param player
   */
  public static void toggleFlight(Player player) {
	  if (player.hasPermission("admincore.fly") || player.hasPermission("admincore.*")) {
		  if (playercheck.containsKey(player.getName())) {
			  if (flightCheck.containsKey(player.getName())) {
				  flightCheck.remove(player.getName(), player);
		          player.setAllowFlight(false);
		          player.setFlying(false);
		          player.sendMessage(color(Config.flightDisabled()));
			  } else {
				  flightCheck.put(player.getName(), player);
			      player.setAllowFlight(true);
			      player.setFlying(true);
			      player.sendMessage(color(Config.flightEnabled()));
			  }
		  } else player.sendMessage(Methods.color(Config.notInStaffMode()));
	  } else {player.sendMessage(color(Config.noPermission()));}
  }
  
  
  
  
  /**
   * Enables Flight Mode for player
   * 
   * @param player
   */
  public static void enableFlight(Player player) {
    if (player.hasPermission("admincore.fly") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (flightCheck.containsKey(player.getName()))
          return; 
        flightCheck.put(player.getName(), player);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.sendMessage(color(Config.flightEnabled()));
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      } 
    } else {player.sendMessage(color(Config.noPermission()));} 
  }
  
  
  
  
  /**
   * Disables Flight Mode for player
   * 
   * @param player
   */
  public static void disableFlight(Player player) {
    if (player.hasPermission("admincore.fly") || player.hasPermission("admincore.*")) {
      if (playercheck.containsKey(player.getName())) {
        if (flightCheck.containsKey(player.getName())) {
        	flightCheck.remove(player.getName(), player);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(color(Config.flightDisabled()));
        } else { return;} 
      } else {
        player.sendMessage(color(Config.notInStaffMode()));
      } 
    } else {
      player.sendMessage(color(Config.noPermission()));
    } 
  }
}


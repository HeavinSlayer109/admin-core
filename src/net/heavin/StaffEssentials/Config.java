package net.heavin.StaffEssentials;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;


public class Config {
	
	private static StaffEssentials main;
	  public Config(StaffEssentials main) {
	    Config.main = main;
	    main.getConfig().options().copyDefaults();
	    main.saveDefaultConfig();
	  }
	  public static FileConfiguration getConfig() {
	    return main.getConfig();
	  }
	  
	  
	  /*
	   * some stuff i dont know what to name
	   * 
	   */
	  public static String noPermission() {
		return getConfig().getString("no-permission");
	  }
	  public static String pluginPrefix() {
		  if (getConfig().getString("prefix").equals("None")) {
			  return "";
		  }
		return getConfig().getString("prefix") + "&r ";
	  }
	  
	  
	  
	  
	  /*
	   * LESS LIKELY TO GET USED
	   */
	  public static String staffAlreadyEnabled() {
		return getConfig().getString("less-likely-to-be-used.staff-already-enabled");
	  }
	  public static String staffAlreadyDisabled() {
		return getConfig().getString("less-likely-to-be-used.staff-already-disabled");
	  }
	  
	  public static String vanishAlreadyDisabled() {
	    return getConfig().getString("less-likely-to-be-used.vanish-already-disabled");
	  }
	  public static String vanishAlreadyEnabled() {
	    return getConfig().getString("less-likely-to-be-used.vanish-already-enabled");
	  }
	  
	  public static String nvisionAlreadyEnabled() {
		return getConfig().getString("less-likely-to-be-used.night-vision-already-enabled");
	  }
	  public static String nvisionAlreadyDisabled() {
		return getConfig().getString("less-likely-to-be-used.night-vision-already-disabled");
	  }
	  
	  
	  
	  public static String vanishedStaffLeaveMsg() {
		return getConfig().getString("vanished-staff-left-message");
	  }
	  /*
	   *  
	   */
	  
	  /*
	   *  Staff Config
	   */
	  
	  public static String staffEnabled() {
		return getConfig().getString("staffmode-enabled-message");
	  }
	  public static String staffDisabled() {
		return getConfig().getString("staffmode-disabled-message");
	  }
	  
	  public static String notInStaffMode() {
		return getConfig().getString("not-in-staff-mode");
	  }
	  
	  
	  
	  /*
	   *  Vanish Config
	   */
	  public static String vanishEnabledMessage() {
		return getConfig().getString("vanish-enabled-message");
	  }
	  public static String vanishDisabledMessage() {
		return getConfig().getString("vanish-disabled-message");
	  }
	  
	  
	  
	  /*
	   *  Night Vision Config
	   */
	  public static String nvisionEnabled() {
		return getConfig().getString("night-vision-enabled-message");
	  }
	  public static String nvisionDisabled() {
		return getConfig().getString("night-vision-disabled-message");
	  }
	  
	  
	  
	  /*
	   *  Flight Config
	   */
	  public static String flightEnabled() {
		return getConfig().getString("flight-enabled-message");
	  }
	  public static String flightDisabled() {
		return getConfig().getString("flight-disabled-message");
	  }
	  
	  
	  
	  
	  /*
	   * ADMIN GUI CONFIGURATION
	   * 
	   */
	  
	  public static String teleportedToPlayer() {
		return getConfig().getString("teleported-to-player");
	  }
	  
	  
	  /*
	   * BAN CONFIGURATION
	   * 
	   */
	  
	  public static List<String> banConfig() {
		return getConfig().getStringList("ban.duration");
	  }

}

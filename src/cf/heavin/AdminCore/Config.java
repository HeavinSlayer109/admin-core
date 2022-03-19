package cf.heavin.AdminCore;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	  private static AdminCore main;
	  
	  public Config(AdminCore main) {
	    Config.main = main;
	    main.getConfig().options().copyDefaults();
	    main.saveDefaultConfig();
	  }
	  
	  public static FileConfiguration getConfig() {
	    return main.getConfig();
	  }
	  
	  public static String pluginPrefix() {
		    if (getConfig().getString("prefix").equals("None"))
		      return ""; 
		    return String.valueOf(getConfig().getString("prefix")) + "&r ";
	  }
	  public static String noPermission() {
	    return pluginPrefix() + getConfig().getString("no-permission");
	  }
	  
	  
	  public static String staffAlreadyEnabled() {
	    return pluginPrefix() + getConfig().getString("staff-already-enabled");
	  }
	  public static String staffAlreadyDisabled() {
	    return pluginPrefix() + getConfig().getString("staff-already-disabled");
	  }
	  //public static String vanishAlreadyDisabled() {
	  //  return pluginPrefix() + getConfig().getString("vanish-already-disabled");
	  //}
	  //public static String vanishAlreadyEnabled() {
	  //  return pluginPrefix() + getConfig().getString("vanish-already-enabled");
	  //}
	  //public static String nvisionAlreadyEnabled() {
	  //  return pluginPrefix() + getConfig().getString("night-vision-already-enabled");
	  //}
	  //public static String nvisionAlreadyDisabled() {
	  //  return pluginPrefix() + getConfig().getString("night-vision-already-disabled");
	  //}
	  //public static String vanishedStaffLeaveMsg() {
	  //  return pluginPrefix() + getConfig().getString("vanished-staff-left-message");
	  //}
	  
	  
	  public static String staffEnabled() {
	    return pluginPrefix() + getConfig().getString("staffmode-enabled-message");
	  }
	  public static String staffDisabled() {
	    return pluginPrefix() + getConfig().getString("staffmode-disabled-message");
	  }
	  public static String notInStaffMode() {
	    return pluginPrefix() + getConfig().getString("not-in-staff-mode");
	  }
	  public static String vanishEnabledMessage() {
	    return pluginPrefix() + getConfig().getString("vanish-enabled-message");
	  }
	  public static String vanishDisabledMessage() {
        return getConfig().getString("vanish-disabled-message");
	  }
	  public static String nvisionEnabled() {
	    return pluginPrefix() + getConfig().getString("night-vision-enabled-message");
	  }
	  public static String nvisionDisabled() {
	    return pluginPrefix() + getConfig().getString("night-vision-disabled-message");
	  }
	  public static String flightEnabled() {
	    return pluginPrefix() + getConfig().getString("flight-enabled-message");
	  }
	  public static String flightDisabled() {
	    return pluginPrefix() + getConfig().getString("flight-disabled-message");
	  }
	  public static String teleportedToPlayer() {
	    return pluginPrefix() + getConfig().getString("teleported-to-player");
	  }
	  //public static List<String> banConfig() {
	  //  return getConfig().getStringList("ban.duration");
	  //}
	  //public static String banMessage() {
	  //  return pluginPrefix() + getConfig().getString("ban-message");
	  //}
	  //public static String unbanMessage() {
	  //  return pluginPrefix() + getConfig().getString("unban-message");
	  //}
	  
	  public static String reportSuccess() {
	    return pluginPrefix() + getConfig().getString("report-successful");
	  }
	  public static String reportNoPlayerArg() {
	    return pluginPrefix() + getConfig().getString("report-no-player-argument");
	  }
	  public static String reportPlayerNotOnline() {
	    return pluginPrefix() + getConfig().getString("report-player-not-online");
	  }
	  public static String reportNoReason() {
	    return pluginPrefix() + getConfig().getString("report-no-reason");
	  }
	  public static String reportRecieveMessage() {
	    return pluginPrefix() + getConfig().getString("report-recieve-message");
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  public static String warnMessage() {
	    return getConfig().getString("warn-message");
	  }
	  public static String warnMessageSilent() {
		return getConfig().getString("warn-message-silent");
	  }
	  public static String warnNoPlayerArg() {
	    return pluginPrefix() + getConfig().getString("warn-no-player-argument");
	  }
	  public static String warnPlayerNotOnline() {
	    return pluginPrefix() + getConfig().getString("warn-player-not-online");
	  }
	  public static String warnNoReason() {
	    return pluginPrefix() + getConfig().getString("warn-no-reason");
	  }
	}

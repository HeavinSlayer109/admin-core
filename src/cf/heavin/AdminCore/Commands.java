package cf.heavin.AdminCore;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import cf.heavin.AdminCore.GraphicalUserInterface.Playerlist;
import cf.heavin.AdminCore.GraphicalUserInterface.StaffList;
import cf.heavin.AdminCore.GraphicalUserInterface.Admin.AdminGUI;
import cf.heavin.AdminCore.GraphicalUserInterface.Staff.StaffGUI;
import cf.heavin.AdminCore.Managers.Methods;

public class Commands implements CommandExecutor, TabCompleter {
  @SuppressWarnings("unused")
private AdminCore plugin;
  
  public Commands(AdminCore plugin) {
    this.plugin = plugin;
  }
  
  public Commands() {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0) {
        if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*")) {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------- &7[&6Admin Core&7] &7----------"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore enable &7- Turn on staff mode"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore disable &7- Turn off staff mode"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore gui &7- GUI for staff and moderators"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore admin &7- GUI for administrators"));
          
        } else {
          player.sendMessage(Methods.color(String.valueOf(String.valueOf(Config.pluginPrefix())) + Config.noPermission()));
        }
      } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
        if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*")) {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------- &7[&6Admin Core&7] &7----------"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore enable &7- Turn on staff mode"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore disable &7- Turn off staff mode"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore gui &7- GUI for staff and moderators"));
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/admincore admin &7- GUI for administrators"));
        } else {
          player.sendMessage(Methods.color(Config.noPermission()));
        } 
        
        
        
        
        
      } else if (args.length == 1 && args[0].equalsIgnoreCase("enable")) {
        if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*")) {
          Methods.enableStaff(player);
        } else {
          player.sendMessage(Methods.color(Config.noPermission()));
        } 
        
        
        
        
        
      } else if (args.length == 1 && args[0].equalsIgnoreCase("disable")) {
        if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*")) {
          Methods.disableStaff(player);
        } else {
          player.sendMessage(Methods.color(Config.noPermission()));
        } 
        
        
        
        
        
      } else if(args.length == 1 && args[0].equalsIgnoreCase("gui")) {
        if (player.hasPermission("admincore.staff") || player.hasPermission("admincore.*")) {
        	new StaffGUI(player);
        } else { player.sendMessage(Methods.color(Config.noPermission())); }
        
        
        
        
        
      } else if (args.length == 1 && args[0].equalsIgnoreCase("admin")) {
        if (player.hasPermission("admincore.admin") || player.hasPermission("admincore.*")) {
          if (args[0].equalsIgnoreCase("admin"))
            player.sendMessage(Methods.color(String.valueOf(String.valueOf(Config.pluginPrefix())) + 
                  "&cInvalid arguments: /admincore admin [gui, staffspy]")); 
        } else {
          player.sendMessage(Methods.color(String.valueOf(String.valueOf(Config.pluginPrefix())) + Config.noPermission()));
        } 
        
        
        
        
        
      } else if (args.length == 2 && args[0].equalsIgnoreCase("admin")) {
        if (player.hasPermission("admincore.admin") || player.hasPermission("admincore.*")) {
          if (args[1].equalsIgnoreCase("gui"))
        	  new AdminGUI(player);
        } else {
          player.sendMessage(Methods.color(String.valueOf(String.valueOf(Config.pluginPrefix())) + Config.noPermission()));
        } 
        
        
        
        
        
      } else if (args.length == 1 && args[0].equalsIgnoreCase("vanish")) {
        if (player.hasPermission("admincore.vanish") || player.hasPermission("admincore.*")) {
          if (Methods.vanishCheck.containsKey(player.getName())) {
            Methods.disableVanish(player);
          } else {
            Methods.enableVanish(player);
          } 
        } else {player.sendMessage(Methods.color(String.valueOf(String.valueOf(Config.pluginPrefix())) + Config.noPermission()));} 
        
        
        
      } else if (args.length == 2 && args[0].equalsIgnoreCase("vanish") && args[1].equalsIgnoreCase("list")) {
    	  new StaffList(player);
      } else if (args.length == 1 && args[0].equalsIgnoreCase("fly")) {
        if (player.hasPermission("admincore.fly") || player.hasPermission("admincore.*"))
          Methods.toggleFlight(player); 
        
        
        
        
        
      } else if (args.length == 1 && args[0].equalsIgnoreCase("playerlist")) {
        if (player.hasPermission("admincore.playerlist") || player.hasPermission("admincore.*")) {
        	new Playerlist(player);
        } else {player.sendMessage(Methods.color(String.valueOf(String.valueOf(Config.pluginPrefix())) + Config.noPermission())); }
      }
    } else {
      System.out.println("This command cannot be sent on the console or a commandblock");
      return false;
    } 
    return false;
  }
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    List<String> arguments = Arrays.asList(new String[] { "help", "enable", "disable", "gui", "admin", "vanish", "fly", "playerlist"});
    List<String> FList = Lists.newArrayList();
    if (args.length == 1) {
      for (String s : arguments) {
        if (s.toLowerCase().startsWith(args[0].toLowerCase()))
          FList.add(s); 
      } 
      return FList;
    } 
    return null;
  }
}

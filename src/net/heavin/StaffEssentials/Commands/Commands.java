package net.heavin.StaffEssentials.Commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.heavin.StaffEssentials.AdminCore;
import net.heavin.StaffEssentials.DataManagers.Config;
import net.heavin.StaffEssentials.GUIs.AdminGUI;
import net.heavin.StaffEssentials.GUIs.PlayerList;
import net.heavin.StaffEssentials.GUIs.StaffGUI;
import net.heavin.StaffEssentials.GUIs.StaffSpyGui;
import net.heavin.StaffEssentials.GUIs.Ban.BanGUI;
import net.heavin.StaffEssentials.Managers.Methods;

public class Commands implements CommandExecutor, TabCompleter {
	@SuppressWarnings("unused")
	private AdminCore plugin;
	public Commands(AdminCore plugin) {
		this.plugin = plugin; }
	
	public Commands() {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (player.hasPermission("se.staff")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------- &7[&6Staff Essentials&7] &7----------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se enable &7- Turn on staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se disable &7- Turn off staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se gui &7- GUI for staff and moderators"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se admin &7- GUI for administrators"));
				} else {player.sendMessage(Methods.color(Config.pluginPrefix() + "&cSorry but this command is only used for staff members!"));}
			} else if (args.length == 1 && args[0].equalsIgnoreCase("enable")) {
		    	if (player.hasPermission("se.staff")) {
		    			Methods.enableStaff(player);
				} else {player.sendMessage(Methods.color(Config.pluginPrefix() + Config.noPermission()));}
		    	
			} else if (args.length == 1 && args[0].equalsIgnoreCase("disable")) {
				if (player.hasPermission("se.staff")) {
				Methods.disableStaff(player);
				} else {player.sendMessage(Methods.color(Config.pluginPrefix() + Config.noPermission()));}
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
		    	if (player.hasPermission("se.staff")) {
		    	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------- &7[&6Staff Essentials&7] &7----------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se enable &7- Turn on staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se disable &7- Turn off staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se gui &7- GUI for staff and moderators"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se admin &7- GUI for administrators"));
		    	} else {player.sendMessage(Methods.color(Config.pluginPrefix() + "&cSorry but this command is only used for staff members!"));}
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("gui")) {
		    	if (player.hasPermission("se.staff")) {
		    	new StaffGUI(player);
		    	} else {player.sendMessage(Methods.color(Config.pluginPrefix() + Config.noPermission()));}
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("vanish")) {
		    	if (player.hasPermission("se.vanish")) {
		    		if (Methods.vanishCheck.containsKey(player.getName())) {
		    			Methods.disableVanish(player);
		    		} else {Methods.enableVanish(player);}
		    		
		    	} else {player.sendMessage(Methods.color(Config.pluginPrefix() + Config.noPermission()));}
		    	
		    	
		    } else if (args.length == 1) {
		    	if (player.hasPermission("se.admin")) {
		    		if (args[0].equals("admin")) {
		    			player.sendMessage(Methods.color(Config.pluginPrefix() + "&cInvalid arguments: /se admin [gui, staffspy]"));
				    	}
		    }
		    } else if (args.length == 2 && args[0].equalsIgnoreCase("admin")) {
		    	if (player.hasPermission("se.admin")) {
		    		 if (args[1].equalsIgnoreCase("gui")) {
		    		    	new AdminGUI(player);
		    			} else if (args[1].equalsIgnoreCase("staffspy")) {
			    				new StaffSpyGui(player);
		    			} else {player.sendMessage(Methods.color(Config.pluginPrefix() + "&cInvalid arguments: /se admin [gui, staffspy]"));}
		    	} else {player.sendMessage(Methods.color(Config.pluginPrefix() + Config.noPermission()));}
		    	
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("playerlist")) {
		    	new PlayerList(player);
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("debug")) {
		    	player.sendMessage(player.getName());
		    	if (player.getName().equals("birdh")) {
		    		
		    		new BanGUI(player);
		    	} else {player.sendMessage(Methods.color("&7[&4&lERROR&7] &cYou are not the author of the plugin!"));}
		    }
		    } else { System.out.println("This command cannot be sent on the console or a commandblock"); }
		return false;
		}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> arguments = Arrays.asList("help", "enable", "disable", "gui", "vanish", "admin", "");
        List<String> Flist = Lists.newArrayList();
        if(args.length == 1){
            for(String s : arguments){
                if(s.toLowerCase().startsWith(args[0].toLowerCase()))Flist.add(s);
            }
            return Flist;
        }

        return null;
	}
	
	
}
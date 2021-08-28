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

import net.heavin.StaffEssentials.StaffEssentials;
import net.heavin.StaffEssentials.GUIs.AdminGUI;
import net.heavin.StaffEssentials.GUIs.PlayerList;
import net.heavin.StaffEssentials.GUIs.StaffGUI;
import net.heavin.StaffEssentials.Managers.Methods;

public class Commands implements CommandExecutor, TabCompleter {
	@SuppressWarnings("unused")
	private StaffEssentials plugin;
	public Commands(StaffEssentials plugin) {
		this.plugin = plugin; }
	
	public Commands() {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------- &7[&6Staff Essentials&7] &7----------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se enable &7- Turn on staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se disable &7- Turn off staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se gui &7- GUI for staff and moderators"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se admin &7- GUI for administrators"));
				

		    } else if (args.length == 1 && args[0].equalsIgnoreCase("enable")) {
		    	if (player.hasPermission("se.staff")) {
		    			Methods.enableStaff(player);
		    	}
		    	
			} else if (args.length == 1 && args[0].equalsIgnoreCase("disable")) {
				Methods.disableStaff(player);
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
		    	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------- &7[&6Staff Essentials&7] &7----------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se enable &7- Turn on staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se disable &7- Turn off staff mode"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se gui &7- GUI for staff and moderators"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/se admin &7- GUI for administrators"));
		    	
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("gui")) {
		    	new StaffGUI(player);
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("vanish")) {
		    	if (player.hasPermission("se.vanish")) {
		    		if (Methods.vanishCheck.containsKey(player.getName())) {
		    			Methods.disableVanish(player);
		    		} else {Methods.enableVanish(player);}
		    		
		    	}
		    } else if (args.length == 2 && args[0].equalsIgnoreCase("admin")) {
		    	if (args[1] == null) { player.sendMessage(ChatColor.RED + "Invalid arguments: /se admin [gui]"); }
		    	if (args[1].equalsIgnoreCase("gui")) {
		    	new AdminGUI(player);
		    } else if (args.length == 1 && args[0].equalsIgnoreCase("playerlist")) {
		    	new PlayerList(player);
		    }
		    }
		
		} else { System.out.println("This command cannot be used on the console"); }
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> arguments = Arrays.asList("help", "enable", "disable", "gui", "vanish", "admin", "playerlist");
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
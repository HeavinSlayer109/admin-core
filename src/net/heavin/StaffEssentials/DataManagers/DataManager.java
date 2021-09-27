package net.heavin.StaffEssentials.DataManagers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.heavin.StaffEssentials.AdminCore;

public class DataManager {
	
	public AdminCore plugin;
	public FileConfiguration dataConfig = null;
	public File configFile = null;
	
	public DataManager(AdminCore plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	public DataManager() {}
	public void reloadConfig() {
		if (this.configFile == null)
			this.configFile = new File(plugin.getDataFolder(), "bans.yml");
		this.dataConfig = YamlConfiguration.loadConfiguration(configFile);
		InputStream defaultStream = plugin.getResource("bans.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
		}
	}
	
	public FileConfiguration getConfig() {
		if (this.dataConfig == null) 
			reloadConfig();
		
		return dataConfig;

	}
	public void saveConfig() {
		if (dataConfig == null || configFile == null) 
			return;
		
		try {
			this.getConfig().save(configFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
		}
	}
	public void saveDefaultConfig() {
		if (this.configFile == null)
			this.configFile = new File(plugin.getDataFolder(), "ans.yml");
		
		if (!this.configFile.exists()) {
			this.plugin.saveResource("bans.yml", false);
		}
	}

}

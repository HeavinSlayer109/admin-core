package cf.heavin.AdminCore.Files;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cf.heavin.AdminCore.AdminCore;

public class Data {
	 
    private final AdminCore core;
 
    //Generating the variables which are going to be used
    private final File file;
    private final FileConfiguration config;
    
    
 
    /**
     *  The class constructor which contains initialize the variables created
     *  above when an instance of the class is created.
     *
     *  Usually I'd avoid logic inside the constructor, however for simplicity
     *  I chose to do that here. If you have a better understanding, you might
     *  want to look into creating a Factory method instead.
     *
     * @param core
     */
    @SuppressWarnings("static-access")
	public Data(AdminCore core) {
        this.core = core;
        file = new File(core.getDataFolder(), "staff-settings.yml");
        config = new YamlConfiguration().loadConfiguration(file);
        createFile();
    }
 
    /**
     * The method used to save the configuration file after being modified.
     * @throws IOException Rethrows the failure
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
 
    /**
     * The standard getter for the file variable
     *
     * @return file
     */
    public File getFile() {
        return file;
    }
 
    /**
     * The standard getter for the config variable
     *
     * @return config
     */
    public FileConfiguration getConfig() {
        return config;
    }
 
    /**
     * Creates the file including the folder in case they don't exist.
     *
     * @throws IOException Rethrows the failure
     */
    private void createFile(){
        if(!core.getDataFolder().exists()) {
            core.getDataFolder().mkdirs();
        }
 
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }
 
}
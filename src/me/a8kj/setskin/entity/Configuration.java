package me.a8kj.setskin.entity;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Configuration {
  private File file;
  
  private FileConfiguration fileConfiguration;
  
  public File getFile() {
    return this.file;
  }
  
  public void setFile(File file) {
    this.file = file;
  }
  
  public FileConfiguration getFileConfiguration() {
    return this.fileConfiguration;
  }
  
  public void setFileConfiguration(FileConfiguration fileConfiguration) {
    this.fileConfiguration = fileConfiguration;
  }
  
  private void init(boolean defaultSave, String configName, JavaPlugin plugin) throws IOException {
    this.file.getParentFile().mkdirs();
    if (!this.file.exists())
      if (!defaultSave) {
        if (!this.file.createNewFile()) {
          plugin.getLogger().log(Level.WARNING, 
              "The config file does not created , please make sure you create one");
        } else {
          plugin.getLogger().info("The config file was created successfully !");
        } 
      } else {
        plugin.saveResource(configName, true);
      }  
  }
  
  public Configuration(String fileName, JavaPlugin plugin, boolean saveDefault) {
    setFile(new File(plugin.getDataFolder(), fileName));
    try {
      init(saveDefault, fileName, plugin);
    } catch (IOException e) {
      e.printStackTrace();
    } 
    load();
  }
  
  public void save() {
    try {
      this.fileConfiguration.save(this.file);
    } catch (IOException var1) {
      var1.printStackTrace();
    } 
  }
  
  public void load() {
    setFileConfiguration((FileConfiguration)YamlConfiguration.loadConfiguration(this.file));
  }
}

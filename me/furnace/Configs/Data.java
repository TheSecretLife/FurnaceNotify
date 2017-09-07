package me.furnace.Configs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Data
{
  private String msg;
  private HashMap<String, Object> msgs = new HashMap();
  
  public Data() {}
  
  public Data(String message)
  {
    msg = message;
  }
  
  public File dataFile() {
    return new File("plugins/FurnaceNotify/" + File.separator + "storage.yml");
  }
  
  public boolean exists() {
    return dataFile().exists();
  }
  
  public void create() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(dataFile());
    if (!exists())
    {
      config.options().header("###########################################################\r\n################## FURNACE NOTIFY DATAS ###################\r\n###########################################################");
      

      for (String keys : defaultKeys().keySet()) {
        config.addDefault(keys, defaultKeys().get(keys));
      }
      config.options().copyDefaults(true);
      try {
        config.save(dataFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void reload() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(dataFile());
    if (exists()) {
      try {
        config.load(dataFile());
      } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void save() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(dataFile());
    if (exists()) {
      try {
        config.save(dataFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public FileConfiguration get() {
    return YamlConfiguration.loadConfiguration(dataFile());
  }
  
  public void set(String string, Object value) {
    FileConfiguration config = YamlConfiguration.loadConfiguration(dataFile());
    config.set(string, value);
    try {
      config.save(dataFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public String defaultMessage() {
    return "Message not found in config";
  }
  
  public String getString() {
    String msg = defaultMessage();
    String path = get().getString(this.msg);
    if ((path != null) && (exists())) {
      msg = path;
    }
    return msg;
  }
  
  public boolean worldDisabled(String world) {
    return get().getStringList("DisabledWorlds").contains(world.toLowerCase());
  }
  
  public boolean playerDisabled(Player p) {
    return get().getStringList("DisabledPlayers").contains(p.getName().toLowerCase());
  }
  
  public HashMap<String, Object> defaultKeys() {
    msgs.put("DisabledActionBarPlayers", Arrays.asList(new String[] { "Default" }));
    msgs.put("DisabledWorlds", Arrays.asList(new String[] { "world_the_end" }));
    msgs.put("DisabledPlayers", Arrays.asList(new String[] { "Default" }));
    return msgs;
  }
}

package me.furnace.Configs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config
{
  private String addkey;
  private String removekey;
  private Object keyvalue;
  private HashMap<String, Object> msgs = new HashMap();
  
  public Config() {}
  
  public Config(String removeKey)
  {
    removekey = removeKey;
  }
  
  public Config(String addKey, Object value) {
    addkey = addKey;
    keyvalue = value;
  }
  
  public File configFile() {
    return new File("plugins/FurnaceNotify/" + File.separator + "settings.yml");
  }
  
  public boolean exists() {
    return configFile().exists();
  }
  
  public void create() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile());
    if (!exists())
    {
      config.options().header("###########################################################\r\n################## FURNACE NOTIFY CONFIG ##################\r\n###########################################################");
      

      for (String keys : defaultKeys().keySet()) {
        config.addDefault(keys, defaultKeys().get(keys));
      }
      config.options().copyDefaults(true);
      try {
        config.save(configFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void save() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile());
    if (exists()) {
      try {
        config.save(configFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void reload() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile());
    if (exists()) {
      try {
        config.load(configFile());
      } catch (IOException|InvalidConfigurationException e) {
        e.printStackTrace();
      }
    }
  }
  
  public FileConfiguration get() {
    return YamlConfiguration.loadConfiguration(configFile());
  }
  
  public void addKey() {
    msgs.put(addkey, keyvalue);
  }
  
  public void removeKey() {
    msgs.remove(removekey);
  }
  
  public int getTitleFadeIn() {
    int stay = 30;
    String spath = get().getString("Title");
    String path = get().getString("Title.FadeIn");
    if ((spath != null) && (path != null) && (exists())) {
      int integer = get().getInt("Title.FadeIn");
      if (integer > 0) {
        stay = integer;
      }
    }
    return stay;
  }
  
  public int getTitleStay() {
    int stay = 30;
    String spath = get().getString("Title");
    String path = get().getString("Title.Stay");
    if ((spath != null) && (path != null) && (exists())) {
      int integer = get().getInt("Title.Stay");
      if (integer > 0) {
        stay = integer;
      }
    }
    return stay;
  }
  
  public int getTitleFadeOut() {
    int stay = 30;
    String spath = get().getString("Title");
    String path = get().getString("Title.FadeOut");
    if ((spath != null) && (path != null) && (exists())) {
      int integer = get().getInt("Title.FadeOut");
      if (integer > 0) {
        stay = integer;
      }
    }
    return stay;
  }
  
  public HashMap<String, Object> defaultKeys() {
    msgs.put("Support.PlotSquared", Boolean.valueOf(false));
    msgs.put("Support.Factions", Boolean.valueOf(false));
    msgs.put("Support.uSkyBlock", Boolean.valueOf(false));
    msgs.put("Cooldown.Enabled", Boolean.valueOf(true));
    msgs.put("Fireworks", Boolean.valueOf(true));
    msgs.put("Cooldown.Time", Integer.valueOf(50));
    msgs.put("Effect.Enabled", Boolean.valueOf(true));
    msgs.put("Effect.Type", "Flame");
    msgs.put("Title.FadeIn", Integer.valueOf(30));
    msgs.put("Title.Stay", Integer.valueOf(20));
    msgs.put("Title.FadeOut", Integer.valueOf(30));
    return msgs;
  }
}

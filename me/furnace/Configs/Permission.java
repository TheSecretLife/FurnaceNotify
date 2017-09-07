package me.furnace.Configs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

public class Permission
{
  private String perm;
  private HashMap<String, Object> msgs = new HashMap();
  
  public Permission() {}
  
  public Permission(String permission)
  {
    perm = permission;
  }
  
  public File permsFile() {
    return new File("plugins/FurnaceNotify/" + File.separator + "permissions.yml");
  }
  
  public boolean exists() {
    return permsFile().exists();
  }
  
  public void create() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(permsFile());
    if (!exists())
    {
      config.options().header("###########################################################\r\n################## FURNACE NOTIFY PERMISSIONS #############\r\n###########################################################");
      

      for (String keys : defaultKeys().keySet()) {
        config.addDefault(keys, defaultKeys().get(keys));
      }
      config.options().copyDefaults(true);
      try {
        config.save(permsFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void reload() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(permsFile());
    if (exists()) {
      try {
        config.load(permsFile());
      } catch (IOException|InvalidConfigurationException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void save() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(permsFile());
    if (exists()) {
      try {
        config.save(permsFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public FileConfiguration get() {
    return YamlConfiguration.loadConfiguration(permsFile());
  }
  
  public String getPermission() {
    String perm = "furnace." + this.perm;
    String permpath = get().getString("Permission");
    String path = get().getString("Permission." + this.perm);
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getdisableWorldPermission() {
    String perm = "furnace.disableworld";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.DisableWorld");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getdisablePlayerPermission() {
    String perm = "furnace.disableplayer";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.DisablePlayer");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getInfoPermission() {
    String perm = "furnace.info";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.InfoPlayer");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getIslandBypassPermission() {
    String perm = "furnace.skyblockbypass";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.SkyBlockBypass");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getPlotBypassPermission() {
    String perm = "furnace.plotbypass";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.PlotBypass");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getFactionBypassPermission() {
    String perm = "furnace.factionbypass";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.FactionBypass");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getCooldownBypassPermission() {
    String perm = "furnace.cooldownbypass";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.CooldownBypass");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getNotifyPermission() {
    String perm = "furnace.notify";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.Notify");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getTogglePermission() {
    String perm = "furnace.toggle";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.Toggle");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getToggleOthers() {
    String perm = "furnace.toggle.others";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.ToggleOthers");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getToggleBypass() {
    String perm = "furnace.toggle.bypass";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.ToggleBypass");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getToggleExempt() {
    String perm = "furnace.toggle.exempt";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.ToggleExempt");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public String getReloadPermission() {
    String perm = "furnace.reload";
    String permpath = get().getString("Permission");
    String path = get().getString("Permission.Reload");
    if ((permpath != null) && (path != null) && (exists())) {
      perm = path;
    }
    return perm;
  }
  
  public HashMap<String, Object> defaultKeys() {
    msgs.put("Permission.Reload", "furnace.reload");
    msgs.put("Permission.Notify", "furnace.notify");
    msgs.put("Permission.Toggle", "furnace.toggle");
    msgs.put("Permission.InfoPlayer", "furnace.info");
    msgs.put("Permission.CooldownBypass", "furnace.cooldownbypass");
    msgs.put("Permission.ToggleOthers", "furnace.toggle.others");
    msgs.put("Permission.ToggleExempt", "furnace.toggle.exempt");
    msgs.put("Permission.ToggleBypass", "furnace.toggle.bypass");
    msgs.put("Permission.DisableWorld", "furnace.disableworld");
    msgs.put("Permission.PlotBypass", "furnace.plotbypass");
    msgs.put("Permission.FactionBypass", "furnace.factionbypass");
    msgs.put("Permission.SkyBlockBypass", "furnace.skyblockbypass");
    return msgs;
  }
}

package me.furnace.Configs;

import java.io.File;
import java.io.IOException;
import me.furnace.Utils.Variables;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class IPlayer
{
  private Player p;
  private Messages message = new Messages();
  
  public IPlayer(Player player) {
    p = player;
  }
  
  public File playerFile() {
    return new File(
      "plugins/FurnaceNotify/" + File.separator + "data" + File.separator + p.getName() + ".yml");
  }
  
  public boolean exists() {
    return playerFile().exists();
  }
  
  public void createConfig() {
    if (!exists()) {
      FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile());
      config.addDefault("Name", p.getName());
      config.addDefault("PlayerDisabled", Boolean.valueOf(false));
      config.addDefault("ToggleDisabled", Boolean.valueOf(false));
      config.options().copyDefaults(true);
      try {
        config.save(playerFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void deleteConfig() {
    if (exists()) {
      playerFile().delete();
    }
  }
  
  public void saveConfig() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile());
    try {
      config.save(playerFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void reloadConfig() {
    if (exists()) {
      FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile());
      try {
        config.save(playerFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public FileConfiguration getConfig() {
    File playerfile = new File(
      "plugins/FurnaceNotify/" + File.separator + "data" + File.separator + p.getName() + ".yml");
    return YamlConfiguration.loadConfiguration(playerfile);
  }
  
  public String getStatus(String path) {
    String ans = "No";
    if (exists()) {
      String pathc = getConfig().getString(path);
      if (pathc != null) {
        boolean bool = getConfig().getBoolean(path);
        if (bool) {
          ans = "Yes";
        }
      }
    }
    return ans;
  }
  
  public void setPlayerDisabled(boolean bool) {
    FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile());
    config.set("PlayerDisabled", Boolean.valueOf(bool));
    try {
      config.save(playerFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void setToggleDisabled(boolean bool) {
    FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile());
    config.set("ToggleDisabled", Boolean.valueOf(bool));
    try {
      config.save(playerFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void sendInfoMessage(Player player)
  {
    for (String msg : message.getInfoMessages()) {
      player.sendMessage(new Variables(p, msg).applyChatVariables()
        .replace("%toggle_status%", getStatus("ToggleDisabled"))
        .replace("%player_status%", getStatus("PlayerDisabled")));
    }
  }
}

package me.furnace;

import java.util.HashMap;
import me.furnace.Commands.FurnaceCommand;
import me.furnace.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class Main extends org.bukkit.plugin.java.JavaPlugin
{
  private int task;
  public static Plugin instance;
  public static HashMap<java.util.UUID, Long> cooldown;
  
  public Main() {}
  
  private Utils utils = new Utils(Bukkit.getServer().getConsoleSender(), task, this);
  
  public void onEnable() {
    instance = this;
    utils.enablePlugin();
    cooldown = new HashMap();
    getCommand("furnace").setExecutor(new FurnaceCommand());
  }
  
  public void onDisable() {
    utils.disablePlugin();
  }
  
  public static Plugin getInstance() {
    return instance;
  }
}

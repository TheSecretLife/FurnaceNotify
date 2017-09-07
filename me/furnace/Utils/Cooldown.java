package me.furnace.Utils;

import java.util.HashMap;
import java.util.UUID;
import me.furnace.Configs.Config;
import me.furnace.Configs.Messages;
import me.furnace.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

public class Cooldown
{
  private UUID uuid;
  private org.bukkit.plugin.Plugin plugin;
  private int cooltask;
  private Utils util = new Utils();
  private Config config = new Config();
  private Messages message = new Messages();
  
  public Cooldown() {}
  
  public Cooldown(org.bukkit.plugin.Plugin plg)
  {
    plugin = plg;
  }
  
  public Cooldown(UUID uuid) {
    this.uuid = uuid;
  }
  
  public boolean isEnabled() {
    boolean bool = false;
    String coolpath = config.get().getString("Cooldown");
    String path = config.get().getString("Cooldown.Enabled");
    if ((coolpath != null) && (path != null) && (config.exists())) {
      bool = config.get().getBoolean("Cooldown.Enabled");
    }
    return bool;
  }
  
  public int getCooldownTime() {
    int cooldown = 0;
    String coolpath = config.get().getString("Cooldown");
    String path = config.get().getString("Cooldown.Time");
    if ((coolpath != null) && (path != null) && (config.exists())) {
      int string = config.get().getInt("Cooldown.Time");
      if (string >= 1) {
        cooldown = string;
      }
    }
    return cooldown;
  }
  
  public long getEndTime() {
    long end = -1L;
    if (contains()) {
      end = ((Long)Main.cooldown.get(uuid)).longValue();
    }
    return end;
  }
  
  public boolean contains() {
    boolean bool = false;
    if (Main.cooldown.containsKey(uuid)) {
      bool = true;
    }
    return bool;
  }
  
  public void add() {
    long current = System.currentTimeMillis();
    long end = current + getCooldownTime() * 1 * 1000L;
    Main.cooldown.put(uuid, Long.valueOf(end));
  }
  
  public void remove() {
    Main.cooldown.remove(uuid);
  }
  
  public boolean running() {
    boolean bool = false;
    if (Bukkit.getServer().getScheduler().isCurrentlyRunning(cooltask)) {
      bool = true;
    }
    return bool;
  }
  
  public void start() {
    try {
      cooltask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
      {
        public void run() {
          for (UUID uuid : Main.cooldown.keySet()) {
            if (Main.cooldown.size() != 0) {
              Cooldown cooldown = new Cooldown(uuid);
              Long current = Long.valueOf(System.currentTimeMillis());
              Long end = Long.valueOf(cooldown.getEndTime());
              if ((end.longValue() < current.longValue()) && (end.longValue() != -1L)) {
                cooldown.remove();
                if (Bukkit.getPlayer(uuid) != null) {
                  org.bukkit.entity.Player p = Bukkit.getPlayer(uuid);
                  p.sendMessage(util.color(
                    new Variables(p, message.getCooldownOverMessage()).applyChatVariables()));
                }
              }
            }
          }
        }
      }, Long.valueOf(1L).longValue() * 20L, Long.valueOf(5L).longValue() * 20L);
    } catch (NullPointerException e) {
      stop();
    }
  }
  
  public void stop() {
    if (running()) {
      Bukkit.getServer().getScheduler().cancelTask(cooltask);
    }
  }
  
  public String getRemainingTime() {
    String remainingTime = "";
    long current = System.currentTimeMillis();
    long end = getEndTime();
    long difference = end - current;
    if (end == -1L) {
      return "&cPERMANENT";
    }
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    int days = 0;
    int weeks = 0;
    while (difference >= 1000L) {
      difference -= 1000L;
      seconds++;
    }
    while (seconds >= 60) {
      seconds -= 60;
      minutes++;
    }
    while (minutes >= 60) {
      minutes -= 60;
      hours++;
    }
    while (hours >= 24) {
      hours -= 24;
      days++;
    }
    while (days >= 7) {
      days -= 7;
      weeks++;
    }
    if (weeks == 0) {
      remainingTime = days + " d, " + hours + " h, " + minutes + " min and " + seconds + " secs.";
    } else {
      remainingTime = 
        weeks + " w, " + days + " d, " + hours + " h, " + minutes + " min and " + seconds + " seconds";
    }
    if (days == 0) {
      remainingTime = hours + " h, " + minutes + " min and " + seconds + " seconds";
    }
    if (hours == 0) {
      remainingTime = minutes + " min and " + seconds + " seconds";
    }
    if (minutes == 0) {
      remainingTime = seconds + " seconds";
    }
    return remainingTime;
  }
}

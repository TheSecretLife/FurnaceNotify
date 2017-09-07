package me.furnace.Utils;

import java.io.File;
import java.util.logging.Logger;
import me.furnace.API;
import me.furnace.Configs.Config;
import me.furnace.Configs.Data;
import me.furnace.Configs.Messages;
import me.furnace.Configs.Permission;
import me.furnace.Events.PlayerJoin;
import me.furnace.Support.Factions;
import me.furnace.Support.PlotSquared;
import me.furnace.Support.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Furnace;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public class Utils
{
  private API api;
  private int taskid;
  private Player p;
  private Plugin plugin;
  private Data data = new Data();
  private ConsoleCommandSender console;
  private Config config = new Config();
  private Messages messages = new Messages();
  private Permission perms = new Permission();
  private Logger log = Logger.getLogger("Minecraft");
  private String version = Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", 
    "");
  
  public Utils() {}
  
  public Utils(Player player)
  {
    p = player;
  }
  
  public Utils(Player player, Furnace furnace) {
    p = player;
  }
  
  public Utils(ConsoleCommandSender sender, int TaskID, Plugin plugin) {
    console = sender;
    this.plugin = plugin;
    taskid = TaskID;
  }
  
  public String color(String msg) {
    return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', msg);
  }
  
  public String getAuthor() {
    return plugin.getDescription().getAuthors().toString().replace("[", "").replace("]", "").replace("-", "");
  }
  
  public String pluginVersion() {
    return plugin.getDescription().getVersion();
  }
  
  public boolean fireworksEnabled() {
    String path = config.get().getString("Fireworks");
    if (path != null) {
      return config.get().getBoolean("Fireworks");
    }
    return false;
  }
  
  public void spawnFirework(boolean check) {
    if (check) {
      if (fireworksEnabled()) {
        Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffects(new FireworkEffect[] {FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).withColor(Color.GREEN)
          .withColor(Color.RED).withColor(Color.OLIVE).withColor(Color.YELLOW).withFade(Color.BLACK)
          .withFade(Color.BLUE).withFade(Color.FUCHSIA).withFade(Color.PURPLE).withColor(Color.SILVER)
          .withFlicker().withTrail().build() });
        meta.setPower(1);
        firework.setFireworkMeta(meta);
      }
    } else {
      Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
      FireworkMeta meta = firework.getFireworkMeta();
      meta.addEffects(new FireworkEffect[] {FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).withColor(Color.GREEN)
        .withColor(Color.RED).withColor(Color.OLIVE).withColor(Color.YELLOW).withFade(Color.BLACK)
        .withFade(Color.BLUE).withFade(Color.FUCHSIA).withFade(Color.PURPLE).withColor(Color.SILVER)
        .withFlicker().withTrail().build() });
      meta.setPower(1);
      firework.setFireworkMeta(meta);
    }
  }
  
  public void createFolder() {
    File file;
    if (!(file = new File("plugins/FurnaceNotify/" + File.separator, "data")).exists()) {
      try {
        if (file.mkdir()) {
          log.info("Created userdata directory.");
        }
        return;
      } catch (Exception e) {
        log.severe("Failed to make userdata directory!");
        log.severe(e.getMessage());
      }
    }
  }
  
  public String versionName() {
    String vers = "";
    if (version.equalsIgnoreCase("v1_12_R1")) {
      vers = "1.12";
    } else if (version.equalsIgnoreCase("v1_11_R1")) {
      vers = "1.11";
    } else if (version.equalsIgnoreCase("v1_10_R1")) {
      vers = "1.10.1";
    } else if (version.equalsIgnoreCase("v1_9_R2")) {
      vers = "1.9.2";
    } else if (version.equalsIgnoreCase("v1_9_R1")) {
      vers = "1.9";
    } else if (version.equalsIgnoreCase("v1_8_R3")) {
      vers = "1.8";
    } else if (version.equalsIgnoreCase("v1_8_R2")) {
      vers = "1.8";
    } else if (version.equalsIgnoreCase("v1_8_R1")) {
      vers = "1.8";
    } else {
      vers = "&c&kii&cUnknown Version!&c&kii";
    }
    return vers;
  }
  
  public void enablePlugin() {
    Factions factions = new Factions();
    SkyBlock skyblock = new SkyBlock();
    PlotSquared plots = new PlotSquared();
    Cooldown cooldown = new Cooldown(plugin);
    
    createFolder();
    cooldown.start();
    config.create();
    config.save();
    data.create();
    data.save();
    messages.create();
    messages.save();
    perms.create();
    perms.save();
    new Scheduler(plugin, taskid).start();
    PluginManager manager = plugin.getServer().getPluginManager();
    manager.registerEvents(new me.furnace.Events.DisableWorldGUI(), plugin);
    manager.registerEvents(new me.furnace.Events.DisablePlayerGUI(), plugin);
    manager.registerEvents(new PlayerJoin(), plugin);
    
    console.sendMessage(color("&8-=-=-=-=-=-=-=-"));
    console.sendMessage(color("&b&kii &e&lFurnaceNotify &cv" + pluginVersion() + " &b&kii"));
    console.sendMessage(color("&8"));
    console.sendMessage(color("&7&o> &3Author: &a" + getAuthor()));
    console.sendMessage(color("&7&o> &3Bukkit Version: &5" + versionName()));
    if ((plots.support()) && (plots.plugin())) {
      console.sendMessage(color("&7&o> &3PlotSquared: &2Support Enabled, plugin found!"));
    } else if ((!plots.support()) && (plots.plugin())) {
      console.sendMessage(color("&7&o> &3PlotSquared: &cSupport Disabled, support is disabled in config!"));
    } else {
      console.sendMessage(color("&7&o> &3PlotSquared: &cSupport Disabled, plugin not found!"));
    }
    if ((factions.support()) && (factions.plugin())) {
      console.sendMessage(color("&7&o> &3Factions: &2Support Enabled, plugin found!"));
    } else if ((!factions.support()) && (factions.plugin())) {
      console.sendMessage(color("&7&o> &3Factions: &cSupport Disabled, support is disabled in config!"));
    } else {
      console.sendMessage(color("&7&o> &3Factions: &cSupport Disabled, plugin not found!"));
    }
    if ((skyblock.support()) && (skyblock.plugin())) {
      console.sendMessage(color("&7&o> &3uSkyBlock: &2Support Enabled, plugin found!"));
    } else if ((!skyblock.support()) && (skyblock.plugin())) {
      console.sendMessage(color("&7&o> &3uSkyBlock: &cSupport Disabled, support is disabled in config!"));
    } else {
      console.sendMessage(color("&7&o> &3uSkyBlock: &cSupport Disabled, plugin not found!"));
    }
    console.sendMessage(color("&8"));
    console.sendMessage(color("&6Plugin Enabled, Thanks for using this plugin! :)"));
    console.sendMessage(color("&8-=-=-=-=-=-=-=-"));
  }
  
  public void disablePlugin() {
    Factions factions = new Factions();
    SkyBlock skyblock = new SkyBlock();
    PlotSquared plots = new PlotSquared();
    Cooldown cooldown = new Cooldown(plugin);
    
    createFolder();
    cooldown.stop();
    config.create();
    config.save();
    data.create();
    data.save();
    messages.create();
    messages.save();
    perms.create();
    perms.save();
    new Scheduler(plugin, taskid).stop();
    
    console.sendMessage(color("&8-=-=-=-=-=-=-=-"));
    console.sendMessage(color("&bii &e&lFurnaceNotify &cv" + pluginVersion() + " &bii"));
    console.sendMessage(color("&8"));
    console.sendMessage(color("&7&o> &3Author: &a" + getAuthor()));
    console.sendMessage(color("&7&o> &3Bukkit Version: &5" + versionName()));
    if ((plots.support()) && (plots.plugin())) {
      console.sendMessage(color("&7&o> &3PlotSquared: &2Support Enabled, plugin found!"));
    } else if ((!plots.support()) && (plots.plugin())) {
      console.sendMessage(color("&7&o> &3PlotSquared: &cSupport Disabled, support is disabled in config!"));
    } else {
      console.sendMessage(color("&7&o> &3PlotSquared: &cSupport Disabled, plugin not found!"));
    }
    if ((factions.support()) && (factions.plugin())) {
      console.sendMessage(color("&7&o> &3Factions: &2Support Enabled, plugin found!"));
    } else if ((!factions.support()) && (factions.plugin())) {
      console.sendMessage(color("&7&o> &3Factions: &cSupport Disabled, support is disabled in config!"));
    } else {
      console.sendMessage(color("&7&o> &3Factions: &cSupport Disabled, plugin not found!"));
    }
    if ((skyblock.support()) && (skyblock.plugin())) {
      console.sendMessage(color("&7&o> &3uSkyBlock: &2Support Enabled, plugin found!"));
    } else if ((!skyblock.support()) && (skyblock.plugin())) {
      console.sendMessage(color("&7&o> &3uSkyBlock: &cSupport Disabled, support is disabled in config!"));
    } else {
      console.sendMessage(color("&7&o> &3uSkyBlock: &cSupport Disabled, plugin not found!"));
    }
    console.sendMessage(color("&8"));
    console.sendMessage(color("&4Plugin Disabled, Thanks for using this plugin! :)"));
    console.sendMessage(color("&8-=-=-=-=-=-=-=-"));
  }
  
  public API getAPI() {
    try {
      if (version.equalsIgnoreCase("v1_12_R1")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_12_R1").newInstance());
      } else if (version.equalsIgnoreCase("v1_11_R1")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_11_R1").newInstance());
      } else if (version.equalsIgnoreCase("v1_10_R1")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_10_R1").newInstance());
      } else if (version.equalsIgnoreCase("v1_9_R2")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_9_R2").newInstance());
      } else if (version.equalsIgnoreCase("v1_9_R1")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_9_R1").newInstance());
      } else if (version.equalsIgnoreCase("v1_8_R3")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_8_R3").newInstance());
      } else if (version.equalsIgnoreCase("v1_8_R2")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_8_R2").newInstance());
      } else if (version.equalsIgnoreCase("v1_8_R1")) {
        api = ((API)Class.forName("me.furnace.Versions.v1_8_R1").newInstance());
      } else {
        Bukkit.getConsoleSender().sendMessage(color(
          "[FurnaceNotify] &cThe plugin don't support this version, you must use 1.12, 1.11, 1.10, 1.9 or 1.8 version, please restart the server and change the version!"));
        Bukkit.getServer().savePlayers();
      }
    } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
      e.printStackTrace();
    }
    return api;
  }
}

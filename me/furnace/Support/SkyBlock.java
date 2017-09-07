package me.furnace.Support;

import me.furnace.Configs.Config;
import me.furnace.Configs.Permission;
import org.bukkit.Server;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import us.talabrek.ultimateskyblock.api.IslandInfo;
import us.talabrek.ultimateskyblock.uSkyBlock;

public class SkyBlock
{
  private Player p;
  private Furnace furnace;
  private Config config = new Config();
  private Permission perm = new Permission();
  
  public SkyBlock() {}
  
  public SkyBlock(Player player, Furnace furnace)
  {
    p = player;
    this.furnace = furnace;
  }
  
  public boolean support() {
    boolean bool = false;
    String supportpath = config.get().getString("Support");
    String path = config.get().getString("Support.uSkyBlock");
    if ((supportpath != null) && (path != null) && (config.exists())) {
      bool = config.get().getBoolean("Support.uSkyBlock");
    }
    return bool;
  }
  
  public boolean plugin() {
    boolean bool = false;
    Plugin skyblock = org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("uSkyBlock");
    if (skyblock != null) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isSkyWorld() {
    boolean bool = false;
    uSkyBlock plugin = uSkyBlock.getInstance();
    org.bukkit.World world = furnace.getWorld();
    if ((plugin.isSkyWorld(world)) || (plugin.isSkyNether(world))) {
      bool = true;
    }
    return bool;
  }
  
  public IslandInfo getIsland() {
    uSkyBlock plugin = uSkyBlock.getInstance();
    IslandInfo info = plugin.getIslandInfo(furnace.getLocation());
    return info;
  }
  
  public boolean isallowed() {
    boolean bool = false;
    IslandInfo island = getIsland();
    if ((island.getMembers().contains(p.getName())) || (island.getLeader().equalsIgnoreCase(p.getName())) || 
      (island.getTrustees().contains(p.getName())) || 
      (p.hasPermission(perm.getIslandBypassPermission()))) {
      bool = true;
    }
    return bool;
  }
}

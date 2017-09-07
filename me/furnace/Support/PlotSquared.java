package me.furnace.Support;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.plotsquared.bukkit.util.BukkitUtil;
import me.furnace.Configs.Config;
import me.furnace.Configs.Permission;
import org.bukkit.Server;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PlotSquared
{
  private Player p;
  private Furnace furnace;
  private Config config = new Config();
  private Permission perm = new Permission();
  
  public PlotSquared() {}
  
  public PlotSquared(Player player, Furnace furnace)
  {
    p = player;
    this.furnace = furnace;
  }
  
  public boolean support() {
    boolean bool = false;
    String supportpath = config.get().getString("Support");
    String path = config.get().getString("Support.PlotSquared");
    if ((supportpath != null) && (path != null) && (config.exists())) {
      bool = config.get().getBoolean("Support.PlotSquared");
    }
    return bool;
  }
  
  public boolean plugin() {
    boolean bool = false;
    Plugin plotsquared = org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("PlotSquared");
    if (plotsquared != null) {
      bool = true;
    }
    return bool;
  }
  
  public boolean hasOwner() {
    return getPlot().hasOwner();
  }
  
  public boolean isPlotWorld()
  {
    PlotAPI plotapi = new PlotAPI();
    return plotapi.isPlotWorld(furnace.getWorld());
  }
  
  public Plot getPlot() {
    if (furnace.getLocation() == null) {
      return null;
    }
    return BukkitUtil.getLocation(furnace.getLocation()).getPlot();
  }
  
  public boolean isallowed() {
    boolean bool = false;
    Plot plot = getPlot();
    if ((plot.getMembers().contains(p.getUniqueId())) || (plot.getOwners().contains(p.getUniqueId())) || 
      (p.hasPermission(perm.getPlotBypassPermission()))) {
      bool = true;
    }
    return bool;
  }
}

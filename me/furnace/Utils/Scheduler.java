package me.furnace.Utils;

import me.furnace.Blocks.FurnaceBlock;
import me.furnace.Configs.Data;
import me.furnace.Configs.Permission;
import me.furnace.Methods.Toggle;
import me.furnace.Support.Factions;
import me.furnace.Support.PlotSquared;
import me.furnace.Support.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Scheduler
{
  private Plugin plugin;
  private int task;
  private Data dataconfig = new Data();
  private Permission perm = new Permission();
  
  public Scheduler(int taskid) {
    task = taskid;
  }
  
  public Scheduler(Plugin plg, int taskid) {
    task = taskid;
    plugin = plg;
  }
  
  public boolean running() {
    boolean bool = false;
    if (Bukkit.getServer().getScheduler().isCurrentlyRunning(task)) {
      bool = true;
    }
    return bool;
  }
  
  public void start() {
    try {
      task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
        public void run() {
          for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Toggle toggle = new Toggle(p);
            Block b = p.getTargetBlock(null, 5);
            
            if (b.getType() == org.bukkit.Material.BURNING_FURNACE) {
              Furnace f = (Furnace)b.getState();
              String worldname = p.getWorld().getName();
              FurnaceBlock furnace = new FurnaceBlock(p, f);
              PlotSquared plotsquared = new PlotSquared(p, f);
              Factions factions = new Factions(p, f);
              SkyBlock skyblock = new SkyBlock(p, f);
              if ((p.hasPermission(perm.getNotifyPermission())) && 
                (!dataconfig.worldDisabled(worldname)) && (!dataconfig.playerDisabled(p)) && 
                (!toggle.isDisabled()) && 
                (furnace.getSmeltingItem() != null) && (furnace.getFuelItem() != null) && 
                (furnace.getProgress() > 0)) {
                if ((plotsquared.support()) && (plotsquared.plugin()) && 
                  (plotsquared.isPlotWorld()) && (plotsquared.getPlot() != null) && 
                  (plotsquared.hasOwner())) {
                  if (plotsquared.isallowed()) {
                    furnace.sendBurnMessage();
                  }
                }
                else if ((skyblock.support()) && (skyblock.plugin()) && 
                  (skyblock.isSkyWorld()) && (skyblock.getIsland() != null)) {
                  if (skyblock.isallowed()) {
                    furnace.sendBurnMessage();
                  }
                }
                else if ((factions.support()) && (factions.plugin()) && 
                  (factions.getFaction() != null)) {
                  if (factions.isallowed()) {
                    furnace.sendBurnMessage();
                  }
                }
                else {
                  furnace.sendBurnMessage();
                  return;
                }
                
              }
              
            }
          }
        }
      }, Long.valueOf(1L).longValue() * 20L, 3L);
    }
    catch (NullPointerException|IllegalStateException localNullPointerException) {}
  }
  
  public void stop() {
    if (running()) {
      Bukkit.getServer().getScheduler().cancelTask(task);
    }
  }
}

package me.furnace.Support;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import me.furnace.Configs.Config;
import me.furnace.Configs.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Factions
{
  private Player p;
  private Furnace furnace;
  private Config config = new Config();
  private Permission perm = new Permission();
  
  public Factions() {}
  
  public Factions(Player player, Furnace furnace)
  {
    p = player;
    this.furnace = furnace;
  }
  
  public boolean support() {
    boolean bool = false;
    String supportpath = config.get().getString("Support");
    String path = config.get().getString("Support.Factions");
    if ((supportpath != null) && (path != null) && (config.exists())) {
      bool = config.get().getBoolean("Support.Factions");
    }
    return bool;
  }
  
  public boolean plugin() {
    boolean bool = false;
    Plugin factions = Bukkit.getServer().getPluginManager().getPlugin("Factions");
    Plugin mcore = Bukkit.getServer().getPluginManager().getPlugin("MassiveCore");
    if ((factions != null) && (mcore != null)) {
      bool = true;
    }
    return bool;
  }
  
  public Faction getFaction() {
    return BoardColl.get().getFactionAt(com.massivecraft.massivecore.ps.PS.valueOf(furnace.getLocation()));
  }
  
  public boolean isallowed() {
    boolean bool = false;
    Faction playerfaction = MPlayer.get(p).getFaction();
    if ((getFaction().equals(playerfaction)) || (p.hasPermission(perm.getFactionBypassPermission()))) {
      bool = true;
    }
    return bool;
  }
}

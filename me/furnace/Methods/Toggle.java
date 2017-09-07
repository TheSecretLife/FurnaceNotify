package me.furnace.Methods;

import java.util.List;
import me.furnace.Configs.Data;
import me.furnace.Configs.IPlayer;
import me.furnace.Configs.Permission;
import me.furnace.Utils.Cooldown;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Toggle
{
  private Player p;
  private Data dataconfig = new Data();
  private Permission perm = new Permission();
  private Cooldown cooldown1 = new Cooldown();
  
  public Toggle(Player player) {
    p = player;
  }
  
  public List<String> list() {
    return dataconfig.get().getStringList("DisabledActionBarPlayers");
  }
  
  public boolean isDisabled() {
    return list().contains(p.getName().toLowerCase());
  }
  
  public boolean contains() {
    boolean bool = false;
    String path = dataconfig.get().getString("DisabledActionBarPlayers");
    if ((path != null) && (dataconfig.exists()) && 
      (isDisabled())) {
      bool = true;
    }
    
    return bool;
  }
  
  public void add() {
    IPlayer playerconfig = new IPlayer(p);
    String playername = p.getName().toLowerCase();
    Cooldown cooldown = new Cooldown(p.getUniqueId());
    
    List<String> stringList = list();
    if (!stringList.contains(playername)) {
      stringList.add(playername);
    }
    if ((cooldown1.isEnabled()) && (!p.hasPermission(perm.getCooldownBypassPermission()))) {
      cooldown.add();
    }
    dataconfig.set("DisabledActionBarPlayers", stringList);
    dataconfig.save();
    playerconfig.setToggleDisabled(true);
    playerconfig.saveConfig();
  }
  
  public void remove() {
    IPlayer playerconfig = new IPlayer(p);
    String playername = p.getName().toLowerCase();
    Cooldown cooldown = new Cooldown(p.getUniqueId());
    
    List<String> stringList = list();
    if (stringList.contains(playername)) {
      stringList.remove(playername);
    }
    if (cooldown.contains()) {
      cooldown.remove();
    }
    dataconfig.set("DisabledActionBarPlayers", stringList);
    dataconfig.save();
    playerconfig.setToggleDisabled(false);
    playerconfig.saveConfig();
  }
}

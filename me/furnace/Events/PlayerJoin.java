package me.furnace.Events;

import me.furnace.Configs.IPlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements org.bukkit.event.Listener
{
  public PlayerJoin() {}
  
  @EventHandler
  public void onJoin(final PlayerJoinEvent e)
  {
    org.bukkit.Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(me.furnace.Main.getInstance(), new Runnable() {
      public void run() {
        Player p = e.getPlayer();
        IPlayer pconfig = new IPlayer(p);
        
        pconfig.createConfig();
        pconfig.saveConfig();
      }
    }, 20L);
  }
  
  @EventHandler
  public void onLeave(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    IPlayer playerconfig = new IPlayer(p);
    
    playerconfig.createConfig();
    playerconfig.saveConfig();
  }
}

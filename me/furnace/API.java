package me.furnace;

import org.bukkit.entity.Player;

public abstract interface API
{
  public abstract String translate(String paramString);
  
  public abstract void sendActionBar(Player paramPlayer, String paramString);
  
  public abstract void sendTitle(Player paramPlayer, String paramString1, String paramString2);
}

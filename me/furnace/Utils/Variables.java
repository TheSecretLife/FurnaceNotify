package me.furnace.Utils;

import me.furnace.API;
import me.furnace.Configs.Messages;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;

public class Variables
{
  private Player p;
  private String msg;
  private Furnace furnace;
  private Utils util = new Utils();
  
  public Variables(Player player, String message) {
    p = player;
    msg = message;
  }
  
  public Variables(Player player, String message, Furnace furnace) {
    msg = message;
    this.furnace = furnace;
    p = player;
  }
  
  public String applyChatVariables()
  {
    return util.getAPI().translate(me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, msg.replace("%prefix%", new Messages().getChatPrefixMessage())));
  }
  
  public String applyFurnaceVariables()
  {
    int cooktime = furnace.getCookTime() / 2;
    ItemStack fuel = furnace.getInventory().getFuel();
    ItemStack smelt = furnace.getInventory().getSmelting();
    
    return util.getAPI().translate(me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, msg.replace("%prefix%", new Messages().getChatPrefixMessage())
      .replace("%cook_time%", String.valueOf(cooktime))
      .replace("%smelt_item_amount%", String.valueOf(smelt.getAmount()))
      .replace("%smelt_item%", new Items(smelt).getName())
      .replace("%fuel%", new Items(fuel).getName())
      .replace("%fuel_amount%", String.valueOf(fuel.getAmount()))));
  }
}

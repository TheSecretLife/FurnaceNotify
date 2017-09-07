package me.furnace.Blocks;

import me.furnace.Configs.Config;
import me.furnace.Configs.Messages;
import me.furnace.Utils.Utils;
import me.furnace.Utils.Variables;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;

public class FurnaceBlock
{
  private Player p;
  private ItemStack stack;
  private Furnace furnace;
  private Utils util = new Utils();
  private Config config = new Config();
  
  public FurnaceBlock(Player player, Furnace furnace) {
    this.furnace = furnace;
    p = player;
  }
  
  public FurnaceBlock(Player player, Furnace furnace, ItemStack resultfuelsmelting) {
    this.furnace = furnace;
    p = player;
    stack = resultfuelsmelting;
  }
  
  public Location getLocation() {
    return furnace.getLocation();
  }
  
  public FurnaceInventory getInventory() {
    return furnace.getInventory();
  }
  
  public void setProgress(short time) {
    furnace.setCookTime(time);
  }
  
  public int getProgress() {
    return furnace.getCookTime() / 2;
  }
  
  public void setFuelItem() {
    furnace.getInventory().setFuel(stack);
  }
  
  public ItemStack getFuelItem() {
    return furnace.getInventory().getFuel();
  }
  
  public void setSmeltingItem() {
    furnace.getInventory().setSmelting(stack);
  }
  
  public ItemStack getSmeltingItem() {
    return furnace.getInventory().getSmelting();
  }
  
  public void setResultItem() {
    furnace.getInventory().setResult(stack);
  }
  
  public ItemStack getResultItem() {
    return furnace.getInventory().getResult();
  }
  
  public boolean isEffectEnabled() {
    boolean bool = false;
    String effpath = config.get().getString("Effect");
    String path = config.get().getString("Effect.Enabled");
    if ((effpath != null) && (path != null) && (config.exists())) {
      return config.get().getBoolean("Effect.Enabled");
    }
    return bool;
  }
  
  private void playFurnaceEffect(Effect effect, int id)
  {
    p.playEffect(furnace.getLocation().add(0.5D, 1.0D, 0.5D), Effect.FLAME, id);
  }
  
  public void sendActionBar(String msg) {
    util.getAPI().sendActionBar(p, 
      new Variables(p, new Messages(msg).getProgressMessage(), furnace).applyFurnaceVariables());
  }
  
  public void playEffect(int id)
  {
    String effpath = config.get().getString("Effect");
    String path = config.get().getString("Effect.Type");
    if ((effpath != null) && (path != null) && (config.exists())) {
      String effect = config.get().getString("Effect.Type");
      if (effect.equalsIgnoreCase("Flame")) {
        playFurnaceEffect(Effect.FLAME, id);
      } else if (effect.equalsIgnoreCase("Happy")) {
        playFurnaceEffect(Effect.HAPPY_VILLAGER, id);
      } else if (effect.equalsIgnoreCase("Smoke")) {
        playFurnaceEffect(Effect.SMOKE, id);
      } else if (effect.equalsIgnoreCase("Dust")) {
        playFurnaceEffect(Effect.COLOURED_DUST, id);
      } else if (effect.equalsIgnoreCase("Heart")) {
        playFurnaceEffect(Effect.HEART, id);
      } else if (effect.equalsIgnoreCase("Portal")) {
        playFurnaceEffect(Effect.PORTAL, id);
      } else if (effect.equalsIgnoreCase("Notes")) {
        playFurnaceEffect(Effect.NOTE, id);
      } else if (effect.equalsIgnoreCase("Cloud")) {
        playFurnaceEffect(Effect.CLOUD, id);
      } else if (effect.equalsIgnoreCase("Snow")) {
        playFurnaceEffect(Effect.SNOW_SHOVEL, id);
      } else {
        org.bukkit.Bukkit.getServer().getConsoleSender().sendMessage(util.color(
          "&eFurnaceNotify &8> &cUnknown Effect, avaible effects are: notes, snow, cloud, portal, heart, coloureddust, smoke, happy, flame."));
      }
    } else {
      playFurnaceEffect(Effect.FLAME, id);
    }
  }
  
  public void sendBurnMessage() {
    int cooktime = getProgress();
    if (cooktime < 10) {
      sendActionBar("below10");
      return; }
    if ((cooktime == 10) || (cooktime == 11) || (cooktime == 12) || (cooktime == 13) || (cooktime == 14) || 
      (cooktime == 15) || (cooktime == 16) || (cooktime == 17) || (cooktime == 18) || (cooktime == 19)) {
      sendActionBar("10");
      return; }
    if ((cooktime == 20) || (cooktime == 21) || (cooktime == 22) || (cooktime == 23) || (cooktime == 24) || 
      (cooktime == 25) || (cooktime == 26) || (cooktime == 27) || (cooktime == 28) || (cooktime == 29)) {
      sendActionBar("20");
      return; }
    if ((cooktime == 30) || (cooktime == 31) || (cooktime == 32) || (cooktime == 33) || (cooktime == 34) || 
      (cooktime == 35) || (cooktime == 36) || (cooktime == 37) || (cooktime == 38) || (cooktime == 39)) {
      sendActionBar("30");
      return; }
    if ((cooktime == 40) || (cooktime == 41) || (cooktime == 42) || (cooktime == 43) || (cooktime == 44) || 
      (cooktime == 45) || (cooktime == 46) || (cooktime == 47) || (cooktime == 48) || (cooktime == 49)) {
      sendActionBar("40");
      return; }
    if ((cooktime == 50) || (cooktime == 51) || (cooktime == 52) || (cooktime == 53) || (cooktime == 54) || 
      (cooktime == 55) || (cooktime == 56) || (cooktime == 57) || (cooktime == 58) || (cooktime == 59)) {
      sendActionBar("50");
      return; }
    if ((cooktime == 60) || (cooktime == 61) || (cooktime == 62) || (cooktime == 63) || (cooktime == 64) || 
      (cooktime == 65) || (cooktime == 66) || (cooktime == 67) || (cooktime == 68) || (cooktime == 69)) {
      sendActionBar("60");
      return; }
    if ((cooktime == 70) || (cooktime == 71) || (cooktime == 72) || (cooktime == 73) || (cooktime == 74) || 
      (cooktime == 75) || (cooktime == 76) || (cooktime == 77) || (cooktime == 78) || (cooktime == 79)) {
      sendActionBar("70");
      return; }
    if ((cooktime == 80) || (cooktime == 81) || (cooktime == 82) || (cooktime == 83) || (cooktime == 84) || 
      (cooktime == 85) || (cooktime == 86) || (cooktime == 87) || (cooktime == 88) || (cooktime == 89)) {
      sendActionBar("80");
      return; }
    if ((cooktime == 90) || (cooktime == 91) || (cooktime == 92) || (cooktime == 93) || (cooktime == 94) || 
      (cooktime == 95) || (cooktime == 96) || (cooktime == 97)) {
      sendActionBar("90");
      return; }
    if (cooktime == 98) {
      sendActionBar("100");
      if (isEffectEnabled()) {
        for (int id = 0; id < 30; id++) {
          playEffect(id);
        }
      }
      return;
    }
  }
}

package me.furnace.Methods;

import java.util.ArrayList;
import java.util.List;
import me.furnace.Configs.Data;
import me.furnace.Configs.Messages;
import me.furnace.Utils.Inv;
import me.furnace.Utils.Utils;
import me.furnace.Utils.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DisablePlayer
{
  private Player p;
  private Inventory gui;
  private String playername;
  private Utils util = new Utils();
  private Data dataconfig = new Data();
  private Messages message = new Messages();
  
  public DisablePlayer() {}
  
  public DisablePlayer(Player player)
  {
    p = player;
  }
  
  public DisablePlayer(String playername) {
    this.playername = playername;
  }
  
  public String defaultMessage() {
    return "Message not found in config";
  }
  
  public String getStatus(Player player) {
    String stat = "&cDisabled";
    if (!dataconfig.playerDisabled(player)) {
      stat = "&aEnabled";
    }
    return stat;
  }
  
  public String getGUIName() {
    String message = defaultMessage();
    String pathgui = this.message.get().getString("Messages.DisablePlayerGUI");
    String path = this.message.get().getString("Messages.DisablePlayerGUI.GUIName");
    if ((pathgui != null) && (path != null) && (this.message.exists())) {
      message = path;
    }
    return message;
  }
  
  public String getItemName() {
    String message = defaultMessage();
    String pathgui = this.message.get().getString("Messages.DisablePlayerGUI");
    String path = this.message.get().getString("Messages.DisablePlayerGUI.ItemName");
    if ((pathgui != null) && (path != null) && (this.message.exists())) {
      message = path;
    }
    return message;
  }
  
  public List<String> getItemLores() {
    List<String> message = new ArrayList();
    String pathgui = this.message.get().getString("Messages.DisablePlayerGUI");
    String path = this.message.get().getString("Messages.DisablePlayerGUI.ItemLores");
    if ((pathgui != null) && (path != null) && (this.message.exists())) {
      return this.message.get().getStringList("Messages.DisablePlayerGUI.ItemLores");
    }
    return message;
  }
  
  public short getGlassColor() {
    short color = 0;
    String pathgui = message.get().getString("Messages.DisablePlayerGUI");
    String path = message.get().getString("Messages.DisablePlayerGUI.GlassColor");
    if ((pathgui != null) && (path != null) && (message.exists())) {
      if (path.equalsIgnoreCase("white")) {
        color = 0;
      } else if (path.equalsIgnoreCase("orange")) {
        color = 1;
      } else if (path.equalsIgnoreCase("magenta")) {
        color = 2;
      } else if (path.equalsIgnoreCase("yellow")) {
        color = 4;
      } else if (path.equalsIgnoreCase("lime")) {
        color = 5;
      } else if (path.equalsIgnoreCase("pink")) {
        color = 6;
      } else if (path.equalsIgnoreCase("gray")) {
        color = 7;
      } else if (path.equalsIgnoreCase("cyan")) {
        color = 9;
      } else if (path.equalsIgnoreCase("purple")) {
        color = 10;
      } else if (path.equalsIgnoreCase("blue")) {
        color = 11;
      } else if (path.equalsIgnoreCase("brown")) {
        color = 12;
      } else if (path.equalsIgnoreCase("green")) {
        color = 13;
      } else if (path.equalsIgnoreCase("red")) {
        color = 14;
      } else if (path.equalsIgnoreCase("black")) {
        color = 15;
      } else {
        color = 0;
      }
    }
    return color;
  }
  
  public void items(Inventory inv)
  {
    for (int place = 0; place < 9; place++) {
      inv.setItem(place, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    }
    
    inv.setItem(0, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(18, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(27, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(36, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(45, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    
    inv.setItem(8, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(26, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(35, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(44, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    inv.setItem(53, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    
    for (int place = 45; place < 54; place++) {
      inv.setItem(place, new ItemStack(Material.STAINED_GLASS_PANE, 1, getGlassColor()));
    }
    
    for (Player player : Bukkit.getOnlinePlayers()) {
      ItemStack item = null;
      ArrayList<String> lores = new ArrayList();
      for (String lore : getItemLores()) {
        lores.add(util.color(new Variables(player, lore).applyChatVariables()
          .replace("%status%", util.color(getStatus(player)))));
      }
      item = 
        new Inv(Material.SKULL_ITEM, 1, (short)3, getItemName().replace("%player_name%", player.getName()), lores).createItem();
      SkullMeta meta = (SkullMeta)item.getItemMeta();
      meta.setOwner(player.getName());
      item.setItemMeta(meta);
      inv.addItem(new ItemStack[] { item });
    }
  }
  
  public void openGUI()
  {
    Inventory inventory = new Inv(gui, getGUIName(), 54).createInv();
    items(inventory);
    Inv inv = new Inv(inventory, p);
    inv.openInvWithNote(new Note(0));
  }
  
  public List<String> list() {
    return dataconfig.get().getStringList("DisabledPlayers");
  }
  
  public boolean contains() {
    boolean bool = false;
    String path = dataconfig.get().getString("DisabledPlayers");
    if ((path != null) && 
      (list().contains(playername.toLowerCase()))) {
      bool = true;
    }
    
    return bool;
  }
  
  public void add() {
    List<String> stringList = list();
    if (!stringList.contains(playername.toLowerCase())) {
      stringList.add(playername.toLowerCase());
    }
    dataconfig.set("DisabledPlayers", stringList);
    dataconfig.save();
  }
  
  public void remove() {
    List<String> stringList = list();
    if (stringList.contains(playername.toLowerCase())) {
      stringList.remove(playername.toLowerCase());
    }
    dataconfig.set("DisabledPlayers", stringList);
    dataconfig.save();
  }
}

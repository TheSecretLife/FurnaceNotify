package me.furnace.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Inv
{
  private int size;
  private String invname;
  private Inventory inv;
  private Player p;
  private short id;
  private int amount;
  private Material mat;
  private String itemname;
  private ArrayList<String> lores;
  
  public Inv(Player player)
  {
    p = player;
  }
  
  public Inv(Inventory inventory) {
    inv = inventory;
  }
  
  public Inv(Inventory inventory, Player player) {
    inv = inventory;
    p = player;
  }
  
  public Inv(Inventory inventory, String inventoryname, int size) {
    inv = inventory;
    invname = inventoryname;
    this.size = size;
  }
  
  public Inv(Material material, int amount, short id, String name, ArrayList<String> lores) {
    mat = material;
    this.amount = amount;
    this.id = id;
    itemname = name;
    this.lores = lores;
  }
  
  public String color(String msg) {
    return ChatColor.translateAlternateColorCodes('&', msg);
  }
  
  public String colorList(ArrayList<String> list) {
    String msg = color("");
    for (String message : list) {
      msg = color(message);
    }
    return msg;
  }
  
  public void addItem(ItemStack stack) {
    inv.addItem(new ItemStack[] { stack });
  }
  
  public void addItems(ItemStack... stacks) {
    for (ItemStack stack : stacks) {
      inv.addItem(new ItemStack[] { stack });
    }
  }
  
  public void setItem(ItemStack stack, int place) {
    int plc = place + 1;
    inv.setItem(plc, stack);
  }
  
  public void setItems(HashMap<ItemStack, Integer> stacks) {
    for (ItemStack stack : stacks.keySet()) {
      inv.setItem(((Integer)stacks.get(stack)).intValue(), stack);
    }
  }
  
  public void removeItem(ItemStack stack) {
    inv.remove(stack);
  }
  
  public void removeItemPlace(int place) {
    int plc = place + 1;
    inv.setItem(plc, new ItemStack(Material.AIR, 1));
  }
  
  public void removeItems() {
    inv.clear();
  }
  
  public Inventory createInv() {
    return this.inv = Bukkit.getServer().createInventory(null, size, color(invname));
  }
  
  public ItemStack createItem() {
    ItemStack stack = new ItemStack(mat, amount, id);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(color(itemname));
    meta.setLore(lores);
    stack.setItemMeta(meta);
    return stack;
  }
  
  public void openInv() {
    p.openInventory(inv);
  }
  
  public void openInvWithNote(Note note) {
    p.playNote(p.getLocation(), Instrument.PIANO, note);
    p.openInventory(inv);
  }
  
  public void closeInv() {
    p.closeInventory();
  }
  
  public void closeInvWithSound(Sound sound) {
    p.playSound(p.getLocation(), sound, 0.5F, 0.5F);
    p.closeInventory();
  }
}

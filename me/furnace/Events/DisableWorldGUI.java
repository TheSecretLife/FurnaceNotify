package me.furnace.Events;

import me.furnace.API;
import me.furnace.Configs.Messages;
import me.furnace.Configs.Permission;
import me.furnace.Methods.DisableWorld;
import me.furnace.Utils.Utils;
import me.furnace.Utils.Variables;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DisableWorldGUI implements org.bukkit.event.Listener
{
  public DisableWorldGUI() {}
  
  private Utils util = new Utils();
  private Messages message = new Messages();
  private Permission perm = new Permission();
  private DisableWorld gui = new DisableWorld();
  
  @org.bukkit.event.EventHandler
  public void DisableWorld(InventoryClickEvent e) {
    Player p = (Player)e.getWhoClicked();
    ClickType click = e.getClick();
    ItemStack item = e.getCurrentItem();
    InventoryAction action = e.getAction();
    if (e.getInventory().getName().equalsIgnoreCase(util.color(gui.getGUIName()))) {
      if (!(e.getWhoClicked() instanceof Player)) {
        e.setCancelled(true);
        return;
      }
      if (item == null) {
        return;
      }
      if (item.getItemMeta() == null) {
        e.setCancelled(true);
        p.updateInventory();
        return;
      }
      if (item.getItemMeta().getDisplayName() == null) {
        e.setCancelled(true);
        p.updateInventory();
        return;
      }
      if (item.getType() == Material.STAINED_GLASS_PANE) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.MIDDLE) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.DOUBLE_CLICK) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.SHIFT_LEFT) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.SHIFT_RIGHT) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.CREATIVE) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.DROP) {
        e.setCancelled(true);
        return;
      }
      if (click == ClickType.NUMBER_KEY) {
        e.setCancelled(true);
        return;
      }
      if (action == InventoryAction.HOTBAR_MOVE_AND_READD) {
        e.setCancelled(true);
        return;
      }
      if (action == InventoryAction.HOTBAR_SWAP) {
        e.setCancelled(true);
        return;
      }
      if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
        e.setCancelled(true);
        return;
      }
      if (action == InventoryAction.SWAP_WITH_CURSOR) {
        e.setCancelled(true);
        return;
      }
      if (action == InventoryAction.COLLECT_TO_CURSOR) {
        e.setCancelled(true);
        return;
      }
      if (action == InventoryAction.DROP_ALL_CURSOR) {
        return;
      }
      String worldname = 
        net.md_5.bungee.api.ChatColor.stripColor(item.getItemMeta().getDisplayName().replace("◆", "").replace(" ", ""));
      DisableWorld world = new DisableWorld(worldname);
      Utils firework = new Utils(p);
      e.setCancelled(true);
      if (org.bukkit.Bukkit.getWorld(worldname) == null) {
        p.closeInventory();
        util.getAPI().sendTitle(p, 
          new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
          message.getWorldDontExistMessage().replace("%world%", worldname));
        return;
      }
      if (!p.hasPermission(perm.getdisableWorldPermission())) {
        p.closeInventory();
        util.getAPI().sendTitle(p, 
          new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
          new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
        return;
      }
      if (world.contains()) {
        world.remove();
        firework.spawnFirework(true);
        p.closeInventory();
        util.getAPI().sendTitle(p, 
          new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
          new Variables(p, message.getWorldEnableMessage()).applyChatVariables().replace("%world%", 
          worldname));
        return;
      }
      world.add();
      firework.spawnFirework(true);
      p.closeInventory();
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getWorldDisableMessage()).applyChatVariables().replace("%world%", 
        worldname));
      return;
    }
  }
}

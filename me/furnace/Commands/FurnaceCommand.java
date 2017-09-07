package me.furnace.Commands;

import me.furnace.API;
import me.furnace.Configs.Config;
import me.furnace.Configs.Data;
import me.furnace.Configs.IPlayer;
import me.furnace.Configs.Messages;
import me.furnace.Configs.Permission;
import me.furnace.Main;
import me.furnace.Methods.DisablePlayer;
import me.furnace.Methods.DisableWorld;
import me.furnace.Methods.Toggle;
import me.furnace.Utils.Cooldown;
import me.furnace.Utils.Utils;
import me.furnace.Utils.Variables;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FurnaceCommand implements org.bukkit.command.CommandExecutor
{
  private Utils util = new Utils();
  private Data data = new Data();
  private Config config = new Config();
  private Messages message = new Messages();
  private Permission perm = new Permission();
  
  public FurnaceCommand() {}
  
  public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) { if (!cmd.getName().equalsIgnoreCase("furnace")) {
      return false;
    }
    if (!(cs instanceof Player)) {
      config.reload();
      config.save();
      message.reload();
      message.save();
      data.reload();
      data.save();
      perm.reload();
      perm.save();
      Main.getInstance().reloadConfig();
      cs.sendMessage(util.color(message.getReloadMessage()));
      return true;
    }
    Player p = (Player)cs;
    if (args.length == 0) {
      p.sendMessage(util.getAPI().translate("&7-----------=====[ &e&lFurnace Notify &7]=====-----------"));
      p.sendMessage(util.getAPI().translate("&a▶  &e/furnace toggle &8: &7Toggle the actionbar!"));
      p.sendMessage(
        util.getAPI().translate("&a▶  &e/furnace info &8: &7Get informations about the player!"));
      p.sendMessage(util.getAPI().translate("&a▶  &e/furnace disableworld &8: &7Opens the gui!"));
      p.sendMessage(util.getAPI().translate("&a▶  &e/furnace disableplayer &8: &7Opens the gui!"));
      p.sendMessage(util.getAPI().translate("&a▶  &e/furnace reload &8: &7Reload the config!"));
      p.sendMessage(util.getAPI().translate("&7-----------=====[ &e&lFurnace Notify &7]=====-----------"));
      return true;
    }
    if (((args.length < 1) || (args.length > 1)) && (args[0].equalsIgnoreCase("disableplayer")) && 
      (!p.hasPermission(perm.getdisablePlayerPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("disableplayer")) && 
      (!p.hasPermission(perm.getdisablePlayerPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length >= 1) && (args[0].equalsIgnoreCase("disableplayer"))) {
      Utils firework = new Utils(p);
      DisablePlayer gui = new DisablePlayer(p);
      gui.openGUI();
      firework.spawnFirework(true);
      return true;
    }
    if (((args.length < 1) || (args.length > 1)) && (args[0].equalsIgnoreCase("disableworld")) && 
      (!p.hasPermission(perm.getdisableWorldPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("disableworld")) && 
      (!p.hasPermission(perm.getdisableWorldPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length >= 1) && (args[0].equalsIgnoreCase("disableworld"))) {
      Utils firework = new Utils(p);
      DisableWorld gui = new DisableWorld(p);
      gui.openGUI();
      firework.spawnFirework(true);
      return true;
    }
    if (((args.length < 2) || (args.length > 2)) && (args[0].equalsIgnoreCase("info")) && 
      (!p.hasPermission(perm.getInfoPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length == 2) && (args[0].equalsIgnoreCase("info")) && (!p.hasPermission(perm.getInfoPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if (((args.length < 2) || (args.length > 2)) && (args[0].equalsIgnoreCase("info"))) {
      p.sendMessage(util.color(new Variables(p, message.getInfoUsageMessage()).applyChatVariables()));
      return true;
    }
    if ((args.length == 2) && (args[0].equalsIgnoreCase("info"))) {
      Player target = org.bukkit.Bukkit.getServer().getPlayerExact(args[1]);
      IPlayer playerconfig = new IPlayer(target);
      if (target == null) {
        util.getAPI().sendTitle(p, 
          new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
          message.getOfflinePlayerMessage().replace("%player_name%", args[1]));
        return true;
      }
      if (!playerconfig.exists()) {
        util.getAPI().sendTitle(p, 
          new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
          message.getConfigNotFoundMessage().replace("%player_name%", args[1]));
        return true;
      }
      Utils firework = new Utils(p);
      playerconfig.sendInfoMessage(p);
      firework.spawnFirework(true);
      return true;
    }
    if (((args.length < 1) || (args.length > 1)) && (args[0].equalsIgnoreCase("reload")) && 
      (!p.hasPermission(perm.getReloadPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("reload")) && 
      (!p.hasPermission(perm.getReloadPermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if (((args.length < 1) || (args.length > 1)) && (args[0].equalsIgnoreCase("reload"))) {
      p.sendMessage(util.color(new Variables(p, message.getReloadUsageMessage()).applyChatVariables()));
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("reload"))) {
      Utils firework = new Utils(p);
      config.reload();
      config.save();
      message.reload();
      message.save();
      data.reload();
      data.save();
      perm.reload();
      perm.save();
      firework.spawnFirework(true);
      Main.getInstance().reloadConfig();
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getReloadMessage()).applyChatVariables());
      return true;
    }
    if (((args.length < 1) || (args.length > 2)) && (args[0].equalsIgnoreCase("toggle")) && 
      (!p.hasPermission(perm.getTogglePermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("toggle")) && 
      (!p.hasPermission(perm.getTogglePermission()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if ((args.length == 2) && (args[0].equalsIgnoreCase("toggle")) && (!p.hasPermission(perm.getToggleOthers()))) {
      util.getAPI().sendTitle(p, new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
        new Variables(p, message.getNoPermissionMessage()).applyChatVariables());
      return true;
    }
    if (((args.length < 1) || (args.length > 2)) && (args[0].equalsIgnoreCase("toggle"))) {
      p.sendMessage(util.color(new Variables(p, message.getToggleUsageMessage()).applyChatVariables()));
      return true;
    }
    if ((args.length == 1) && (args[0].equalsIgnoreCase("toggle"))) {
      Toggle toggle = new Toggle(p);
      Utils firework = new Utils(p);
      Cooldown cooldown = new Cooldown(p.getUniqueId());
      if (cooldown.contains()) {
        p.sendMessage(util.color(new Variables(p, 
          message.getCooldownMessage().replace("%cooldown%", cooldown.getRemainingTime()))
          .applyChatVariables()));
        return true;
      }
      if (toggle.contains()) {
        toggle.remove();
        firework.spawnFirework(true);
        p.sendMessage(
          util.color(new Variables(p, message.getToggleEnableMessage()).applyChatVariables()));
        return true;
      }
      toggle.add();
      firework.spawnFirework(true);
      p.sendMessage(
        util.color(new Variables(p, message.getToggleDisableMessage()).applyChatVariables()));
      return true;
    }
    if ((args.length == 2) && (args[0].equalsIgnoreCase("toggle"))) {
      Player target = org.bukkit.Bukkit.getServer().getPlayerExact(args[1]);
      Toggle toggle = new Toggle(target);
      Utils firework = new Utils(target);
      Cooldown cooldown = new Cooldown(p.getUniqueId());
      if (target == null) {
        util.getAPI().sendTitle(p, 
          new Variables(p, message.getTitlePrefixMessage()).applyChatVariables(), 
          message.getOfflinePlayerMessage().replace("%player_name%", args[1]));
        return true;
      }
      if ((target.hasPermission(perm.getToggleExempt())) && (!p.hasPermission(perm.getToggleBypass()))) {
        p.sendMessage(util
          .color(new Variables(target, message.getToggleNotThisPlayer()).applyChatVariables()));
        return true;
      }
      if (cooldown.contains()) {
        p.sendMessage(util.color(new Variables(target, 
          message.getCooldownMessage().replace("%cooldown%", cooldown.getRemainingTime()))
          .applyChatVariables()));
      }
      if (toggle.contains()) {
        toggle.remove();
        firework.spawnFirework(true);
        p.sendMessage(util
          .color(new Variables(target, message.getToggleEnableOthers()).applyChatVariables()));
        target.sendMessage(util
          .color(new Variables(target, message.getToggleEnableMessage()).applyChatVariables()));
        return true;
      }
      toggle.add();
      firework.spawnFirework(true);
      p.sendMessage(
        util.color(new Variables(target, message.getToggleDisableOthers()).applyChatVariables()));
      target.sendMessage(util
        .color(new Variables(target, message.getToggleDisableMessage()).applyChatVariables()));
      return true;
    }
    return false;
  }
}

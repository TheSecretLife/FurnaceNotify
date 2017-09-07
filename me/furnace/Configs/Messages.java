package me.furnace.Configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import me.furnace.API;
import me.furnace.Utils.Utils;
import me.furnace.Utils.Variables;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Messages
{
  private String msg;
  private Player p;
  private Furnace furnace;
  private HashMap<String, Object> msgs = new HashMap();
  
  public Messages() {}
  
  public Messages(String message)
  {
    msg = message;
  }
  
  public Messages(Player player, String message) {
    msg = message;
    p = player;
  }
  
  public Messages(Player player, String message, Furnace fur) {
    p = player;
    msg = message;
    furnace = fur;
  }
  
  public File msgFile() {
    return new File("plugins/FurnaceNotify/" + File.separator + "en.yml");
  }
  
  public boolean exists() {
    return msgFile().exists();
  }
  
  public void create() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(msgFile());
    if (!exists())
    {
      config.options().header("###########################################################\r\n################## FURNACE NOTIFY MESSAGES ################\r\n###########################################################");
      

      for (String keys : defaultKeys().keySet()) {
        config.addDefault(keys, defaultKeys().get(keys));
      }
      config.options().copyDefaults(true);
      try {
        config.save(msgFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void reload() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(msgFile());
    if (exists()) {
      try {
        config.load(msgFile());
      } catch (IOException|InvalidConfigurationException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void save() {
    FileConfiguration config = YamlConfiguration.loadConfiguration(msgFile());
    if (exists()) {
      try {
        config.save(msgFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public FileConfiguration get() {
    return YamlConfiguration.loadConfiguration(msgFile());
  }
  
  public String defaultMessage() {
    return "Message not found in config";
  }
  
  public void sendFurnaceMessage() {
    Utils util = new Utils();
    p.sendMessage(
      util.getAPI().translate(new Variables(p, msg, furnace).applyFurnaceVariables()));
  }
  
  public void sendChatMessage() {
    Utils util = new Utils();
    p.sendMessage(util.getAPI().translate(new Variables(p, msg).applyChatVariables()));
  }
  
  public String getMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages." + msg);
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getProgressMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.Progress" + msg);
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getCooldownMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.Cooldown");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getConfigNotFoundMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ConfigNotFound");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getCooldownOverMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.CooldownOver");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getChatPrefixMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.PrefixChat");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public List<String> getInfoMessages() {
    ArrayList<String> list = new ArrayList();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.InfoMessages");
    if ((messages != null) && (path != null) && (exists())) {
      return get().getStringList("Messages.InfoMessages");
    }
    return list;
  }
  
  public String getTitlePrefixMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.PrefixTitle");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getNoPermissionMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.NoPermission");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getOfflinePlayerMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.OfflinePlayer");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getToggleUsageMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ToggleUsage");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getInfoUsageMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.InfoUsage");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getReloadUsageMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ReloadUsage");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getReloadMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.Reload");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getToggleEnableMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ToggleEnable");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getToggleEnableOthers() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ToggleEnableOthers");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getToggleDisableMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ToggleDisable");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getToggleNotThisPlayer() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ToggleExempt");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getToggleDisableOthers() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.ToggleDisableOthers");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getWorldDisableMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.DisableWorld");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getPlayerDisableMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.DisablePlayer");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getWorldDontExistMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.WorldNotFound");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getWorldEnableMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.EnableWorld");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public String getPlayerEnableMessage() {
    String message = defaultMessage();
    String messages = get().getString("Messages");
    String path = get().getString("Messages.EnablePlayer");
    if ((messages != null) && (path != null) && (exists())) {
      message = path;
    }
    return message;
  }
  
  public HashMap<String, Object> defaultKeys() {
    msgs.put("Messages.DisableWorldGUI.GUIName", "&7&lChoose a world to disable!");
    msgs.put("Messages.DisableWorldGUI.GlassColor", "Black");
    msgs.put("Messages.DisableWorldGUI.ItemName", "&a&l%world%");
    msgs.put("Messages.DisableWorldGUI.ItemLores", Arrays.asList(new String[] { "&8", "&aWorld Status &8▶ &r%status%", 
      "&cRight-Click to disable this world!", "&8-=-=-=-=-=-" }));
    msgs.put("Messages.DisablePlayerGUI.GUIName", "&7&lChoose a player to disable!");
    msgs.put("Messages.DisablePlayerGUI.GlassColor", "Black");
    msgs.put("Messages.DisablePlayerGUI.ItemName", "&a&l%player_name%");
    msgs.put("Messages.DisablePlayerGUI.ItemLores", Arrays.asList(new String[] { "&8", "&aPlayer Status &8▶ &r%status%", 
      "&cRight-Click to disable this player!", "&8-=-=-=-=-=-" }));
    msgs.put("Messages.InfoMessages", 
      Arrays.asList(new String[] {"&7-----------=====[ &e&lFurnace Notify &7]=====-----------", 
      "&8▶ &3PlayerName&8: &r%player_name%", "&8▶ &3IP&8: &r%player_ip%", 
      "&8▶ &3ToggleDisabled&8: &c%toggle_status%", "&8▶ &3PlayerDisabled&8: &c%player_status%", " " }));
    msgs.put("Messages.PrefixChat", "&e&lFurnaceNotify &r");
    msgs.put("Messages.PrefixTitle", "&7◆  &e&lFurnaceNotify &7◆");
    msgs.put("Messages.CooldownOver", 
      "%prefix% &8▶ &aCooldown is over &r%player_displayname%, &ayou can use the command again.");
    msgs.put("Messages.Cooldown", 
      "%prefix% &8▶ &cYou must wait &r%cooldown% &cbefore you can use this command again.");
    msgs.put("Messages.NoPermission", "&cNo Permission, &r%player_displayname%");
    msgs.put("Messages.OfflinePlayer", "&cPlayer &r%player_name%&c, not found!");
    msgs.put("Messages.ToggleUsage", 
      "%prefix% &8▶  &cUsage&7: &e/fn toggle or /fn toggle <player> &8: &7Toggle the actionbar!");
    msgs.put("Messages.ReloadUsage", "%prefix% &8▶  &cUsage&7: &e/fn reload &8: &7Reload the config!");
    msgs.put("Messages.ConfigNotFound", "&cConfig for %player_name% not found!");
    msgs.put("Messages.InfoUsage", 
      "%prefix% &8▶  &cUsage&7: /fn info <player> &8: &7Get informations about the player!");
    msgs.put("Messages.Reload", "&aConfig reloaded!");
    msgs.put("Messages.EnablePlayer", "&ePlayer %player_name% &aenabled &esuccessfully");
    msgs.put("Messages.DisablePlayer", "&cPlayer %player_name% disabled &csuccessfully");
    msgs.put("Messages.EnableWorld", "&eWorld %world% &aenabled &esuccessfully");
    msgs.put("Messages.DisableWorld", "&cWorld %world% disabled &csuccessfully");
    msgs.put("Messages.WorldNotFound", "&cWorld %world% not found");
    msgs.put("Messages.ToggleExempt", "%prefix% &8▶ &cYou cant toggle the actionbar for this player!");
    msgs.put("Messages.ToggleEnable", "%prefix% &8▶ &eToggle &aEnabled&e, now you can receive the actionbar!");
    msgs.put("Messages.ToggleEnableOthers", 
      "%prefix% &8▶ &eToggle &aEnabled&e for &r%player_name%, &enow he can receive the actionbar!");
    msgs.put("Messages.ToggleDisable", 
      "%prefix% &8▶ &eToggle &cDisabled&e, you cant receive the actionbar anymore!");
    msgs.put("Messages.ToggleDisableOthers", 
      "%prefix% &8▶ &eToggle &cDisabled&e for &r%player_name%, &enow he cant receive the actionbar anymore!");
    msgs.put("Messages.Progressbelow10", 
      "&7▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress10", 
      "&2█ &7▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress20", 
      "&2█ █ &7▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress30", 
      "&2█ █ █ &7▒ ▒ ▒ ▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress40", 
      "&2█ █ █ █ &7▒ ▒ ▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress50", 
      "&2█ █ █ █ █ &7▒ ▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress60", 
      "&2█ █ █ █ █ █ &7▒ ▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress70", 
      "&2█ █ █ █ █ █ █ &7▒ ▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress80", 
      "&2█ █ █ █ █ █ █ █ &7▒ ▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress90", 
      "&2█ █ █ █ █ █ █ █ █ &7▒ &a%cook_time%, smelting &d%smelt_item_amount% &e%smelt_item% &awith &5%fuel%");
    msgs.put("Messages.Progress100", "&2█ █ █ █ █ █ █ █ █ █ &6DONE!, &r%player_displayname%");
    return msgs;
  }
}

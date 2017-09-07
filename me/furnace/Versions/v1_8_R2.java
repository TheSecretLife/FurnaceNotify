package me.furnace.Versions;

import me.furnace.Configs.Config;
import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_8_R2 implements me.furnace.API
{
  private Config util = new Config();
  
  public v1_8_R2() {}
  
  public String translate(String msg) { return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg); }
  

  public void sendActionBar(Player p, String msg)
  {
    CraftPlayer player = (CraftPlayer)p;
    IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + translate(msg) + "\"}");
    PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
    getHandleplayerConnection.sendPacket(ppoc);
  }
  
  public void sendTitle(Player player, String title, String subtitle)
  {
    PlayerConnection connection = getHandleplayerConnection;
    
    PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, 
      Integer.valueOf(util.getTitleFadeIn()).intValue(), Integer.valueOf(util.getTitleStay()).intValue(), 
      Integer.valueOf(util.getTitleFadeOut()).intValue());
    connection.sendPacket(packetPlayOutTimes);
    if (subtitle != null) {
      IChatBaseComponent titleSub = 
        IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + translate(subtitle) + "\"}");
      PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(
        PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
      connection.sendPacket(packetPlayOutSubTitle);
    }
    if (title != null) {
      IChatBaseComponent titleMain = 
        IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + translate(title) + "\"}");
      PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, 
        titleMain);
      connection.sendPacket(packetPlayOutTitle);
    }
  }
}

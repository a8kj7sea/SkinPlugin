package me.a8kj.setskin.utils;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import lombok.Data;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class SimpleUtils {

    @Data
    public static class PlayerUtils {

        public static void sendMessage(Player player, String message) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }

        public static void sendActionbar(Player player, String message) {
            PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
            playerConnection.sendPacket(new PacketPlayOutChat(
                    IChatBaseComponent.ChatSerializer.a(
                            "{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}"),
                    (byte) 2));
        }
    }
}

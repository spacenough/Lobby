package me.spacenough.lobby.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String altChar(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}

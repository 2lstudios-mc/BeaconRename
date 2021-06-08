package dev._2lstudios.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class ChatColorUtils {
    public static List<String> translateColorCodes(final List<String> list) {
        if (list == null) {
            return new ArrayList<>();
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        
        return list;
    } 

    public static String translateColorCodes(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}

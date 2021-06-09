package dev._2lstudios.utils;

import org.bukkit.inventory.ItemStack;

public class BukkitUtils {
    public static boolean isSword(final ItemStack item) {
        return item.getType().name().contains("_SWORD");
    }
}

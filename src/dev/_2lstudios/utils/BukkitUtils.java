package dev._2lstudios.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BukkitUtils {
    public static boolean isSword(final ItemStack item) {
        return item.getType().name().contains("_SWORD");
    }

    public static boolean hasDisplayname(final ItemStack item) {
        final ItemMeta itemMeta = item.getItemMeta();

        return itemMeta.hasDisplayName();
    }
}

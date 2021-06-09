package dev._2lstudios.rename;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import dev._2lstudios.utils.BukkitUtils;

public class RenameTask {
    private final RenameConfig renameConfig;
    private final Player player;
    private final int retries;
    private int runs = 0;

    RenameTask(final RenameConfig renameConfig, final Player player) {
        this.renameConfig = renameConfig;
        this.player = player;
        this.retries = renameConfig.getRetries();
    }

    int run() {
        runs++;

        final String itemName = renameConfig.getItemName();

        if (runs < retries) {
            final Sound sound = renameConfig.getRetrySound();

            player.sendMessage(renameConfig.getRetryMessage(itemName));

            if (sound != null) {
                player.playSound(player.getLocation(), sound, 1, 1);
            }
        } else if (runs == retries) {
            final PlayerInventory inventory = player.getInventory();
            final int heldItemSlot = inventory.getHeldItemSlot();
            final ItemStack item = inventory.getItem(heldItemSlot);

            if (item != null && BukkitUtils.isSword(item)) {
                final Sound sound = renameConfig.getSuccessSound();
                final ItemMeta itemMeta = item.getItemMeta();

                itemMeta.setDisplayName(itemName);
                item.setItemMeta(itemMeta);
                inventory.setItem(heldItemSlot, item);
                player.sendMessage(renameConfig.getSuccessMessage(itemName));

                if (sound != null) {
                    player.playSound(player.getLocation(), sound, 1, 1);
                }
            } else {
                final Sound sound = renameConfig.getErrorSound();

                player.sendMessage(renameConfig.getErrorMessage());

                if (sound != null) {
                    player.playSound(player.getLocation(), sound, 1, 1);
                }
            }
        }

        return runs;
    }
}

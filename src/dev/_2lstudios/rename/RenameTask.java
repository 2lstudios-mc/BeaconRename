package dev._2lstudios.rename;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameTask {
    private final RenameConfig renameConfig;
    private final Player player;
    private final ItemStack item;
    private final int retries;
    private int runs = 0;

    RenameTask(final RenameConfig renameConfig, final Player player, final ItemStack item) {
        this.renameConfig = renameConfig;
        this.player = player;
        this.item = item;
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
            final ItemStack itemInHand = inventory.getItem(heldItemSlot);

            if (itemInHand != null && itemInHand.isSimilar(item)) {
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

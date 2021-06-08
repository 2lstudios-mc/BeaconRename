package dev._2lstudios.rename;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
            player.sendMessage(renameConfig.getRetryMessage(itemName));
        } else if (runs == retries) {
            final PlayerInventory inventory = player.getInventory();
            final int heldItemSlot = inventory.getHeldItemSlot();
            final ItemStack itemInHand = inventory.getItem(heldItemSlot);

            if (itemInHand != null && itemInHand.isSimilar(item)) {
                inventory.setItem(heldItemSlot, item);
                player.sendMessage(renameConfig.getSuccessMessage(itemName));
            } else {
                player.sendMessage(renameConfig.getErrorMessage());
            }
        }

        return runs;
    }
}

package dev._2lstudios.rename;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RenameTask {
    private final Player player;
    private final ItemStack item;
    private int runs = 0;

    RenameTask(final Player player, final ItemStack item) {
        this.player = player;
        this.item = item;
    }

    int run() {
        runs++;

        if (runs == 1) {

        } else if (runs == 2) {

        } else if (runs == 3) {

        } else if (runs == 4) {
            final PlayerInventory inventory = player.getInventory();
            final int heldItemSlot = inventory.getHeldItemSlot();
            final ItemStack itemInHand = inventory.getItem(heldItemSlot);

            if (itemInHand != null && itemInHand.isSimilar(item)) {
                inventory.setItem(heldItemSlot, item);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCant rename item not found."));

            }
        }

        return runs;
    }
}

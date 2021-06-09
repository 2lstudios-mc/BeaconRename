package dev._2lstudios.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import dev._2lstudios.rename.RenameConfig;
import dev._2lstudios.rename.RenameManager;
import dev._2lstudios.utils.BukkitUtils;

class PlayerInteractListener implements Listener {
    private final RenameConfig renameConfig;
    private final RenameManager renameManager;

    PlayerInteractListener(final RenameConfig renameConfig, final RenameManager renameManager) {
        this.renameConfig = renameConfig;
        this.renameManager = renameManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final Block block = event.getClickedBlock();

            if (block != null && block.getType() == Material.BEACON) {
                final Player player = event.getPlayer();
                final PlayerInventory inventory = player.getInventory();
                final int heldItemSlot = inventory.getHeldItemSlot();
                final ItemStack heldItem = inventory.getItem(heldItemSlot);

                if (heldItem != null && BukkitUtils.isSword(heldItem)) {
                    final String permission = renameConfig.getPermission();

                    if (permission == null || permission.isEmpty() || player.hasPermission(permission)) {
                        if (!renameManager.hasTask(player)) {
                            renameManager.createTask(player);
                        } else {
                            player.sendMessage(renameConfig.getAlreadyRenamingMessage());
                        }

                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}

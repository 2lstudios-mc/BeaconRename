package dev._2lstudios.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dev._2lstudios.rename.RenameManager;

public class PlayerInteractListener implements Listener {
    private final RenameManager renameManager;

    PlayerInteractListener(final RenameManager renameManager) {
        this.renameManager = renameManager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final Player player = event.getPlayer();

            renameManager.createTask(player);
        }
    }
}

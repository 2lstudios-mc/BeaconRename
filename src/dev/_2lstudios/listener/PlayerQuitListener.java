package dev._2lstudios.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import dev._2lstudios.rename.RenameManager;

public class PlayerQuitListener {
    private final RenameManager renameManager;

    PlayerQuitListener(final RenameManager renameManager) {
        this.renameManager = renameManager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        renameManager.removeTask(event.getPlayer());
    }
}

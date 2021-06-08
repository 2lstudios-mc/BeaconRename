package dev._2lstudios.listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import dev._2lstudios.rename.RenameManager;

public class BeaconRenameListeners {
    public static void initialize(final Plugin plugin, final RenameManager renameManager) {
        final PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerInteractListener(renameManager), plugin);
    }
}

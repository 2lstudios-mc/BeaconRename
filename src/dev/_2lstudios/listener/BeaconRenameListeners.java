package dev._2lstudios.listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import dev._2lstudios.rename.RenameConfig;
import dev._2lstudios.rename.RenameManager;

public class BeaconRenameListeners {
    public static void initialize(final Plugin plugin, final RenameConfig renameConfig, final RenameManager renameManager) {
        final PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerInteractListener(renameConfig, renameManager), plugin);
    }
}

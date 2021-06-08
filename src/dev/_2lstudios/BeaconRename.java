package dev._2lstudios;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.listener.BeaconRenameListeners;
import dev._2lstudios.rename.RenameManager;

public class BeaconRename extends JavaPlugin {
    @Override
    public void onEnable() {
        final RenameManager renameManager = new RenameManager();

        BeaconRenameListeners.initialize(this, renameManager);
    }
}
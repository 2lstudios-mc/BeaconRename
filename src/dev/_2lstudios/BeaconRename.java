package dev._2lstudios;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.listener.BeaconRenameListeners;

public class BeaconRename extends JavaPlugin {
    @Override
    public void onEnable() {
       BeaconRenameListeners.initialize(this); 
    }
}
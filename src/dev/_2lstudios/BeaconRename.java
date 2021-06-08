package dev._2lstudios;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.listener.BeaconRenameListeners;
import dev._2lstudios.rename.RenameConfig;
import dev._2lstudios.rename.RenameManager;
import dev._2lstudios.utils.ConfigurationUtils;

public class BeaconRename extends JavaPlugin {
    @Override
    public void onEnable() {
        final ConfigurationUtils configurationUtils = new ConfigurationUtils(this);
        final Configuration config = configurationUtils.createAndGet("%datafolder%/config.yml", "config.yml");
        final RenameConfig renameConfig = new RenameConfig(config);
        final RenameManager renameManager = new RenameManager(renameConfig);
        final long delay = renameConfig.getDelay();

        BeaconRenameListeners.initialize(this, renameManager);

        getServer().getScheduler().runTaskTimerAsynchronously(this, renameManager, delay, delay);
    }
}
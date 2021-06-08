package dev._2lstudios.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class ConfigurationUtils {
    private static final String DATA_FOLDER_PLACEHOLDER = "%datafolder%";
    private final String dataFolderPath;
    private final String prefix;
    private final Logger logger;
    private final Plugin plugin;
    private final BukkitScheduler scheduler;
    private final ClassLoader classLoader;

    public ConfigurationUtils(final Plugin plugin) {
		this.plugin = plugin;
		this.scheduler = plugin.getServer().getScheduler();
		this.logger = plugin.getLogger();
		this.classLoader = plugin.getClass().getClassLoader();
		this.dataFolderPath = plugin.getDataFolder().toString();
		this.prefix = "[" + plugin.getName() + "]";
	}

    private void createParentFolder(final File file) {
        final File parentFile = file.getParentFile();

        if (parentFile != null) {
            parentFile.mkdirs();
        }
    }

    public YamlConfiguration get(final String rawPath) {
        final File file = new File(rawPath.replace(DATA_FOLDER_PLACEHOLDER, dataFolderPath));

        if (file.exists()) {
            return YamlConfiguration.loadConfiguration(file);
        } else {
            return new YamlConfiguration();
        }
    }

    public void create(final String rawPath, final String resourcePath) {
        final String path = rawPath.replace(DATA_FOLDER_PLACEHOLDER, dataFolderPath);

        try {
            final File configFile = new File(path);

            if (!configFile.exists()) {
                final InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

                createParentFolder(configFile);

                if (inputStream != null) {
                    Files.copy(inputStream, configFile.toPath());
                } else {
                    configFile.createNewFile();
                }

                logger.info(prefix + " File '" + path + "' has been created!");
            }
        } catch (final IOException e) {
            logger.info(prefix + " Unable to create '" + path + "'!");
        }
    }

    public YamlConfiguration createAndGet(final String rawPath, final String resourcePath) {
        create(rawPath, resourcePath);

        return get(rawPath);
    }

    public void save(final YamlConfiguration config, final String rawPath) {
        final String path = rawPath.replace(DATA_FOLDER_PLACEHOLDER, dataFolderPath);

        scheduler.runTaskAsynchronously(plugin, () -> {
            try {
                config.save(path);

                logger.info(prefix + " File '" + path + "' has been saved!");
            } catch (final IOException e) {
                logger.info(prefix + " Unable to save '" + path + "'!");
            }
        });
    }
}

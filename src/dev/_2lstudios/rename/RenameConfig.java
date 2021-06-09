package dev._2lstudios.rename;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;

import dev._2lstudios.utils.ChatColorUtils;
import dev._2lstudios.utils.ListUtils;

public class RenameConfig {
    private final List<String> retryMessages;
    private final List<String> successMessages;
    private final List<String> errorMessages;
    private final List<String> itemNames;

    private final List<Sound> retrySounds;
    private final List<Sound> successSounds;
    private final List<Sound> errorSounds;

    private final int retries;

    private final long delay;

    public RenameConfig(final Configuration config) {
        retryMessages = ChatColorUtils.translateColorCodes(config.getStringList("retry_messages"));
        successMessages = ChatColorUtils.translateColorCodes(config.getStringList("success_messages"));
        errorMessages = ChatColorUtils.translateColorCodes(config.getStringList("error_messages"));
        itemNames = ChatColorUtils.translateColorCodes(config.getStringList("item_names"));

        retrySounds = ListUtils.toSoundList(config.getStringList("retry_sounds"));
        successSounds = ListUtils.toSoundList(config.getStringList("success_sounds"));
        errorSounds = ListUtils.toSoundList(config.getStringList("error_sounds"));

        retries = config.getInt("retries", 4);

        delay = config.getLong("delay", 5L);
    }

    public long getDelay() {
        return delay;
    }

    public int getRetries() {
        return retries;
    }

    private String replacePlaceholders(final String string, final String itemName) {
        return ChatColorUtils.translateColorCodes(string.replace("%item_name%", itemName));
    }

    public String getRetryMessage(final String itemName) {
        return replacePlaceholders(ListUtils.getRandomString(retryMessages), itemName);
    }

    public String getSuccessMessage(final String itemName) {
        return replacePlaceholders(ListUtils.getRandomString(successMessages), itemName);
    }

    public String getErrorMessage() {
        return ChatColorUtils.translateColorCodes(ListUtils.getRandomString(errorMessages));
    }

    public String getItemName() {
        return ChatColorUtils.translateColorCodes(ListUtils.getRandomString(itemNames));
    }

    public Sound getRetrySound() {
        return ListUtils.getRandomSound(retrySounds);
    }

    public Sound getSuccessSound() {
        return ListUtils.getRandomSound(successSounds);
    }

    public Sound getErrorSound() {
        return ListUtils.getRandomSound(errorSounds);
    }
}

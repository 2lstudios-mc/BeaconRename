package dev._2lstudios.rename;

import java.util.List;

import org.bukkit.configuration.Configuration;

import dev._2lstudios.utils.ChatColorUtils;

public class RenameConfig {
    private final List<String> retryMessages;
    private final List<String> successMessages;
    private final List<String> errorMessages;
    private final List<String> itemNames;
    private final int retries;
    private final long delay;

    public RenameConfig(final Configuration config) {
        retryMessages = ChatColorUtils.translateColorCodes(config.getStringList("retry_messages"));
        successMessages = ChatColorUtils.translateColorCodes(config.getStringList("success_messages"));
        errorMessages = ChatColorUtils.translateColorCodes(config.getStringList("error_messages"));
        itemNames = ChatColorUtils.translateColorCodes(config.getStringList("item_names"));
        retries = config.getInt("retries", 4);
        delay = config.getLong("delay", 5L);
    }

    public long getDelay() {
        return delay;
    }

    public int getRetries() {
        return retries;
    }

    private String getRandom(final List<String> list) {
        final int index = (int) (Math.random() * list.size());

        return list.get(index);
    }

    private String replacePlaceholders(final String string, final String itemName) {
        return ChatColorUtils.translateColorCodes(string.replace("%item_name%", itemName));
    }

    public String getRetryMessage(final String itemName) {
        return replacePlaceholders(getRandom(retryMessages), itemName);
    }

    public String getSuccessMessage(final String itemName) {
        return replacePlaceholders(getRandom(successMessages), itemName);
    }

    public String getErrorMessage() {
        return getRandom(errorMessages);
    }

    public String getItemName() {
        return getRandom(itemNames);
    }
}

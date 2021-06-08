package dev._2lstudios.rename;

import java.util.Collection;

import org.bukkit.configuration.Configuration;

import dev._2lstudios.util.ChatColorUtils;

public class RenameConfig {
    private final Collection<String> retryMessages;
    private final Collection<String> successMessages;
    private final Collection<String> itemNames;

    RenameConfig(final Configuration config) {
        retryMessages = ChatColorUtils.translateColorCodes(config.getStringList("retry_messages"));
        successMessages = ChatColorUtils.translateColorCodes(config.getStringList("success_messages"));
        itemNames = ChatColorUtils.translateColorCodes(config.getStringList("item_names"));
    }

    public Collection<String> getRetryMessages() {
        return retryMessages;
    }

    public Collection<String> getSuccessMessages() {
        return successMessages;
    }

    public Collection<String> getItemNames() {
        return itemNames;
    }
}

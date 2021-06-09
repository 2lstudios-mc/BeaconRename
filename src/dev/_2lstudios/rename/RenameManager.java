package dev._2lstudios.rename;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class RenameManager implements Runnable {
    private final Map<UUID, RenameTask> tasks = new HashMap<>();
    private final RenameConfig renameConfig;

    public RenameManager(final RenameConfig renameConfig) {
        this.renameConfig = renameConfig;
    }

    public void createTask(final Player player) {
        this.tasks.put(player.getUniqueId(), new RenameTask(renameConfig, player));
    }

    public void removeTask(final Player player) {
        this.tasks.remove(player.getUniqueId());
    }

    public boolean hasTask(final Player player) {
        return this.tasks.containsKey(player.getUniqueId());
    }

    @Override
    public void run() {
        final Iterator<RenameTask> iterator = tasks.values().iterator();

        while (iterator.hasNext()) {
            final RenameTask renameTask = iterator.next();

            if (renameTask.run() > 3) {
                iterator.remove();
            }
        }
    }
}

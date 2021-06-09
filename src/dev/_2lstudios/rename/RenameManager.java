package dev._2lstudios.rename;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.entity.Player;

public class RenameManager implements Runnable {
    private final Collection<RenameTask> tasks = new HashSet<>();
    private final RenameConfig renameConfig;

    public RenameManager(final RenameConfig renameConfig) {
        this.renameConfig = renameConfig;
    }

    public void createTask(final Player player) {
        this.tasks.add(new RenameTask(renameConfig, player));
    }

    @Override
    public void run() {
        final Iterator<RenameTask> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            final RenameTask renameTask = iterator.next();

            if (renameTask.run() > 3) {
                iterator.remove();
            }
        }
    }
}

package dev._2lstudios.rename;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RenameManager implements Runnable {
    private final Collection<RenameTask> tasks = new HashSet<>();
    private final RenameConfig renameConfig;

    public RenameManager(final RenameConfig renameConfig) {
        this.renameConfig = renameConfig;
    }

    public void createTask(final Player player) {
        final PlayerInventory inventory = player.getInventory();
        final int heldItemSlot = inventory.getHeldItemSlot();
        final ItemStack heldItem = inventory.getItem(heldItemSlot);

        this.tasks.add(new RenameTask(renameConfig, player, heldItem));
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

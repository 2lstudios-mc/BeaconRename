package dev._2lstudios.rename;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RenameManager {
    private final Collection<RenameTask> tasks = new HashSet<>();

    public void createTask(final Player player) {
        final PlayerInventory inventory = player.getInventory();
        final int heldItemSlot = inventory.getHeldItemSlot();
        final ItemStack heldItem = inventory.getItem(heldItemSlot);

        this.tasks.add(new RenameTask(player, heldItem));
    }

    public void update() {
        final Iterator<RenameTask> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            final RenameTask renameTask = iterator.next();

            if (renameTask.run() > 3) {
                iterator.remove();
            }
        }
    }
}

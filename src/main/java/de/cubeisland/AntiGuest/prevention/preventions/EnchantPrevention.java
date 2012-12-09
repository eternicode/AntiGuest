package de.cubeisland.AntiGuest.prevention.preventions;

import de.cubeisland.AntiGuest.prevention.Prevention;
import de.cubeisland.AntiGuest.prevention.PreventionPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Prevents enchanting
 *
 * @author Phillip Schichtel
 */
public class EnchantPrevention extends Prevention
{
    public EnchantPrevention(PreventionPlugin plugin)
    {
        super("enchant", plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void open(InventoryOpenEvent event)
    {
        if (event.getInventory().getType() == InventoryType.ENCHANTING)
        {
            if (event.getPlayer() instanceof Player)
            {
                checkAndPrevent(event, (Player)event.getPlayer());
            }
        }
    }
}

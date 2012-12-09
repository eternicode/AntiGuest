package de.cubeisland.AntiGuest.prevention.preventions;

import de.cubeisland.AntiGuest.prevention.Prevention;
import de.cubeisland.AntiGuest.prevention.PreventionPlugin;
import org.bukkit.entity.Hanging;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ItemFramePrevention extends Prevention
{
    public ItemFramePrevention(PreventionPlugin plugin)
    {
        super("itemframe", plugin);
        setEnableByDefault(true);
        setEnablePunishing(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void interactEntity(PlayerInteractEntityEvent event)
    {
        if (event.getRightClicked() instanceof Hanging)
        {
            checkAndPrevent(event, event.getPlayer());
        }
    }
}

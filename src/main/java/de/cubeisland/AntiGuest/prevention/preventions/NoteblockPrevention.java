package de.cubeisland.AntiGuest.prevention.preventions;

import de.cubeisland.AntiGuest.prevention.Prevention;
import de.cubeisland.AntiGuest.prevention.PreventionPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Prevents noteblock usage
 *
 * @author Phillip Schichtel
 */
public class NoteblockPrevention extends Prevention
{
    public NoteblockPrevention(PreventionPlugin plugin)
    {
        super("noteblock", plugin);
        setEnablePunishing(true);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void handle(PlayerInteractEvent event)
    {
        final Action action = event.getAction();
        if (action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_BLOCK)
        {
            if (event.getClickedBlock().getType() == Material.NOTE_BLOCK)
            {
                prevent(event, event.getPlayer());
            }
        }
    }
}
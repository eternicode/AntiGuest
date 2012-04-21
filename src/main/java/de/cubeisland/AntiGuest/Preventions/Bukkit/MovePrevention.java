package de.cubeisland.AntiGuest.Preventions.Bukkit;

import de.codeinfection.Util.Vector2D;
import de.cubeisland.AntiGuest.Convert;
import de.cubeisland.AntiGuest.Prevention;
import de.cubeisland.AntiGuest.PreventionPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

/**
 * Prevents movement
 *
 * @author Phillip Schichtel
 */
public class MovePrevention extends Prevention
{
    private int radius;

    public MovePrevention(PreventionPlugin plugin)
    {
        super("move", plugin);
    }

    @Override
    public Configuration getDefaultConfig()
    {
        Configuration config = super.getDefaultConfig();

        config.set("messageDelay", 3);
        config.set("radius", Math.max(5, getPlugin().getServer().getSpawnRadius()));

        return config;
    }
    
    @Override
    public void enable()
    {
        super.enable();
        this.radius = getConfig().getInt("radius");
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void handle(PlayerMoveEvent event)
    {
        final Player player = event.getPlayer();
        if (!can(player))
        {
            final Location toLocation = event.getTo();
            final Location spawnLocation = toLocation.getWorld().getSpawnLocation();
            final Vector2D to = Convert.toVector2D(toLocation);
            final Vector2D spawn = Convert.toVector2D(spawnLocation);

            if (this.radius / spawn.distance(to) < 1)
            {
                sendThrottledMessage(player);
                event.setCancelled(true);
                
                final Vector2D from = Convert.toVector2D(player.getLocation());
                // i bit less then 1 because of the inaccurate from location
                if (this.radius / spawn.distance(from) <= 0.98)
                {
                    // teleportation scheduled for the next tick to prevent kick (moved too fast)
                    player.getServer().getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable()
                    {
                        public void run()
                        {
                            player.teleport(spawnLocation, TeleportCause.PLUGIN);
                        }
                    });
                }
            }
        }
    }
}

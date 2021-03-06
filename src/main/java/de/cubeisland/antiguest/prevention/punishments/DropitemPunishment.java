package de.cubeisland.antiguest.prevention.punishments;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import de.cubeisland.antiguest.prevention.Punishment;

/**
 * Drops the player players held item
 *
 * @author Phillip Schichtel
 */
public class DropitemPunishment implements Punishment
{
    public String getName()
    {
        return "dropitem";
    }

    public void punish(Player player, ConfigurationSection config)
    {
        player.getWorld().dropItemNaturally(player.getLocation(), player.getInventory().getItemInMainHand())
              .setPickupDelay(config.getInt("pickupDelay", 4) * 20);
        player.getInventory().clear(player.getInventory().getHeldItemSlot());
    }
}

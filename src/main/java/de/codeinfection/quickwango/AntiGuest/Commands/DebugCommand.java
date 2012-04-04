package de.codeinfection.quickwango.AntiGuest.Commands;

import de.codeinfection.quickwango.AntiGuest.AbstractCommand;
import de.codeinfection.quickwango.AntiGuest.AntiGuestBukkit;
import de.codeinfection.quickwango.AntiGuest.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * This command toggles the debug mode
 *
 * @author Phillip Schichtel
 */
public class DebugCommand extends AbstractCommand
{
    public DebugCommand(BaseCommand base)
    {
        super("debug", base);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args)
    {
        AntiGuestBukkit.debugMode = !AntiGuestBukkit.debugMode;
        if (AntiGuestBukkit.debugMode)
        {
            sender.sendMessage(ChatColor.GREEN + "Debug mode is now enabled!");
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Debug mode is now disabled!");
        }

        return true;
    }

    @Override
    public String getDescription()
    {
        return "Toggles the debug mode of antiguest.";
    }
}

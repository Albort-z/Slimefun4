package io.github.thebusybiscuit.slimefun4.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun4.core.services.localization.Language;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;

/**
 * This class represents a {@link SubCommand}, it is a {@link Command} that starts with
 * {@code /sf ...} and is followed by the name of this {@link SubCommand}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see SlimefunCommand
 *
 */
public abstract class SubCommand {

    protected final SlimefunPlugin plugin;
    protected final SlimefunCommand cmd;

    protected SubCommand(SlimefunPlugin plugin, SlimefunCommand cmd) {
        this.plugin = plugin;
        this.cmd = cmd;
    }

    public abstract String getName();

    public abstract void onExecute(CommandSender sender, String[] args);

    protected String getDescriptionPath() {
        return "commands." + getName();
    }

    /**
     * This returns a description for this {@link SubCommand}.
     * If the given {@link CommandSender} is a {@link Player}, the description
     * will be localized with the currently selected {@link Language} of that {@link Player}.
     * 
     * @param sender
     *            The {@link CommandSender} who requested the description
     * @return A possibly localized description of this {@link SubCommand}
     */
    public String getDescription(CommandSender sender) {
        if (sender instanceof Player) {
            return SlimefunPlugin.getLocal().getMessage((Player) sender, getDescriptionPath());
        }
        else {
            return SlimefunPlugin.getLocal().getMessage(getDescriptionPath());
        }
    }

}

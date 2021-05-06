package de.jonas.scoreboard.command;

import de.jonas.Scoreboard;
import de.jonas.scoreboard.object.CommandCheck;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command command,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandCheck commandCheck = new CommandCheck(sender, args);

        if (!commandCheck.check(
            0,
            0,
            "scoreboard.reload",
            "/sbrl"
        )) {
            return true;
        }

        Scoreboard.getInstance().initialize();

        commandCheck.getPlayer().sendMessage(
            Scoreboard.getPrefix() + "Das Scoreboard mitsamt Config wurde neu geladen!"
        );
        return true;
    }
}

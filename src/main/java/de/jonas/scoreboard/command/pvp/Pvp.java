package de.jonas.scoreboard.command.pvp;

import de.jonas.Scoreboard;
import de.jonas.scoreboard.handler.PvpHandler;
import de.jonas.scoreboard.object.CommandCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class Pvp implements CommandExecutor {
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
            1,
            "scoreboard.pvp",
            "/pvp"
        )) {
            return true;
        }

        final Player player = commandCheck.getPlayer();

        if (args.length == 0) {
            final PvpHandler pvpHandler = new PvpHandler(player);
            player.sendMessage(Scoreboard.getPrefix() + "Deine PVP-Daten:");
            player.sendMessage(Scoreboard.getPrefix() + "Tode: " + pvpHandler.getDeaths());
            player.sendMessage(Scoreboard.getPrefix() + "Kills: " + pvpHandler.getKills());
            return true;
        }

        if (!player.hasPermission("scoreboard.economy.other")) {
            player.sendMessage(Scoreboard.getPrefix() + "Dazu hast du keine Rechte!");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(Scoreboard.getPrefix() + "Der Spieler ist nicht online!");
            return true;
        }

        final PvpHandler pvpHandler = new PvpHandler(target);

        player.sendMessage(Scoreboard.getPrefix() + "Die PVP-Daten von " + target.getName() + ":");
        player.sendMessage(Scoreboard.getPrefix() + "Tode: " + pvpHandler.getDeaths());
        player.sendMessage(Scoreboard.getPrefix() + "Kills: " + pvpHandler.getKills());
        return true;
    }
}

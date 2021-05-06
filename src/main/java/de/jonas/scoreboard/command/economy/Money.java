package de.jonas.scoreboard.command.economy;

import de.jonas.Scoreboard;
import de.jonas.scoreboard.handler.ConfigurationHandler;
import de.jonas.scoreboard.handler.EconomyHandler;
import de.jonas.scoreboard.object.CommandCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class Money implements CommandExecutor {
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
            "economy.money",
            "/money"
        )) {
            return true;
        }

        final Player player = commandCheck.getPlayer();

        if (args.length == 0) {
            final EconomyHandler economyHandler = new EconomyHandler(player);
            final int money = economyHandler.getEconomy();
            player.sendMessage(
                Scoreboard.getPrefix() + "Dein Kontostand beträgt " + money + ConfigurationHandler.getCurrency() + "!"
            );
            return true;
        }

        if (!player.hasPermission("economy.money.other")) {
            player.sendMessage(Scoreboard.getPrefix() + "Dazu hast du keine Rechte!");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(Scoreboard.getPrefix() + "Der Spieler ist nicht online!");
            return true;
        }

        final EconomyHandler economyHandler = new EconomyHandler(player);
        final int money = economyHandler.getEconomy();

        player.sendMessage(
            Scoreboard.getPrefix() + "Der Kontostand von " + target.getName() + " beträgt " + money
                + ConfigurationHandler.getCurrency() + "!"
        );
        return true;
    }
}

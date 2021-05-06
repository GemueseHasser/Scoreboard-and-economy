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

public final class Pay implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command command,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandCheck commandCheck = new CommandCheck(sender, args);

        if (!commandCheck.check(
            2,
            2,
            "economy.pay",
            "/pay <player> <amount>"
        )) {
            return true;
        }

        final Player player = commandCheck.getPlayer();
        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(Scoreboard.getPrefix() + "Der Spieler ist nicht online!");
            return true;
        }

        final int amount;

        try {
            amount = Integer.parseInt(args[1]);
        } catch (@NotNull final IllegalArgumentException ignored) {
            player.sendMessage(Scoreboard.getPrefix() + "Bitte wähle einen gültigen Betrag!");
            return true;
        }

        final EconomyHandler economyHandlerPlayer = new EconomyHandler(player);
        final EconomyHandler economyHandlerTarget = new EconomyHandler(target);

        final int moneyPlayer = economyHandlerPlayer.getEconomy();
        final int moneyTarget = economyHandlerTarget.getEconomy();

        economyHandlerPlayer.setEconomy(moneyPlayer - amount);
        economyHandlerTarget.setEconomy(moneyTarget + amount);

        player.sendMessage(Scoreboard.getPrefix() + "Du hast dem Spieler " + target.getName()
            + amount + ConfigurationHandler.getCurrency() + " überwiesen!");
        target.sendMessage(Scoreboard.getPrefix() + "Der Spieler " + player.getName()
            + " hat dir einen Betrag von " + amount + ConfigurationHandler.getCurrency() + " überwiesen!");
        return true;
    }
}

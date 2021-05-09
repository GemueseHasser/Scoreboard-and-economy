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

/**
 * Es wird der Befehl implementiert, womit man die Ökonomie eines bestimmten Spielers editieren kann.
 */
public final class Economy implements CommandExecutor {
    //<editor-fold desc="implementation">
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command command,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandCheck commandCheck = new CommandCheck(sender, args);

        final String usage = "/economy <add|remove|set> <player> <amount>";

        if (commandCheck.isNotCorrect(
            3,
            3,
            "economy.admin",
            usage
        )) {
            return true;
        }

        final Player player = commandCheck.getPlayer();

        final Player target = Bukkit.getPlayer(args[1]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(Scoreboard.getPrefix() + "Der Spieler ist nicht online!");
            return true;
        }

        final EconomyHandler economyHandler = new EconomyHandler(target);
        final int amount;

        try {
            amount = Integer.parseInt(args[2]);
        } catch (@NotNull final IllegalArgumentException ignored) {
            player.sendMessage(Scoreboard.getPrefix() + "Bitte wähle einen gültigen Betrag!");
            return true;
        }

        switch (args[0]) {
            case "add":
                economyHandler.setEconomy(economyHandler.getEconomy() + amount);
                player.sendMessage(
                    Scoreboard.getPrefix() + "Der Kontostand von " + target.getName() + " wurde um " + amount
                        + ConfigurationHandler.getCurrency() + " erhöht. "
                        + "(aktuell bei " + economyHandler.getEconomy() + ConfigurationHandler.getCurrency() + ")"
                );
                break;

            case "remove":
                economyHandler.setEconomy(economyHandler.getEconomy() - amount);
                player.sendMessage(
                    Scoreboard.getPrefix() + "Der Kontostand von " + target.getName() + " wurde um " + amount
                        + ConfigurationHandler.getCurrency() + " verringert. "
                        + "(aktuell bei " + economyHandler.getEconomy() + ConfigurationHandler.getCurrency() + ")"
                );
                break;

            case "set":
                economyHandler.setEconomy(amount);
                player.sendMessage(
                    Scoreboard.getPrefix() + "Der Kontostand von " + target.getName() + " wurde auf " + amount
                        + ConfigurationHandler.getCurrency() + " gesetzt."
                );
                break;

            default:
                player.sendMessage(Scoreboard.getPrefix() + "Bitte benutze " + usage);
                break;
        }
        return true;
    }
    //</editor-fold>
}

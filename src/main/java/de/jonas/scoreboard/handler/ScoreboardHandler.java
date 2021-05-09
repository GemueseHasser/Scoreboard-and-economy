package de.jonas.scoreboard.handler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe des {@link ScoreboardHandler} lässt sich das Scoreboard für einen bestimmten Spieler setzen. Es werden die
 * Platzhalter '%money', '%kills%' und '%deaths%' genutzt, um die Ökonomie, die Anzahl an Kills und die Anzahl an Toden
 * zu übergeben.
 */
public final class ScoreboardHandler {

    /**
     * Setzt das in der Konfigurations-datei voreingestellte Scoreboard für einen bestimmten Spieler. Es werden die
     * Platzhalter '%money', '%kills%' und '%deaths%' genutzt, um die Ökonomie, die Anzahl an Kills und die Anzahl an
     * Toden zu übergeben.
     *
     * @param player Der Spieler, für den das Scoreboard gesetzt werden soll.
     */
    public static void setScoreboard(@NotNull final Player player) {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        final Objective objective = scoreboard.registerNewObjective("abcde", "abcde");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(
            ChatColor.translateAlternateColorCodes(
                '&',
                ConfigurationHandler.getTitle()
            )
        );

        final EconomyHandler economyHandler = new EconomyHandler(player);
        final PvpHandler pvpHandler = new PvpHandler(player);

        for (int i = 0; i < ConfigurationHandler.getScoreboard().size(); i++) {
            objective.getScore(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    ConfigurationHandler
                        .getScoreboard()
                        .get(i)
                        .replace("%money%", economyHandler.getEconomy() + ConfigurationHandler.getCurrency())
                        .replace("%kills%", pvpHandler.getKills() + "")
                        .replace("%deaths%", pvpHandler.getDeaths() + "")
                )).setScore(ConfigurationHandler.getScoreboard().size() - i);
        }

        player.setScoreboard(scoreboard);
    }

}

package de.jonas.scoreboard.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

public final class ScoreboardHandler {

    public static void setScoreboard(@NotNull final Player player) {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        final Objective objective = scoreboard.registerNewObjective("abcde", "abcde");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ConfigurationHandler.getTitle());

        final EconomyHandler economyHandler = new EconomyHandler(player);
        final PvpHandler pvpHandler = new PvpHandler(player);

        for (int i = ConfigurationHandler.getScoreboard().size(); i > 0; i--) {
            objective.getScore(
                ConfigurationHandler
                    .getScoreboard()
                    .get(i)
                    .replace("%money%", economyHandler.getEconomy() + "")
                    .replace("%kills%", pvpHandler.getKills() + "")
                    .replace("%deaths%", pvpHandler.getDeaths() + "")
            ).setScore(i);
        }

        player.setScoreboard(scoreboard);
    }

}

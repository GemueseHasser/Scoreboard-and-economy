package de.jonas.scoreboard.task;

import de.jonas.scoreboard.handler.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public final class ScoreboardUpdatingTask extends BukkitRunnable {

    @Override
    public void run() {
        for (@NotNull final Player all : Bukkit.getOnlinePlayers()) {
            ScoreboardHandler.setScoreboard(all);
        }
    }

}

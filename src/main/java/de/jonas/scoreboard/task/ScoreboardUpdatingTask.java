package de.jonas.scoreboard.task;

import de.jonas.scoreboard.handler.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

/**
 * Der {@link ScoreboardUpdatingTask} stellt eine sich konstant wiederholende Prozedur dar, womit in einem in der
 * Konfigurations-Datei festgelegten Zeitlichen Abstand, das Scoreboard eines jeden Spielers auf dem Server aktualisiert
 * wird.
 */
public final class ScoreboardUpdatingTask extends BukkitRunnable {

    //<editor-fold desc="runnable">
    @Override
    public void run() {
        for (@NotNull final Player all : Bukkit.getOnlinePlayers()) {
            // set scoreboard for all players
            ScoreboardHandler.setScoreboard(all);
        }
    }
    //</editor-fold>

}

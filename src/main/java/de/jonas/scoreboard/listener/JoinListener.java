package de.jonas.scoreboard.listener;

import de.jonas.scoreboard.handler.EconomyHandler;
import de.jonas.scoreboard.handler.ScoreboardHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe des {@link JoinListener} wird jedem Spieler bei Betreten des Servers das Scoreboard gesetzt. Zudem wird die
 * Ökonomie des Spieler initialisiert, zufalls er noch keine hat.
 */
public final class JoinListener implements Listener {

    //<editor-fold desc="listener: onJoin">
    @EventHandler
    public void onJoin(@NotNull final PlayerJoinEvent e) {
        // check economy
        final EconomyHandler economyHandler = new EconomyHandler(e.getPlayer());
        economyHandler.check();

        // set scoreboard
        ScoreboardHandler.setScoreboard(e.getPlayer());
    }
    //</editor-fold>

}

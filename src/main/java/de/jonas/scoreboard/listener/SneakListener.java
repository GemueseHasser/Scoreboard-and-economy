package de.jonas.scoreboard.listener;

import de.jonas.scoreboard.handler.ScoreboardHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe des {@link SneakListener} wird überprüft, ob ein Spieler Sneakt und zufalls dies so ist, wird das Scoreboard
 * dieses Spielers aktualisiert.
 */
public final class SneakListener implements Listener {

    //<editor-fold desc="listener: onSneak">
    @EventHandler
    public void onSneak(@NotNull final PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) {
            return;
        }

        ScoreboardHandler.setScoreboard(e.getPlayer());
    }
    //</editor-fold>

}

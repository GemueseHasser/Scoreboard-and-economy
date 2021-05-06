package de.jonas.scoreboard.listener;

import de.jonas.scoreboard.handler.ScoreboardHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.jetbrains.annotations.NotNull;

public final class SneakListener implements Listener {

    @EventHandler
    public void onSneak(@NotNull final PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) {
            return;
        }

        ScoreboardHandler.setScoreboard(e.getPlayer());
    }

}

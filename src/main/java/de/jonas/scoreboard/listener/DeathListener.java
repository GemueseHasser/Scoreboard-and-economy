package de.jonas.scoreboard.listener;

import de.jonas.scoreboard.handler.PvpHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Mithilfe des {@link DeathListener} werden die Tode und Kills eines Spielers gehandhabt und verarbeitet.
 */
public final class DeathListener implements Listener {

    //<editor-fold desc="listener: onDeath">
    @EventHandler
    public void onDeath(@NotNull final PlayerDeathEvent e) {
        final Player player = e.getEntity();

        final PvpHandler pvpHandlerPlayer = new PvpHandler(player);
        pvpHandlerPlayer.incrementDeaths();

        if (e.getEntity().getKiller() == null) {
            return;
        }

        final Player killer = e.getEntity().getKiller();

        final PvpHandler pvpHandlerKiller = new PvpHandler(killer);
        pvpHandlerKiller.incrementKills();
    }
    //</editor-fold>

}

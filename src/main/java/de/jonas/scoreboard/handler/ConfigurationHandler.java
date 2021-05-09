package de.jonas.scoreboard.handler;

import de.jonas.Scoreboard;
import lombok.Getter;

import java.util.List;

/**
 * Mithilfe des {@link ConfigurationHandler}, lassen sich die Werte aus der Konfigurations-Datei neu initialiseren bzw
 * neu laden. Diese werden hiermit einmal neu geladen und dann kann immer wieder auf diese geladenen Werte zugegriffen
 * werden, damit nicht ständig die Datei neu gelsesen werden muss und das Plugin nicht zu unperformant wird.
 */
public final class ConfigurationHandler {

    //<editor-fold desc="STATIC FIELDS">
    /** Die Währung, welche das Ökonomie-System haben soll. */
    @Getter
    private static String currency;
    /** Der Betrag, den ein Spieler bekommt, sobald er das Netzwerk zum ersten Mal betritt. */
    @Getter
    private static int economyStart;
    /** Der Zustand, ob das Scoreboard konstant über einen Task aktualisiert werden soll. */
    @Getter
    private static boolean shouldUpdate;
    /** Der Abstand in Sekunden, in dem das Scoreboard konstant aktualisiert werden soll. */
    @Getter
    private static int updatePeriod;
    /** Der Titel des Scoreboard. */
    @Getter
    private static String title;
    /** Die einzelnen Zeilen des Scoreboard (gefasst in einer Liste). */
    @Getter
    private static List<String> scoreboard;
    //</editor-fold>

    //<editor-fold desc="initialising">

    /**
     * Alle Werte werden aus der Konfigurations-Datei geladen und initialisiert.
     */
    public static void initialize() {
        currency = Scoreboard.getInstance().getConfig().getString("Config.Currency");
        economyStart = Scoreboard.getInstance().getConfig().getInt("Config.economyStart");
        shouldUpdate = Scoreboard.getInstance().getConfig().getBoolean("Config.SchedulePeriodicScoreboardUpdating");
        updatePeriod = Scoreboard.getInstance().getConfig().getInt("Config.ScoreboardUpdatePeriodInSeconds");
        title = Scoreboard.getInstance().getConfig().getString("Config.Title");
        scoreboard = Scoreboard.getInstance().getConfig().getStringList("Config.Scoreboard");
    }
    //</editor-fold>

}

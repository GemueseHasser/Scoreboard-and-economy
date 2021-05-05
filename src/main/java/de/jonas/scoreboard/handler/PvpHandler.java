package de.jonas.scoreboard.handler;

import de.jonas.Scoreboard;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Mithilfe des {@link PvpHandler} lassen sich die PVP-Daten eines bestimmten Spielers abfragen und inkrementieren.
 */
public final class PvpHandler {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der Basis-Pfad, unter dem alle PVP-Daten gespeichert werden. */
    private final String basicPath;
    /** Die Datei, in der alle PVP-Daten gespeichert werden. */
    private final File file;
    /** Die Konfiguration, mit der sich die Datei editieren lässt. */
    private final YamlConfiguration cfg;

    /** Die Anzahl an Toden des Spielers. */
    @Getter
    private int deaths;
    /** Die Anzahl an Kills des Spielers. */
    @Getter
    private int kills;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz des {@link PvpHandler}.Mithilfe des {@link PvpHandler}
     * lassen sich die PVP-Daten eines bestimmten Spielers abfragen und inkrementieren. Ein {@link PvpHandler} wird
     * mithilfe eines Spielers erzeugt, da ein {@link PvpHandler} aufbauend auf einen Spieler erzeugt wird und auch nur
     * die Daten eines bestimmten Spielers geladen werden.
     *
     * @param player Der Spieler, für den der {@link PvpHandler} instanziiert wird.
     */
    public PvpHandler(@NotNull final Player player) {
        this.basicPath = "PVP." + player.getUniqueId().toString() + ".";

        this.file = new File(Scoreboard.PLUGIN_PATH, "pvp.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);

        this.loadPersonalData();
    }
    //</editor-fold>


    //<editor-fold desc="data: loading">

    /**
     * Lädt die persönlichen Daten des Spielers.
     */
    public void loadPersonalData() {
        this.deaths = this.cfg.getInt(this.basicPath + "Deaths");
        this.kills = this.cfg.getInt(this.basicPath + "Kills");
    }
    //</editor-fold>

    //<editor-fold desc="data: editing">

    /**
     * Erhöht die Kills des Spielers um 1.
     */
    @SneakyThrows
    public void incrementKills() {
        this.cfg.set(this.basicPath + "Kills", this.kills + 1);
        this.cfg.save(file);
    }

    /**
     * Erhöht die Tode des Spielers um 1.
     */
    @SneakyThrows
    public void incrementDeaths() {
        this.cfg.set(this.basicPath + "Deaths", this.deaths + 1);
        this.cfg.save(file);
    }
    //</editor-fold>

}

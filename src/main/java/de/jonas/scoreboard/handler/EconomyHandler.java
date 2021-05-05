package de.jonas.scoreboard.handler;

import de.jonas.Scoreboard;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.io.File;

/**
 * Mithilfe des {@link EconomyHandler} lässt sich die Ökonomie eines bestimmten Spielers verwalten und editieren.
 */
public final class EconomyHandler {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der Pfad, unter dem die Ökonomie des Spielers gespeichert wird. */
    private final String path;
    /** Die Datei, in der die Ökonomie gespeichert wird. */
    private final File file;
    /** Die Konfiguration, womit die Ökonomie editiert und verwaltet werden kann. */
    private final YamlConfiguration cfg;

    /** Die Ökonomie des Spielers. */
    @Getter
    private int economy;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe eines Spielers eine neue und vollständig unabhängige Instanz des {@link EconomyHandler}. Ein
     * {@link EconomyHandler} wird aufbauend auf einen Spieler instanziiert, da nur die persönliche Ökonomie eines
     * Spielers verwaltet und editiert werden kann.
     *
     * @param player Der Spieler, für den der {@link EconomyHandler} instanziiert wird.
     */
    public EconomyHandler(@NotNull final Player player) {
        this.path = "Economy." + player.getUniqueId().toString();

        this.file = new File(Scoreboard.PLUGIN_PATH, "economy.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);

        this.economy = cfg.getInt(this.path);
    }
    //</editor-fold>


    //<editor-fold desc="data: editing">

    /**
     * Setzt den Kontostand des Spielers neu.
     *
     * @param economy Der neue Kontostand, der gesetzt werden soll.
     */
    @SneakyThrows
    public void setEconomy(@Range(from = 0, to = Integer.MAX_VALUE) final int economy) {
        this.economy = economy;
        this.cfg.set(this.path, this.economy);
        this.cfg.save(file);
    }
    //</editor-fold>

}

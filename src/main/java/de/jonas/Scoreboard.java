package de.jonas;

import de.jonas.scoreboard.command.Reload;
import de.jonas.scoreboard.command.economy.Economy;
import de.jonas.scoreboard.command.economy.Money;
import de.jonas.scoreboard.command.economy.Pay;
import de.jonas.scoreboard.command.pvp.Pvp;
import de.jonas.scoreboard.handler.ConfigurationHandler;
import de.jonas.scoreboard.listener.DeathListener;
import de.jonas.scoreboard.listener.JoinListener;
import de.jonas.scoreboard.listener.SneakListener;
import de.jonas.scoreboard.task.ScoreboardUpdatingTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>Die Haupt- und Main-Klasse des Scoreboard-Plugins. Da diese Klasse von einem {@link JavaPlugin} erbt,
 * implementiert sie die Schnittstelle zu dem Server und es werden die Methoden implementiert, die beim Aktivieren und
 * Deaktivieren des Plugins aufgerufen werden. Diese Methoden sind zwingend nötig, da ohne jene Methoden wäre das Plugin
 * nicht funktionsfähig, da keine exakte und einheitliche Schnittstelle für den Server sichtbar wäre, welcher das Plugin
 * lesen und aktivieren soll.</p>
 *
 * <p>Außerdem werden formale Daten wie der Plugin-Prefix initialisiert und die Methode, womit man das Scoreboard
 * mitsamt Konfigurations-Datei neu laden kann.</p>
 */
public class Scoreboard extends JavaPlugin {

    //<editor-fold desc="CONSTANTS">
    /** Der Pfad, unter dem Plugin-spezifische Inhalte bzw. Dateien gespeichert werden. */
    public static final String PLUGIN_PATH = "plugins/Scoreboard";
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die Instanz, womit man auf das {@link Scoreboard} zugreifen kann. */
    @Getter
    private static Scoreboard instance;
    /** Der Prefix des Plugins, welcher vor jeder Text-Ausgabe steht. */
    @Getter
    private static String prefix;
    //</editor-fold>

    //<editor-fold desc="setup and startup">
    @Override
    public void onEnable() {
        // declare instance
        instance = this;

        // declare prefix
        prefix = this.getGeneratedPrefix();

        // load configuration-file
        this.loadConfig();

        // initialize
        this.initialize();

        // register listener
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new SneakListener(), this);
        pm.registerEvents(new JoinListener(), this);

        // register commands
        getCommand("economy").setExecutor(new Economy());
        getCommand("money").setExecutor(new Money());
        getCommand("pay").setExecutor(new Pay());
        getCommand("pvp").setExecutor(new Pvp());
        getCommand("sbrl").setExecutor(new Reload());

        getLogger().info(
            "Das Plugin wurde erfolgreich aktiviert."
        );
    }
    //</editor-fold>

    //<editor-fold desc="disabling">
    @Override
    public void onDisable() {
        getLogger().info(
            "Das Plugin wurde deaktiviert."
        );
    }
    //</editor-fold>

    //<editor-fold desc="basic initialising">

    /**
     * Initialisiert das Scoreboard für jeden Spieler neu und lädt die Konfigurations-Datei neu. Diese Methode wird beim
     * Starten des Plugins aufgerufen, aber auch beim aktualisieren der Konfigurations-Datei.
     */
    public void initialize() {
        // load configuration-data
        ConfigurationHandler.initialize();

        // cancel all tasks from this plugin
        Bukkit.getScheduler().cancelTasks(this);

        if (!ConfigurationHandler.isShouldUpdate()) {
            return;
        }

        // schedule periodic scoreboard updating
        new ScoreboardUpdatingTask().runTaskTimer(
            this,
            20,
            ConfigurationHandler.getUpdatePeriod() * 20L
        );
    }
    //</editor-fold>

    /**
     * Lädt die Konfigurations-Datei mitsamt zuvor geänderten Inhalten.
     */
    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    /**
     * Es wird der Plugin-Prefix, der vor jeder Text-Ausgabe steht generiert.
     *
     * @return Der Plugin-Prefix, der vor jeder Text-Ausgabe steht.
     */
    private String getGeneratedPrefix() {
        return ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "["
            + ChatColor.GOLD + "Scoreboard"
            + ChatColor.DARK_GRAY + ChatColor.BOLD + "]"
            + ChatColor.GRAY + " ";
    }

}

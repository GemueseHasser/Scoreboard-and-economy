package de.jonas;

import de.jonas.scoreboard.handler.ConfigurationHandler;
import de.jonas.scoreboard.listener.DeathListener;
import de.jonas.scoreboard.task.ScoreboardUpdatingTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Scoreboard extends JavaPlugin {

    public static final String PLUGIN_PATH = "plugins/Scoreboard";

    @Getter
    private static Scoreboard instance;

    @Getter
    private static String prefix;

    @Override
    public void onEnable() {
        // declare instance
        instance = this;

        // declare prefix
        prefix = getGeneratedPrefix();

        // load configuration-file
        loadConfig();

        // load configuration-data
        ConfigurationHandler.initialize();

        // register listener
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DeathListener(), this);

        // schedule periodic scoreboard updating
        new ScoreboardUpdatingTask().runTaskTimer(
            this,
            20,
            ConfigurationHandler.getUpdatePeriod()
        );
    }

    @Override
    public void onDisable() {

    }

    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    private String getGeneratedPrefix() {
        return ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "["
            + ChatColor.GOLD + "Scoreboard"
            + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "]";
    }

}

package de.jonas;

import de.jonas.scoreboard.command.Reload;
import de.jonas.scoreboard.command.economy.Economy;
import de.jonas.scoreboard.command.economy.Money;
import de.jonas.scoreboard.command.economy.Pay;
import de.jonas.scoreboard.command.pvp.Pvp;
import de.jonas.scoreboard.handler.ConfigurationHandler;
import de.jonas.scoreboard.listener.DeathListener;
import de.jonas.scoreboard.listener.SneakListener;
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

    private ScoreboardUpdatingTask scoreboardUpdatingTask;

    @Override
    public void onEnable() {
        // declare instance
        instance = this;

        // declare prefix
        prefix = getGeneratedPrefix();

        // load configuration-file
        this.loadConfig();

        // initialize task
        this.scoreboardUpdatingTask = new ScoreboardUpdatingTask();

        // initialize
        this.initialize();

        // register listener
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new SneakListener(), this);

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

    @Override
    public void onDisable() {
        getLogger().info(
            "Das Plugin wurde deaktiviert."
        );
    }

    public void initialize() {
        // load configuration-data
        ConfigurationHandler.initialize();

        // get task-id from scoreboard-updating-task
        final int taskId = this.scoreboardUpdatingTask.getTaskId();

        // check if task is already running
        if (Bukkit.getScheduler().isCurrentlyRunning(taskId)) {

            // check if periodic updating is enabled in the config
            if (!ConfigurationHandler.isShouldUpdate()) {
                Bukkit.getScheduler().cancelTask(taskId);
                return;
            }

            return;
        }

        // check if periodic updating is enabled in the config
        if (!ConfigurationHandler.isShouldUpdate()) {
            return;
        }

        // schedule periodic scoreboard updating
        this.scoreboardUpdatingTask.runTaskTimer(
            this,
            20,
            ConfigurationHandler.getUpdatePeriod() * 20L
        );
    }

    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    private String getGeneratedPrefix() {
        return ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "["
            + ChatColor.GOLD + "Scoreboard"
            + ChatColor.DARK_GRAY + ChatColor.BOLD + "]"
            + ChatColor.GRAY + " ";
    }

}

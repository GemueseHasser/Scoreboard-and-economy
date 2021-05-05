package de.jonas;

import lombok.Getter;
import org.bukkit.ChatColor;
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
    }

    @Override
    public void onDisable() {

    }

    private String getGeneratedPrefix() {
        return ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "["
            + ChatColor.GOLD + "Scoreboard"
            + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "]";
    }

}

package de.jonas.scoreboard.handler;

import de.jonas.Scoreboard;
import lombok.Getter;

import java.util.List;

public final class ConfigurationHandler {

    @Getter
    private static int updatePeriod;
    @Getter
    private static String title;
    @Getter
    private static List<String> scoreboard;

    public static void initialize() {
        updatePeriod = Scoreboard.getInstance().getConfig().getInt("Config.ScoreboardUpdatePeriodInSeconds");
        title = Scoreboard.getInstance().getConfig().getString("Config.Title");
        scoreboard = Scoreboard.getInstance().getConfig().getStringList("Config.Scoreboard");
    }

}

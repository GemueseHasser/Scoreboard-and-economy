package de.jonas.scoreboard.object;

import de.jonas.Scoreboard;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class CommandCheck {

    private final CommandSender sender;
    private final String[] args;

    @Getter
    private Player player;

    public CommandCheck(
        @NotNull final CommandSender sender,
        @NotNull final String[] args
    ) {
        this.sender = sender;
        this.args = args;
    }

    public boolean check(
        @Range(from = 0, to = Integer.MAX_VALUE) final int minLength,
        @Range(from = 0, to = Integer.MAX_VALUE) final int maxLength,
        @NotNull final String permission,
        @NotNull final String usage
    ) {
        if (!(this.sender instanceof Player)) {
            this.sender.sendMessage(Scoreboard.getPrefix() + "Du musst ein Spieler sein!");
            return false;
        }

        this.player = (Player) sender;

        if (!this.player.hasPermission(permission)) {
            this.player.sendMessage(Scoreboard.getPrefix() + "Dazu hast du keine Rechte!");
            return false;
        }

        if (!(this.args.length >= minLength && this.args.length <= maxLength)) {
            this.player.sendMessage(Scoreboard.getPrefix() + "Bitte benutze " + usage);
            return false;
        }

        return true;
    }

}

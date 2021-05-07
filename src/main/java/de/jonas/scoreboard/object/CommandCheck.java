package de.jonas.scoreboard.object;

import de.jonas.Scoreboard;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Mithilfe des {@link CommandCheck} kann man die Abfragen eines Befehls vereinfachen. So kann man in einer Abfrage
 * überprüfen, ob der Befehls-Sender ein Spieler ist, ob der Spieler die nötigen Rechte hat und ob die Argumenten-Länge
 * stimmt.
 */
public final class CommandCheck {

    //<editor-fold desc="LOCAL FIELDS">
    /** Der Befehls-Sender, der den Befehl ausgeführt hat. */
    private final CommandSender sender;
    /** Die Argumente, die auf den Basis-Befehl folgen. */
    private final String[] args;

    /** Der Spieler, der den Befehl ausgeführt hat (zufalls es ein Spieler war). */
    @Getter
    private Player player;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt mithilfe eines Befehls-Senders und einem Array aus Argumenten eine neue und vollständig unabhängige
     * Instanz des {@link CommandCheck}. Mithilfe des {@link CommandCheck} kann man die Abfragen eines Befehls
     * vereinfachen. So kann man in einer Abfrage überprüfen, ob der Befehls-Sender ein Spieler ist, ob der Spieler die
     * nötigen Rechte hat und ob die Argumenten-Länge stimmt.
     *
     * @param sender Der Befehls-Sender, der den Befehl ausgeführt hat.
     * @param args   Die Argumente, die auf den Basis-Befehl folgen.
     */
    public CommandCheck(
        @NotNull final CommandSender sender,
        @NotNull final String[] args
    ) {
        this.sender = sender;
        this.args = args;
    }
    //</editor-fold>


    //<editor-fold desc="checking">

    /**
     * Überprüft den Befehl (bzw dessen Angaben), ob sie korrekt sind und der Befehl ausgeführt werden kann. Zuerst wird
     * geprüft, ob der Befehl von einem Spieler ausgeführt wurde, dann wird geprüft, ob der Spieler, der den Befehl
     * ausgeführt hat die nötigen Rechte hat und zuletzt wird geprüft, ob die Argumenten-Länge des Befehls stimmt.
     * Zufalls dies alles zutrifft, ist der Befehl (bzw dessen Angaben) korrekt, ansonsten nicht.
     *
     * @param minLength  Die minimale Argumenten-Länge, die mit der Argumenten-Länge des Befehls abgeglichen wird.
     * @param maxLength  Die maximale Argumenten-Länge, die mit der Argumenten-Länge des Befehls abgeglichen wird.
     * @param permission Die Rechte, die dafür nötig sind, diesen Befehl auszuführen.
     * @param usage      Die korrekte Benutzung des Befehls.
     *
     * @return Wenn die Angaben des Befehls korrekt sind, {@code true}, ansonsten {@code false}.
     */
    public boolean isNotCorrect(
        @Range(from = 0, to = Integer.MAX_VALUE) final int minLength,
        @Range(from = 0, to = Integer.MAX_VALUE) final int maxLength,
        @NotNull final String permission,
        @NotNull final String usage
    ) {
        if (!(this.sender instanceof Player)) {
            this.sender.sendMessage(Scoreboard.getPrefix() + "Du musst ein Spieler sein!");
            return true;
        }

        this.player = (Player) sender;

        if (!this.player.hasPermission(permission)) {
            this.player.sendMessage(Scoreboard.getPrefix() + "Dazu hast du keine Rechte!");
            return true;
        }

        if (!(this.args.length >= minLength && this.args.length <= maxLength)) {
            this.player.sendMessage(Scoreboard.getPrefix() + "Bitte benutze " + usage);
            return true;
        }

        return false;
    }
    //</editor-fold>

}

package net.sacredisle.rpgengine.core.command;

import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.condition.CommandCondition;
import net.sacredisle.rpgengine.api.ICommand;
import net.sacredisle.rpgengine.api.player.IRPGPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * A command that can be executed.
 * Sub-commands can still make use of the default Minestom {@link Command} class.
 */
public abstract class RPGCommand extends Command implements ICommand {

    private boolean playerOnly = false;
    private boolean consoleOnly = false;

    public RPGCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);

        setCondition(new CommandCondition() {
            @Override
            public boolean canUse(@NotNull CommandSender sender, @Nullable String commandString) {
                if (consoleOnly)
                    return !(sender instanceof IRPGPlayer);
                if (playerOnly && (!(sender instanceof IRPGPlayer))) return false;
                if (sender instanceof IRPGPlayer) return hasPermission((IRPGPlayer) sender);
                else return true; /* Console or other source */
            }
        });
    }

    /**
     * Sets whether this command can only be executed by players or not.
     */
    public void setPlayerOnly(boolean playerOnly) {
        this.playerOnly = playerOnly;
        this.consoleOnly = !playerOnly;
    }

    /**
     * Sets whether this command can only be executed by the server console
     * or other command source other than players.
     */
    public void setServerOnly(boolean consoleOnly) {
        this.consoleOnly = consoleOnly;
        this.playerOnly = !consoleOnly;
    }
}

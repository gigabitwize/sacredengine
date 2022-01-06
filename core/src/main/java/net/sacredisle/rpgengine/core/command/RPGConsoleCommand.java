package net.sacredisle.rpgengine.core.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * Represents a {@link RPGCommand} that can only be executed by the console
 * or alternative command sources excluding players.
 */
public abstract class RPGConsoleCommand extends RPGCommand {

    public RPGConsoleCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);
        setServerOnly(true);
    }
}

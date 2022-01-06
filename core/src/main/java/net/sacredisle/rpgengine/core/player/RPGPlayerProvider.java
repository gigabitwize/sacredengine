package net.sacredisle.rpgengine.core.player;

import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.network.player.PlayerConnection;
import net.sacredisle.rpgengine.api.name.Username;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * The default {@link PlayerProvider} for the {@link net.sacredisle.rpgengine.core.RPGEngine}. This provider
 * makes it so that every {@link Player} object can be cast to a {@link RPGPlayer} object and vice versa.
 */
public class RPGPlayerProvider implements PlayerProvider {

    @Override
    public @NotNull Player createPlayer(@NotNull UUID uuid, @NotNull String name, @NotNull PlayerConnection connection) {
        Username username = new Username(uuid, name);
        return new RPGPlayer(username, connection);
    }
}

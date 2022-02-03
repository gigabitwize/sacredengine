package net.sacredisle.engine.player;

import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.network.player.PlayerConnection;
import net.sacredisle.engine.api.name.Name;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Giovanni on 1/11/2022
 * The default {@link PlayerProvider} for the {@link net.sacredisle.engine.SacredEngine}. This provider
 * makes it so that every {@link Player} object can be cast to a {@link RPGPlayer} object and vice versa.
 */
public class RPGPlayerProvider implements PlayerProvider {

    @Override
    public @NotNull Player createPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection connection) {
        return new RPGPlayer(new Name(uuid, username), connection);
    }
}

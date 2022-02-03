package net.sacredisle.engine.player;

import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.sacredisle.engine.api.name.Name;
import net.sacredisle.engine.api.player.IRPGPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/11/2022
 */
public class RPGPlayer extends Player implements IRPGPlayer {

    private final Name name;

    public RPGPlayer(@NotNull Name name, @NotNull PlayerConnection connection) {
        super(name.getUniqueId(), name.getName(), connection);
        this.name = name;
    }

    @Override
    public Name getNameData() {
        return name;
    }
}

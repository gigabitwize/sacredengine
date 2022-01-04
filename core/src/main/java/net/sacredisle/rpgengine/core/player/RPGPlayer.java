package net.sacredisle.rpgengine.core.player;

import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.sacredisle.rpgengine.api.name.Username;
import net.sacredisle.rpgengine.api.player.IRPGPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/4/2022
 */
public class RPGPlayer extends Player implements IRPGPlayer {

    private final Username username;
    private String displayName;

    public RPGPlayer(Username username, @NotNull PlayerConnection playerConnection) {
        super(username.getId(), username.getLastName(), playerConnection);
        this.username = username;
        this.displayName = username.getLastName();
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public Username getLiveUsername() {
        return username;
    }

    @Override
    public String getLiveName() {
        return username.getLastName();
    }

    @Override
    public String getLiveDisplayName() {
        return displayName;
    }
}

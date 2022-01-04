package net.sacredisle.rpgengine.api.player;

import net.minestom.server.entity.Player;
import net.sacredisle.rpgengine.api.Advancing;
import net.sacredisle.rpgengine.api.name.Named;
import net.sacredisle.rpgengine.api.name.Username;

/**
 * Created by Giovanni on 1/4/2022
 */
public interface IRPGPlayer extends Named, Advancing {

    /**
     * Returns this player's username.
     */
    Username getLiveUsername();
}

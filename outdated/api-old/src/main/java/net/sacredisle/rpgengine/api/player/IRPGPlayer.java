package net.sacredisle.rpgengine.api.player;

import net.minestom.server.permission.PermissionHandler;
import net.sacredisle.rpgengine.api.Advancing;
import net.sacredisle.rpgengine.api.name.Named;
import net.sacredisle.rpgengine.api.name.Username;

/**
 * Created by Giovanni on 1/4/2022
 */
public interface IRPGPlayer extends Named, Advancing, PermissionHandler {

    /**
     * Returns this player's username.
     */
    Username getLiveUsername();
}

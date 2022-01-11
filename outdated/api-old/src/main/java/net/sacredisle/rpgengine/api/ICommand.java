package net.sacredisle.rpgengine.api;

import net.minestom.server.permission.Permission;
import net.sacredisle.rpgengine.api.player.IRPGPlayer;

/**
 * Created by Giovanni on 1/6/2022
 */
public interface ICommand {

    /**
     * Returns the {@link Permission} required to execute this command.
     */
    Permission getPermission();

    default boolean hasPermission(IRPGPlayer player) {
        return player.hasPermission(getPermission());
    }
}

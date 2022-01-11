package net.sacredisle.engine.api.instance;

import net.minestom.server.world.DimensionType;
import net.sacredisle.engine.api.Actor;

/**
 * Created by Giovanni on 1/11/2022
 */
public interface RPGInstance extends Actor {

    DimensionType DEFAULT_DIMENSION = DimensionType.OVERWORLD;

    /**
     * Unloads this instance.
     */
    void unload();

    /**
     * Moves all players within this instance to another {@link RPGInstance}.
     */
    void movePlayersTo(RPGInstance newInstance);
}

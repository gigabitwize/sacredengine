package net.sacredisle.rpgengine.api.instance;

import net.minestom.server.coordinate.Pos;

/**
 * Created by Giovanni on 1/4/2022
 */
public interface IRPGInstance {

    /**
     * Returns the spawn position of this instance.
     */
    Pos getSpawn();

    /**
     * Sets the spawn position of this instance.
     */
    void setSpawn(Pos pos);
}

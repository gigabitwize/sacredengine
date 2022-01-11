package net.sacredisle.rpgengine.api.instance.generator;

import net.minestom.server.instance.ChunkGenerator;

/**
 * Created by Giovanni on 1/4/2022
 */
public interface Generator extends ChunkGenerator {

    /**
     * Returns the Y where the default spawn should be.
     */
    int getSpawnY();
}

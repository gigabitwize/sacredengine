package net.sacredisle.engine.instance.generator;

import net.minestom.server.instance.ChunkGenerator;

/**
 * Created by Giovanni on 1/11/2022
 */
public interface Generator extends ChunkGenerator {

    /**
     * Returns the Y level of where the default spawn should be.
     * This is used to make sure players don't spawn inside of a block or the void for example.
     */
    int getSpawnY();
}

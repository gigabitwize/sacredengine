package net.sacredisle.rpgengine.api.instance.generator;

import net.minestom.server.instance.ChunkPopulator;
import net.minestom.server.instance.batch.ChunkBatch;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Giovanni on 1/4/2022
 */
public class VoidGenerator implements Generator {

    @Override
    public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkZ) {

    }

    @Override
    public @Nullable List<ChunkPopulator> getPopulators() {
        return null;
    }

    @Override
    public int getSpawnY() {
        return 2;
    }
}

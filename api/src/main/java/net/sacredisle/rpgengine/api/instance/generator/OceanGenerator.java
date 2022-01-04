package net.sacredisle.rpgengine.api.instance.generator;

import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.ChunkPopulator;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Giovanni on 1/4/2022
 */
public class OceanGenerator implements ChunkGenerator, Generator {

    @Override
    public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkZ) {
        // water
        // sand
        // bedrock
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 38; y++) {
                    if (y == 0) {
                        batch.setBlock(x, 0, z, Block.BEDROCK);
                        continue;
                    }
                    if (y < 15) {
                        batch.setBlock(x, y, z, Block.SAND);
                        continue;
                    }
                    batch.setBlock(x, y, z, Block.WATER);
                }
            }
        }
    }

    @Override
    public @Nullable List<ChunkPopulator> getPopulators() {
        return null;
    }

    @Override
    public int getSpawnY() {
        return 39;
    }
}

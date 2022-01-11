package net.sacredisle.rpgengine.api.instance.generator;

import net.minestom.server.instance.ChunkPopulator;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Giovanni on 1/4/2022
 */
public class FlatGenerator implements Generator {

    private final Block block;

    public FlatGenerator(Block groundBlock) {
        Block setBlock = groundBlock;
        if (groundBlock.isAir() ||
                groundBlock == Block.SAND ||
                groundBlock == Block.DRAGON_EGG ||
                groundBlock == Block.TURTLE_EGG ||
                groundBlock == Block.GRAVEL ||
                groundBlock == Block.RED_SAND ||
                groundBlock == Block.ANVIL ||
                groundBlock == Block.CHIPPED_ANVIL ||
                groundBlock == Block.DAMAGED_ANVIL) {
            setBlock = Block.STONE;
        }
        this.block = setBlock;
    }

    @Override
    public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkZ) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 10; y++) {
                    batch.setBlock(x, y, z, block);
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
        return 11;
    }
}

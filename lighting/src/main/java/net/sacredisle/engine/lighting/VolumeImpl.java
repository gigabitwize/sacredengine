package net.sacredisle.engine.lighting;

import com.google.common.collect.Lists;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.DynamicChunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.Section;
import net.sacredisle.engine.api.Engine;
import net.sacredisle.engine.api.exception.EngineNotEnabledException;
import net.sacredisle.engine.api.lighting.LightEngine;
import net.sacredisle.engine.api.lighting.LightSource;
import net.sacredisle.engine.api.lighting.Volume;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Giovanni on 1/11/2022
 */
public class VolumeImpl implements Volume {

    private final String id;
    private final Instance instance;
    private final List<DynamicChunk> chunks;
    private final List<LightSource> lightSources;
    private final Pos pos;
    private long lastBuild;
    private byte maxEmission;
    private boolean enabled;

    public VolumeImpl(@NotNull String id,
                      @NotNull Instance instance,
                      @NotNull List<DynamicChunk> chunks,
                      @NotNull Pos pos,
                      byte maxEmission) {
        this.id = id;
        this.instance = instance;
        this.chunks = chunks;
        this.lightSources = Lists.newArrayList();
        this.pos = pos;
        this.maxEmission = maxEmission;
    }

    public static Pos createPosition(List<DynamicChunk> chunks) {
        if (chunks.isEmpty()) return null;
        DynamicChunk chunk = chunks.get(0);
        return new Pos(chunk.getChunkX(), 0, chunk.getChunkZ());
    }

    @Override
    public void buildLighting() {
        SacredLighting.LOG.info("Building lighting in " + getId());
        lastBuild = System.currentTimeMillis();
        for (DynamicChunk chunk : chunks) {
            for (Section section : chunk.getSections()) {
                byte[] blockLight = new byte[2048];
                byte[] skyLight = new byte[2048];

                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 15; y > -1; y--) {
                            int coordIndex = y << 8 | z << 4 | x;
                            int shift = (coordIndex & 1) << 2;
                            int arrayIndex = coordIndex >>> 1;
                            skyLight[arrayIndex] = (byte) ((skyLight[arrayIndex] & (0xF0 >>> shift)) | (maxEmission << shift));

                            if (lightSources.isEmpty()) continue;
                            for (LightSource lightSource : lightSources) {
                                if (!lightSource.isEnabled()) continue;
                                Pos sourcePosition = lightSource.getPosition();

                                int sourcePosIndex = sourcePosition.blockY() << 8 | sourcePosition.blockZ() << 4 | sourcePosition.blockX();
                                int sourceShift = (sourcePosIndex & 1) << 2;
                                int blockSourceIndex = sourcePosIndex >>> 1;
                                byte emissionLevel = lightSource.getEmissionLevel() > maxEmission ? maxEmission : lightSource.getEmissionLevel();

                                blockLight[blockSourceIndex] = (byte) ((blockLight[blockSourceIndex] & (0xF0 >>> sourceShift)) | (emissionLevel << sourceShift));
                            }
                        }
                    }
                }
                section.setSkyLight(skyLight);
                section.setBlockLight(blockLight);
            }
            /* Invalidate the chunk packet cache */
            chunk.setBlock(1, 1, 1, chunk.getBlock(1, 1, 1));
            /* Send chunk packet containing new light data */
            chunk.sendChunk();
        }
        SacredLighting.LOG.info("Lighting built for " + getId());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean b) {
        if (!Engine.get().getLightEngine().isEnabled())
            throw new EngineNotEnabledException(LightEngine.class, "can't change Volume state");

        if (this.enabled != b) {
            this.enabled = b;
            getLightSources().forEach(source -> source.setEnabled(b));
            buildLighting();
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Instance getInstance() {
        return instance;
    }

    @Override
    public Pos getPosition() {
        return pos;
    }

    @Override
    public List<DynamicChunk> getChunks() {
        return chunks;
    }

    @Override
    public List<LightSource> getLightSources() {
        return lightSources;
    }

    @Override
    public byte getMaxEmission() {
        return maxEmission;
    }

    /**
     * Sets the max. emission level allowed within this volume and
     * rebuilds the lighting if required.
     */
    public void setMaxEmission(byte maxEmission) {
        if (this.maxEmission != maxEmission) {
            buildLighting();
            this.maxEmission = maxEmission;
        }
    }

    @Override
    public long getLastBuild() {
        return lastBuild;
    }
}

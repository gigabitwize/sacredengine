package net.sacredisle.rpgengine.core.light;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.DynamicChunk;
import net.minestom.server.instance.Section;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;
import net.sacredisle.rpgengine.api.lighting.LightSource;
import net.sacredisle.rpgengine.api.lighting.Volume;
import net.sacredisle.rpgengine.core.RPGEngine;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giovanni on 1/9/2022
 */
public class VolumeImpl implements Volume {

    private final RPGWorldInstance instance;
    private final List<LightSource> lightSources;
    private final List<DynamicChunk> chunks;
    private byte maxEmission;

    public VolumeImpl(@NotNull RPGWorldInstance instance, ArrayList<DynamicChunk> chunkArrayList, byte maxEmission) {
        this.instance = instance;
        this.lightSources = Lists.newArrayList();
        this.chunks = chunkArrayList;
        this.maxEmission = maxEmission;
    }

    @Override
    public void buildLighting() {
        for (DynamicChunk chunk : chunks) {
            for (Section section : chunk.getSections()) {
                byte[] blockLight = new byte[2048];
                byte[] skyLight = new byte[2048];

                // TODO cleanup
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
            chunk.setBlock(1, 1, 1, chunk.getBlock(1, 1, 1)); /* Invalidate the chunk packet cache */
            chunk.sendChunk(); /* Send chunk packet containing new light data */
        }
    }

    @Override
    public void addLightSource(LightSource source) {
        if (!inVolume(source)) {
            RPGEngine.LOG.error("LightSource placed outside of Volume");
            return;
        }
        if (!positionAvailable(source)) {
            RPGEngine.LOG.error("LightSource position not available");
            return;
        }
        lightSources.add(source);
    }

    @Override
    public ImmutableList<LightSource> getLightSources() {
        return ImmutableList.copyOf(lightSources);
    }

    @Override
    public IRPGInstance getInstance() {
        return instance;
    }

    @Override
    public byte getMaxEmissionLevel() {
        return maxEmission;
    }

    @Override
    public void setMaxEmissionLevel(byte b) {
        maxEmission = b > 15 ? 15 : b;
    }

    private boolean inVolume(LightSource source) {
        Pos pos = source.getPosition();
        for (DynamicChunk chunk : chunks) {
            if (pos.sameChunk(chunk.toPosition()))
                return true;
        }
        return false;
    }

    private boolean positionAvailable(LightSource source) {
        for (LightSource lightSource : lightSources) {
            if (lightSource.getPosition().samePoint(source.getPosition()))
                return false;
        }
        return true;
    }
}

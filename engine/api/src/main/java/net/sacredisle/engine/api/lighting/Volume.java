package net.sacredisle.engine.api.lighting;

import net.minestom.server.instance.DynamicChunk;
import net.sacredisle.engine.api.Actor;

import java.util.List;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * A space/collection of chunks in which the {@link LightEngine} should process lighting.
 * <p>
 * The {@link #getPosition()} of a volume should be equal to the position of the first chunk
 * inside the volume{@link #getChunks()}.
 */
public interface Volume extends Actor {

    /**
     * Builds this volume's lighting.
     */
    void buildLighting();

    /**
     * Returns the time in ms at which the lighting for this volume
     * was last built.
     */
    long getLastBuild();

    /**
     * Returns the max. emission level allowed within this volume.
     */
    byte getMaxEmission();

    /**
     * Returns a list of {@link DynamicChunk}s within this volume.
     */
    List<DynamicChunk> getChunks();

    /**
     * Returns a list of {@link LightSource}s present within this volume.
     */
    List<LightSource> getLightSources();
}

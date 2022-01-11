package net.sacredisle.rpgengine.api.lighting;

import com.google.common.collect.ImmutableList;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;

import java.util.List;

/**
 * Created by Giovanni on 1/9/2022
 * <p>
 * A space in which lighting should be functional and processed.
 */
public interface Volume {

    /**
     * Builds the lighting in this volume.
     */
    void buildLighting();

    /**
     * Adds a {@link LightSource} to this volume.
     */
    void addLightSource(LightSource source);

    /**
     * Returns a list of {@link LightSource}s which are present in this volume.
     */
    ImmutableList<LightSource> getLightSources();

    /**
     * Returns the {@link IRPGInstance} this volume is in.
     */
    IRPGInstance getInstance();

    /**
     * Returns the max. light level that {@link LightSource}s can emit within this volume.
     */
    byte getMaxEmissionLevel();

    /**
     * Sets the {@link #getMaxEmissionLevel()}.
     */
    void setMaxEmissionLevel(byte b);
}

package net.sacredisle.engine.lighting;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.sacredisle.engine.api.Engine;
import net.sacredisle.engine.api.exception.EngineNotEnabledException;
import net.sacredisle.engine.api.lighting.LightEngine;
import net.sacredisle.engine.api.lighting.LightSource;
import net.sacredisle.engine.api.lighting.Volume;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/11/2022
 */
public class LightSourceImpl implements LightSource {

    private final String id;
    private final Volume volume;
    private final Pos pos;
    private byte emissionLevel;
    private boolean enabled;

    public LightSourceImpl(@NotNull String id,
                           @NotNull Volume volume,
                           @NotNull Pos pos,
                           byte emissionLevel) {
        this.id = id;
        this.volume = volume;
        this.pos = pos;
        this.emissionLevel = emissionLevel;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean b) {
        if (!Engine.get().getLightEngine().isEnabled())
            throw new EngineNotEnabledException(LightEngine.class, "can't change LightSource state");
        this.enabled = b;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Instance getInstance() {
        return volume.getInstance();
    }

    @Override
    public Pos getPosition() {
        return pos;
    }

    @Override
    public Volume getVolume() {
        return volume;
    }

    @Override
    public byte getEmissionLevel() {
        return emissionLevel;
    }

    /**
     * Sets the level of light this source emits and rebuilds
     * the lighting in the volume it is in if required.
     */
    public void setEmissionLevel(byte emissionLevel) {
        if (this.emissionLevel != emissionLevel) {
            this.emissionLevel = emissionLevel;
            volume.buildLighting();
        }
    }
}

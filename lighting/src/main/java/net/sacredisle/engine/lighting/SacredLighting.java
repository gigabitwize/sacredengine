package net.sacredisle.engine.lighting;

import com.google.common.collect.Sets;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.DynamicChunk;
import net.minestom.server.instance.Instance;
import net.sacredisle.engine.api.lighting.LightEngine;
import net.sacredisle.engine.api.lighting.Volume;
import net.sacredisle.engine.lighting.exception.EmptyVolumeException;
import net.sacredisle.engine.lighting.exception.VolumeAlreadyRegisteredException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by Giovanni on 1/11/2022
 */
public final class SacredLighting implements LightEngine {

    public static final Logger LOG = LoggerFactory.getLogger(SacredLighting.class);
    private final Set<Volume> volumes;
    private boolean enabled;

    public SacredLighting() {
        this.volumes = Sets.newHashSet();
    }

    /**
     * Registers a {@link Volume} and builts its lighting if required.
     */
    public void registerVolume(Volume volume) {
        if (volumes.contains(volume))
            throw new VolumeAlreadyRegisteredException(volume);
        if (volume.getChunks().isEmpty())
            throw new EmptyVolumeException();
        volumes.add(volume);
        if (volume.getLastBuild() == 0)
            volume.buildLighting();
    }

    /**
     * Creates a new {@link Volume}.
     *
     * @param register Whether the volume should be registered automatically or not.
     */
    public Volume newVolume(@NotNull Instance instance,
                            @NotNull List<DynamicChunk> chunkList,
                            byte maxEmission,
                            boolean register) {
        if (chunkList.isEmpty())
            throw new EmptyVolumeException();
        Pos pos = VolumeImpl.createPosition(chunkList);
        String id = "Volume" + (getVolumeCount() + 1);
        assert pos != null;

        VolumeImpl volume = new VolumeImpl(id, instance, chunkList, pos, maxEmission);
        if (register)
            registerVolume(volume);
        return volume;
    }

    /**
     * Checks if a {@link Volume} exists at a specific chunkX & chunkZ, and if so returns it.
     */
    public Volume getVolumeAt(int chunkX, int chunkZ) {
        for (Volume volume : volumes) {
            if (volume.getPosition().chunkX() == chunkX && volume.getPosition().chunkZ() == chunkZ)
                return volume;
        }
        return null;
    }

    @Override
    public Volume getVolume(String actorId) {
        for (Volume volume : volumes)
            if (volume.getId().equalsIgnoreCase(actorId))
                return volume;
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean b) {
        if (this.enabled != b) {
            this.enabled = b;
            if (volumes.isEmpty()) {
                LOG.info("No Volumes are present at this time, no lighting is being built.");
                return;
            }
            for (Volume volume : volumes)
                volume.setEnabled(enabled);
        }
    }

    @Override
    public void buildLighting() {
        LOG.info("Building lighting for " + getVolumeCount() + " volumes.");
        for (Volume volume : volumes) {
            if (volume.getLastBuild() == System.currentTimeMillis()) continue;
            volume.buildLighting();
        }
        LOG.info("Lighting has been built.");
    }

    @Override
    public Set<Volume> getVolumes() {
        return volumes;
    }
}

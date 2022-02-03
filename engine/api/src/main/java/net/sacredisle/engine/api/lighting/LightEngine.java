package net.sacredisle.engine.api.lighting;

import net.sacredisle.engine.api.Actor;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * The Sacred Engine lighting engine.
 */
public interface LightEngine {

    /**
     * Builds all lighting data.
     */
    void buildLighting();

    /**
     * Returns a set of registered {@link Volume}s.
     */
    Set<Volume> getVolumes();

    /**
     * Returns whether lighting functionality & processing should be enabled or not.
     */
    boolean isEnabled();

    /**
     * Sets whether lighting functionality & processing should be enabled or not.
     */
    void setEnabled(boolean b);

    /**
     * Gets a {@link Volume} by ID.
     *
     * @param actorId The {@link Actor#getId()} ID.
     */
    @Nullable Volume getVolume(String actorId);

    default int getVolumeCount() {
        return getVolumes().size();
    }
}

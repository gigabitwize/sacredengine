package net.sacredisle.engine.api.lighting;

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
     *
     */
    boolean isEnabled();

    /**
     * Sets whether lighting functionality & processing should be enabled or not.
     */
    void setEnabled(boolean b);

    default int getVolumeCount() {
        return getVolumes().size();
    }
}

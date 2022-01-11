package net.sacredisle.engine.api.lighting;

import net.sacredisle.engine.api.Actor;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * An actor that emits light in a {@link Volume}.
 */
public interface LightSource extends Actor {

    /**
     * Returns the {@link Volume} this light source is in.
     */
    Volume getVolume();

    /**
     * Returns the amount of light this source emits.
     */
    byte getEmissionLevel();
}

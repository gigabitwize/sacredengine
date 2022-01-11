package net.sacredisle.rpgengine.api.lighting;

import net.minestom.server.coordinate.Pos;
import net.sacredisle.rpgengine.api.Actor;

/**
 * Created by Giovanni on 1/9/2022
 * <p>
 * An object that can emit light within a {@link Volume}.
 */
public interface LightSource extends Actor {

    /**
     * Returns the light level that this source emits.
     */
    byte getEmissionLevel();

    /**
     * Returns the {@link Pos} of this light source.
     */
    Pos getPosition();
}

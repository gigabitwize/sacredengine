package net.sacredisle.engine.api.model;

import net.sacredisle.engine.api.Actor;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * A 3D model that has animation support.
 */
public interface Model extends Actor {

    /**
     * Returns this model's name.
     */
    String getName();
}

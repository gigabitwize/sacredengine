package net.sacredisle.engine.api;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * An actor is an object that can be placed and removed but also
 * enabled or disabled through the engine.
 */
public interface Actor {

    /**
     * Returns the ID of this actor.
     */
    String getId();

    /**
     * Returns the {@link Instance} this actor is in.
     */
    Instance getInstance();

    /**
     * Returns this actor's {@link Pos}.
     */
    Pos getPosition();

    /**
     * Sets whether this actor is enabled or not.
     */
    boolean isEnabled();

    /**
     * Sets whether this actor is enabled or not.
     */
    void setEnabled(boolean b);
}

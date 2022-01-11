package net.sacredisle.rpgengine.api;

/**
 * Created by Giovanni on 1/7/2022
 *
 * An object that can be enabled and disabled at any time.
 */
public interface Actor {

    /**
     * Sets whether this actor is enabled or not.
     */
    void setEnabled(boolean b);

    /**
     * Returns whether this actor is enabled or not.
     */
    boolean isEnabled();
}

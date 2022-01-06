package net.sacredisle.rpgengine.api.vanilla;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * Represents a vanilla Minecraft function.
 */
public interface VanillaFunction<E extends Event> {

    /**
     * Sets whether this function is enabled or not.
     */
    void setEnabled(boolean b);

    /**
     * Returns whether this function is enabled or not.
     */
    boolean isEnabled();

    /**
     * Returns this function's {@link EventListener}.
     */
    EventListener<E> getEventListener();

    default boolean shouldRun() {
        return VanillaProvider.shouldRun(this);
    }
}

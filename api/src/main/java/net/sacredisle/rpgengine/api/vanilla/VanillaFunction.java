package net.sacredisle.rpgengine.api.vanilla;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.sacredisle.rpgengine.api.Actor;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * Represents a vanilla Minecraft function.
 */
public interface VanillaFunction<E extends Event> extends Actor {

    /**
     * Returns this function's {@link EventListener}.
     */
    EventListener<E> getEventListener();

    default boolean shouldRun() {
        return VanillaProvider.shouldRun(this);
    }
}

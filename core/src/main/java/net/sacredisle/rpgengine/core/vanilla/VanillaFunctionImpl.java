package net.sacredisle.rpgengine.core.vanilla;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.sacredisle.rpgengine.api.vanilla.VanillaFunction;

/**
 * Created by Giovanni on 1/6/2022
 */
public class VanillaFunctionImpl<E extends Event> implements VanillaFunction<E> {

    private final EventListener<E> listener;
    private boolean enabled = true;

    public VanillaFunctionImpl(EventListener<E> listener) {
        this.listener = listener;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean b) {
        this.enabled = b;
    }

    @Override
    public EventListener<E> getEventListener() {
        return listener;
    }
}

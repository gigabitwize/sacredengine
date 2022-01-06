package net.sacredisle.rpgengine.api.vanilla;

import com.google.common.collect.Lists;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * A provider of some vanilla functionality, like stair, wall & fence shapes.
 */
public interface VanillaProvider {

    AtomicReference<Boolean> ENABLED = new AtomicReference<>(false);
    EventNode<Event> VANILLA_NODE = EventNode.all("vanilla-provider");
    Collection<VanillaFunction<? extends Event> > FUNCTIONS = Lists.newArrayList();

    /**
     * Registers a {@link VanillaFunction}.
     */
    static void registerFunction(VanillaFunction<? extends Event> function) {
        FUNCTIONS.add(function);
        VANILLA_NODE.addListener(function.getEventListener());
    }

    /**
     * Disables a {@link VanillaFunction}.
     */
    static void disable(Class<? extends VanillaFunction<? extends Event>> function) {
        for(VanillaFunction<? extends Event> func : FUNCTIONS) {
            if (func.getClass().getName().equalsIgnoreCase(function.getName())) {
                func.setEnabled(false);
                break;
            }
        }
    }

    /**
     * Returns whether a {@link VanillaFunction} should run or not.
     */
    static boolean shouldRun(VanillaFunction<? extends Event>  function) {
        return isEnabled() && function.isEnabled();
    }

    /**
     * Returns whether a {@link VanillaFunction} should run or not.
     */
    static boolean shouldRun(Class<? extends VanillaFunction<? extends Event>> function) {
        boolean run = false;
        for(VanillaFunction<? extends Event> func : FUNCTIONS) {
            if (func.getClass().getName().equalsIgnoreCase(function.getName())) {
                run = func.isEnabled();
                break;
            }
        }
        return isEnabled() && run;
    }

    /**
     * Sets whether the vanilla features provided by this interface are enabled or not.
     */
    static void setEnabled(boolean b) {
        ENABLED.set(b);
    }

    static boolean isEnabled() {
        return ENABLED.get();
    }
}

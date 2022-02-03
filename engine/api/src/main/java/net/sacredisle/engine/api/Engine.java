package net.sacredisle.engine.api;

import net.minestom.server.MinecraftServer;
import net.sacredisle.engine.api.instance.RPGInstance;
import net.sacredisle.engine.api.lighting.LightEngine;
import net.sacredisle.engine.api.model.ModelEngine;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * Engine interface. Represents the Sacred Engine.
 */
public interface Engine {

    AtomicReference<Engine> REFERENCE = new AtomicReference<>();
    String BUILD_VERSION = "0.0.2";

    /**
     * Static access supplier.
     */
    static void set(Engine engine) {
        REFERENCE.set(engine);
    }

    /**
     * Static access.
     */
    static Engine get() {
        return REFERENCE.get();
    }

    /**
     * Returns the {@link MinecraftServer}.
     */
    MinecraftServer getServer();

    /**
     * Returns the {@link ModelEngine}.
     */
    ModelEngine getModelEngine();

    /**
     * Returns the {@link LightEngine}.
     */
    LightEngine getLightEngine();

    /**
     * Returns the default {@link RPGInstance}.
     */
    RPGInstance getDefaultInstance();

    /**
     * Returns the build version of the engine.
     */
    default String getBuildVersion() {
        return BUILD_VERSION;
    }
}

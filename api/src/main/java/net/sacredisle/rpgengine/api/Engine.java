package net.sacredisle.rpgengine.api;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.Instance;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;
import net.sacredisle.rpgengine.api.ping.PingHandler;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Giovanni on 1/4/2022
 */
public interface Engine {

    AtomicReference<Engine> REFERENCE = new AtomicReference<>();
    AtomicReference<String> BUILD_VERSION = new AtomicReference<>("0.0.1");

    /**
     * Returns the {@link MinecraftServer}.
     */
    MinecraftServer getMinecraftServer();

    /**
     * Returns the {@link GlobalEventHandler} of the {@link #getMinecraftServer()}.
     */
    GlobalEventHandler getEventHandler();

    /**
     * Returns the current main {@link Instance}.
     */
    IRPGInstance getMainInstance();

    /**
     * Returns the default {@link ChunkGenerator}. This chunk generator will be
     * used whenever a new {@link Instance} is created and no custom {@link ChunkGenerator}
     * was set by the caller.
     */
    ChunkGenerator getDefaultGenerator();

    /**
     * Returns the default {@link DimensionType}. This dimension data will be
     * used whenever a new {@link Instance} is created and no custom {@link DimensionType}
     * was set by the caller.
     */
    DimensionType getDefaultDimension();

    /**
     * Returns the {@link PingHandler}.
     */
    PingHandler getPingHandler();

    /**
     * Returns the port the {@link #getMinecraftServer()} is running on.
     */
    int getRunningPort();

    /**
     * Returns the IP the {@link #getMinecraftServer()} is running on.
     */
    String getRunningIP();

    /**
     * Registers a {@link Command}.
     */
    void registerCommand(Command command);

    /**
     * Creates a new {@link NamespaceID}.
     */
    static NamespaceID createNamespaceId(String key) {
        return NamespaceID.from("rpgengine", key);
    }

    static void set(Engine engine) {
        REFERENCE.set(engine);
    }

    static Engine get() {
        return REFERENCE.get();
    }

    /**
     * Returns the current version of the engine.
     */
    static String getBuildVersion() {
        return BUILD_VERSION.get();
    }
}

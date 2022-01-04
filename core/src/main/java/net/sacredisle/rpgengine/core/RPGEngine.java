package net.sacredisle.rpgengine.core;

import com.google.common.collect.Lists;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.block.Block;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.world.DimensionType;
import net.sacredisle.rpgengine.api.Engine;
import net.sacredisle.rpgengine.api.Ports;
import net.sacredisle.rpgengine.api.exception.AlreadyRunningException;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;
import net.sacredisle.rpgengine.api.instance.generator.FlatGenerator;
import net.sacredisle.rpgengine.api.instance.generator.Generator;
import net.sacredisle.rpgengine.api.instance.generator.OceanGenerator;
import net.sacredisle.rpgengine.api.instance.generator.VoidGenerator;
import net.sacredisle.rpgengine.core.entity.EntityChecker;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.BindException;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class RPGEngine implements Engine {

    public static final Logger LOG = LoggerFactory.getLogger(RPGEngine.class);

    public static final DimensionType DEFAULT_DIMENSION = DimensionType
            .builder(Engine.createNamespaceId("default_dimension"))
            .ambientLight(0.75F) // Default is 0.5, make it slightly higher
            .piglinSafe(true)
            .raidCapable(false)
            .build();

    private final String ip;
    private final int port;
    private final MinecraftServer minecraftServer;
    private final Generator defaultGenerator;
    private final RPGWorldInstance mainInstance;

    public RPGEngine(String[] args, PlayerProvider playerProvider) throws BindException, AlreadyRunningException {
        if (Engine.get() != null)
            throw new AlreadyRunningException("Failed to create RPGEngine, the engine is already running.");
        LOG.info("Starting the Sacred Engine..");

        Engine.set(this);

        ip = args[0];
        port = Integer.parseInt(args[1]);
        if (!Ports.isAvailable(port))
            throw new BindException("Port " + port + " is already in use by another service.");

        if (args[2].equalsIgnoreCase("ocean"))
            defaultGenerator = new OceanGenerator();
        else if (args[2].equalsIgnoreCase("void"))
            defaultGenerator = new VoidGenerator();
        else if (args[2].equalsIgnoreCase("flat"))
            defaultGenerator = new FlatGenerator(Block.STONE);
        else defaultGenerator = new OceanGenerator();

        minecraftServer = MinecraftServer.init();

        /* Main Instance generation */
        MinecraftServer.getDimensionTypeManager().addDimension(DEFAULT_DIMENSION);
        mainInstance = new RPGWorldInstance(
                UUID.randomUUID(),
                DEFAULT_DIMENSION,
                null,
                new Pos(0,  defaultGenerator.getSpawnY(), 0),
                "Sacrisle", null);
        mainInstance.setChunkGenerator(defaultGenerator);
        MinecraftServer.getInstanceManager().registerInstance(mainInstance);
        getEventHandler().addListener(new EntityChecker());

        /* Auth */
        MojangAuth.init();
        MinecraftServer.getConnectionManager().setPlayerProvider(playerProvider);
        getEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(mainInstance);
        });

        // TODO idk if event.isFirstSpawn only is true when the player joins for the first time ever
        // TODO if not, get the current spawning instance's spawn pos rather than the main instance's.
        getEventHandler().addListener(PlayerSpawnEvent.class, event -> {
            if (event.isFirstSpawn())
                event.getPlayer().teleport(mainInstance.getSpawn());

            if (args[3].equalsIgnoreCase("debug"))
                event.getPlayer().setGameMode(GameMode.CREATIVE);
        });


        /* Start */
        minecraftServer.start(ip, port);
        LOG.info("Sacred Engine is running.");
    }

    public StopResult stop() {
        LOG.info("Stopping the Sacred Engine..");
        Engine.set(null);
        MinecraftServer.stopCleanly();
        return StopResult.SUCCESS;
    }

    @Override
    public void registerCommand(Command command) {
        MinecraftServer.getCommandManager().register(command);
    }

    @Override
    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    @Override
    public GlobalEventHandler getEventHandler() {
        return MinecraftServer.getGlobalEventHandler();
    }

    @Override
    public IRPGInstance getMainInstance() {
        return mainInstance;
    }

    @Override
    public DimensionType getDefaultDimension() {
        return DEFAULT_DIMENSION;
    }

    @Override
    public ChunkGenerator getDefaultGenerator() {
        return defaultGenerator;
    }

    @Override
    public int getRunningPort() {
        return port;
    }

    @Override
    public String getRunningIP() {
        return ip;
    }

    public enum StopResult {

        FAILED,
        SUCCESS;
    }
}

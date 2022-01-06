package net.sacredisle.rpgengine.core;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.block.Block;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.world.DimensionType;
import net.sacredisle.rpgengine.api.Engine;
import net.sacredisle.rpgengine.api.exception.AlreadyRunningException;
import net.sacredisle.rpgengine.api.exception.ConnectionNotReadyException;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;
import net.sacredisle.rpgengine.api.instance.generator.FlatGenerator;
import net.sacredisle.rpgengine.api.instance.generator.Generator;
import net.sacredisle.rpgengine.api.instance.generator.OceanGenerator;
import net.sacredisle.rpgengine.api.instance.generator.VoidGenerator;
import net.sacredisle.rpgengine.api.server.Address;
import net.sacredisle.rpgengine.api.server.Server;
import net.sacredisle.rpgengine.api.vanilla.VanillaProvider;
import net.sacredisle.rpgengine.api.vanilla.VanillaUtil;
import net.sacredisle.rpgengine.core.connection.OpenConnection;
import net.sacredisle.rpgengine.core.entity.EntityChecker;
import net.sacredisle.rpgengine.core.human.RPGHuman;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import net.sacredisle.rpgengine.core.permission.CommandPermissions;
import net.sacredisle.rpgengine.core.ping.DefaultPingHandler;
import net.sacredisle.rpgengine.core.tag.Tags;
import net.sacredisle.rpgengine.core.vanilla.stairs.StairsFunction;
import net.sacredisle.rpgengine.core.vanilla.wall.WallFunction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.BindException;
import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class RPGEngine implements Engine {

    public static final Logger LOG = LoggerFactory.getLogger(RPGEngine.class);
    public static final DimensionType DEFAULT_DIMENSION = DimensionType
            .builder(Engine.createNamespaceId("default_dimension"))
            .ambientLight(0.6F) // Default is 0.5, make it slightly higher
            .piglinSafe(true)
            .raidCapable(false)
            .build();
    public static int MAX_PLAYERS = 100;
    private final MinecraftServer minecraftServer;
    private final Generator defaultGenerator;
    private final RPGWorldInstance mainInstance;
    private final DefaultPingHandler pingHandler;
    private Server.Connection connection;

    public RPGEngine(@NotNull Server.Connection forgedConnection, String[] args, @NotNull PlayerProvider playerProvider) throws BindException, AlreadyRunningException, ConnectionNotReadyException {
        if (Engine.get() != null)
            throw new AlreadyRunningException("Failed to create RPGEngine, the engine is already running.");

        if (!OpenConnection.isOpen(forgedConnection))
            throw new ConnectionNotReadyException();

        LOG.info("Starting the Sacred Engine..");

        Engine.set(this);
        connection = forgedConnection;
        pingHandler = new DefaultPingHandler();

        /* Check args here */
        boolean noVanilla = false;
        for(String arg : args) {
            if(arg.equalsIgnoreCase("no-vanilla"))
                noVanilla = true;
        }

        if (args[0].equalsIgnoreCase("ocean"))
            defaultGenerator = new OceanGenerator();
        else if (args[0].equalsIgnoreCase("void"))
            defaultGenerator = new VoidGenerator();
        else if (args[0].equalsIgnoreCase("flat"))
            defaultGenerator = new FlatGenerator(Block.STONE);
        else defaultGenerator = new OceanGenerator();

        minecraftServer = MinecraftServer.init();
        getEventHandler().addListener(pingHandler);

        /* Vanilla functionality */
        if(!noVanilla) {
            VanillaProvider.setEnabled(true);
            registerVanillaFeatures();
            LOG.info("Using VanillaProvider");
        } else VanillaProvider.setEnabled(false);

        /* Main Instance generation */
        MinecraftServer.getDimensionTypeManager().addDimension(DEFAULT_DIMENSION);
        mainInstance = new RPGWorldInstance(
                UUID.randomUUID(),
                DEFAULT_DIMENSION,
                null,
                new Pos(0, defaultGenerator.getSpawnY(), 0),
                "Sacrisle", null);
        mainInstance.setChunkGenerator(defaultGenerator);
        MinecraftServer.getInstanceManager().registerInstance(mainInstance);
        getEventHandler().addListener(new EntityChecker());

        /* Auth */
        MojangAuth.init();
        MinecraftServer.getConnectionManager().setPlayerProvider(playerProvider);
        getEventHandler().addListener(PlayerLoginEvent.class, event -> {
            if (event.getPlayer() instanceof RPGHuman human) {
                event.setSpawningInstance(human.getSpawnInstance());
                return;
            }
            event.setSpawningInstance(mainInstance);
            if (connection.__debugMode())
                event.getPlayer().addPermission(CommandPermissions.ALL);
        });

        // TODO idk if event.isFirstSpawn only is true when the player joins for the first time ever
        // TODO if not, get the current spawning instance's spawn pos rather than the main instance's.
        getEventHandler().addListener(PlayerSpawnEvent.class, event -> {
            if (event.isFirstSpawn()) {
                if (event.getPlayer().hasTag(Tags.IGNORE_SPAWN_EVENTS)) return;
                event.getPlayer().teleport(mainInstance.getSpawn());
            }
            if (connection.__debugMode())
                event.getPlayer().setGameMode(GameMode.CREATIVE);
        });

        /* Start */
        startMinecraftServer();
        LOG.info("Sacred Engine is running.");
    }

    public StopResult stop() {
        LOG.info("Stopping the Sacred Engine..");
        if (OpenConnection.isOpen(connection))
            connection = ((OpenConnection) connection).close();
        Engine.set(null);
        MinecraftServer.stopCleanly();
        return StopResult.SUCCESS;
    }

    private void startMinecraftServer() {
        Address address = connection.getServer().address();
        minecraftServer.start(address.ip(), address.port());
    }

    private void registerVanillaFeatures() {
        getEventHandler().addChild(VanillaProvider.VANILLA_NODE);
        VanillaProvider.registerFunction(new StairsFunction());
        VanillaProvider.registerFunction(new WallFunction());
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
    public DefaultPingHandler getPingHandler() {
        return pingHandler;
    }

    @Override
    public Server.Connection getConnection() {
        return connection;
    }

    public enum StopResult {

        FAILED,
        SUCCESS;
    }
}

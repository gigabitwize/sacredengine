package net.sacredisle.engine;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.block.Block;
import net.sacredisle.engine.api.Engine;
import net.sacredisle.engine.api.instance.RPGInstance;
import net.sacredisle.engine.api.lighting.LightEngine;
import net.sacredisle.engine.api.model.ModelEngine;
import net.sacredisle.engine.api.server.Address;
import net.sacredisle.engine.exception.ServerStartException;
import net.sacredisle.engine.instance.RPGInstanceImpl;
import net.sacredisle.engine.instance.generator.FlatGenerator;
import net.sacredisle.engine.lighting.SacredLighting;
import net.sacredisle.engine.model.Sacred3D;
import net.sacredisle.engine.player.RPGPlayerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/12/2022
 * <p>
 * The Sacred Engine main class.
 */
public final class SacredEngine implements Engine {

    public static final Logger LOG = LoggerFactory.getLogger(SacredEngine.class);
    private static SacredEngine ENGINE;
    private final SacredServer server;
    private final MinecraftServer minecraftServer;
    private final ModelEngine modelEngine;
    private final LightEngine lightEngine;
    private final RPGInstanceImpl defaultInstance;

    public SacredEngine(Environment environment) throws ServerStartException {
        ENGINE = this;
        long bootTime = System.currentTimeMillis();

        LOG.info("Starting Sacred Engine..");
        server = new SacredServer(environment, 3306);
        Address minecraftAddress = server.start();
        minecraftServer = MinecraftServer.init();

        /* Default Instance */
        defaultInstance = new RPGInstanceImpl(
                "Instance01",
                new Pos(0, 15, 0),
                null,
                true);
        defaultInstance.setCanUnload(false);
        defaultInstance.setChunkGenerator(new FlatGenerator(Block.SANDSTONE));
        MinecraftServer.getInstanceManager().registerInstance(defaultInstance);

        /* Minecraft Authentication */
        MojangAuth.init();
        MinecraftServer.getConnectionManager().setPlayerProvider(new RPGPlayerProvider());
        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(defaultInstance);
        });

        /* Lighting & 3D Models */
        lightEngine = new SacredLighting();
        lightEngine.setEnabled(true);
        modelEngine = new Sacred3D(ModelEngine.DEFAULT_PATH);
        modelEngine.setEnabled(true);

        minecraftServer.start(minecraftAddress.ip(), minecraftAddress.port());
        LOG.info("Sacred Engine bootstrap finished, took " + (System.currentTimeMillis() - bootTime) + "ms.");
    }

    public void exit() {
        LOG.info("Sacred Engine is stopping..");
        // TODO
        MinecraftServer.stopCleanly();
        LOG.info("Goodbye!");
    }

    public static SacredEngine get() {
        return ENGINE;
    }

    /**
     * Returns the {@link SacredServer}.
     */
    public SacredServer getSacredServer() {
        return server;
    }

    @Override
    public MinecraftServer getServer() {
        return minecraftServer;
    }

    @Override
    public ModelEngine getModelEngine() {
        return modelEngine;
    }

    @Override
    public LightEngine getLightEngine() {
        return lightEngine;
    }

    @Override
    public RPGInstance getDefaultInstance() {
        return defaultInstance;
    }

    public enum Environment {
        LOCAL,
        PRODUCTION;
    }
}

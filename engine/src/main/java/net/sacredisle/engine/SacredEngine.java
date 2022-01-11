package net.sacredisle.engine;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.block.Block;
import net.sacredisle.engine.api.Engine;
import net.sacredisle.engine.api.instance.RPGInstance;
import net.sacredisle.engine.api.model.ModelEngine;
import net.sacredisle.engine.instance.RPGInstanceImpl;
import net.sacredisle.engine.instance.generator.FlatGenerator;
import net.sacredisle.engine.lighting.SacredLighting;
import net.sacredisle.engine.model.Sacred3D;
import net.sacredisle.engine.player.RPGPlayerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/11/2022
 */
public class SacredEngine implements Engine {

    public static final Logger LOG = LoggerFactory.getLogger(SacredEngine.class);
    private final MinecraftServer minecraftServer;
    private final SacredLighting lightEngine;
    private final Sacred3D modelEngine;

    private final RPGInstanceImpl defaultInstance;

    public SacredEngine() {
        long bootTime = System.currentTimeMillis();

        LOG.info("Starting SacredEngine..");
        minecraftServer = MinecraftServer.init();

        defaultInstance = new RPGInstanceImpl(
                "Instance01",
                new Pos(0, 15, 0),
                null,
                true);
        defaultInstance.setCanUnload(false);
        defaultInstance.setChunkGenerator(new FlatGenerator(Block.SANDSTONE));
        MinecraftServer.getInstanceManager().registerInstance(defaultInstance);

        MojangAuth.init();
        MinecraftServer.getConnectionManager().setPlayerProvider(new RPGPlayerProvider());
        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(defaultInstance);
        });

        lightEngine = new SacredLighting();
        lightEngine.setEnabled(true);

        modelEngine = new Sacred3D(ModelEngine.DEFAULT_PATH);
        modelEngine.setEnabled(true);

        minecraftServer.start("localhost", 25565);
        LOG.info("SacredEngine bootstrap done, took " + (System.currentTimeMillis() - bootTime) + "ms.");
    }

    /******************
     * TESTING TODO
     */
    public static void main(String[] args) {
        SacredEngine sacredEngine = new SacredEngine();
    }

    @Override
    public MinecraftServer getServer() {
        return minecraftServer;
    }

    @Override
    public RPGInstance getDefaultInstance() {
        return defaultInstance;
    }

    @Override
    public SacredLighting getLightEngine() {
        return lightEngine;
    }

    @Override
    public Sacred3D getModelEngine() {
        return modelEngine;
    }
}

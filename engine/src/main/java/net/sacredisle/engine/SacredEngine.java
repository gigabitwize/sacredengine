package net.sacredisle.engine;

import net.minestom.server.MinecraftServer;
import net.sacredisle.engine.api.Engine;
import net.sacredisle.engine.api.lighting.LightEngine;
import net.sacredisle.engine.api.model.ModelEngine;
import net.sacredisle.engine.lighting.SacredLighting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/11/2022
 */
public class SacredEngine implements Engine {

    public static final Logger LOG = LoggerFactory.getLogger(SacredEngine.class);
    private final MinecraftServer minecraftServer;
    private final SacredLighting lightEngine;

    public SacredEngine() {
        long bootTime = System.currentTimeMillis();

        LOG.info("Starting SacredEngine..");
        lightEngine = new SacredLighting();
        lightEngine.setEnabled(true);

        minecraftServer = MinecraftServer.init();
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
    public ModelEngine getModelEngine() {
        return null;
    }

    @Override
    public LightEngine getLightEngine() {
        return lightEngine;
    }

    @Override
    public MinecraftServer getServer() {
        return minecraftServer;
    }
}

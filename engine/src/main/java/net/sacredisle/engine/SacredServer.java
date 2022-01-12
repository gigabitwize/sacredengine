package net.sacredisle.engine;

import net.sacredisle.engine.api.server.Address;
import net.sacredisle.engine.api.server.Server;
import net.sacredisle.engine.api.server.ServerConnection;
import net.sacredisle.engine.exception.ServerRunningException;
import net.sacredisle.engine.exception.ServerStartException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/12/2022
 * <p>
 * Server implementation, runs concurrently with the {@link net.minestom.server.MinecraftServer}
 * and provides a layer of communication between the network and the engine.
 */
public class SacredServer implements ServerConnection {

    public static final Logger LOG = LoggerFactory.getLogger(SacredServer.class);

    private final Environment environment;
    private final int serverPort;
    private boolean running;
    private Server record;

    /**
     * @param port The port to start the SacredServer on. This port
     *             is not the same as the {@link net.minestom.server.MinecraftServer}'s port.
     *             The SacredServer and MinecraftServer run concurrrently.
     */
    public SacredServer(Environment environment, int port) {
        this.environment = environment;
        this.serverPort = port;
    }

    /**
     * Starts the server.
     *
     * @return The {@link Address} to start the {@link net.minestom.server.MinecraftServer} on.
     */
    protected Address start() throws ServerStartException {
        if (running)
            throw new ServerRunningException();
        long bootTime = System.currentTimeMillis();

        LOG.info("Starting Sacred Server..");
        Address address;
        if (environment == Environment.LOCAL)
            address = new Address("localhost", 25565);
        else
            throw new ServerStartException("Environment is not supported at this time.");

        running = true;
        LOG.info("Sacred Server running, took " + (System.currentTimeMillis() - bootTime) + "ms.");
        return address;
    }

    @Override
    public Server getRecord() {
        return record;
    }


    /**
     * Returns the server's environment.
     */
    public Environment getEnvironment() {
        return environment;
    }
}

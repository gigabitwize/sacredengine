package net.sacredisle.engine.api.server;

/**
 * Created by Giovanni on 1/12/2022
 * <p>
 * Represents a server connection, most commonly a connection between a production instance
 * and the network. Can also be a local connection between the engine and the actual Minecraft Server.
 */
public interface ServerConnection {

    /**
     * Returns this connection's {@link Server} record.
     */
    Server getRecord();
}

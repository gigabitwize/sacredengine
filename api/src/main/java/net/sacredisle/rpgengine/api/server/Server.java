package net.sacredisle.rpgengine.api.server;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * A record containing information about a connection.
 */
public record Server(Address address,
                     String id,
                     String name) {

    public Server(Address address, String id, String name) {
        this.address = address;
        this.id = id;
        this.name = name;
    }

    /**
     * Represents a server connection between a host and a client.
     */
    public interface Connection {

        /**
         * Returns this connection's {@link Server} record.
         */
        Server getServer();

        /**
         * Returns the host's {@link Server} record.
         * <p>
         * If this connection runs in {@link #__debugMode()} then this will
         * return the same record as {@link #getServer()}.
         */
        Server getHost();

        /**
         * Returns whether this connection is running in debug mode.
         * <p>
         * Set to TRUE if running in a local environment, FALSE if a socket
         * connection to the network should be opened.
         */
        boolean __debugMode();
    }
}
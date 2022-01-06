package net.sacredisle.rpgengine.core.connection;

import net.sacredisle.rpgengine.api.server.Server;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * Represents a {@link net.sacredisle.rpgengine.api.server.Server.Connection} object that
 * has been successfully opened and is active.
 */
public class OpenConnection implements Server.Connection {

    public static final Logger LOG = LoggerFactory.getLogger(Server.Connection.class);

    private final Server self, host;
    private final boolean DEBUG;

    public OpenConnection(@NotNull Server self, Server host, boolean DEBUG) {
        this.self = self;
        this.host = DEBUG ? self : host;
        this.DEBUG = DEBUG;
    }

    /**
     * Closes this connection and returns a base, closed {@link net.sacredisle.rpgengine.api.server.Server.Connection}.
     * <p>
     * TODO
     */
    public Server.Connection close() {
        LOG.info("Closing main server<->host connection..");
        return new Server.Connection() {
            @Override
            public Server getServer() {
                return self;
            }

            @Override
            public Server getHost() {
                return host;
            }

            @Override
            public boolean __debugMode() {
                return DEBUG;
            }
        };
    }

    @Override
    public Server getServer() {
        return self;
    }

    @Override
    public Server getHost() {
        return host;
    }

    @Override
    public boolean __debugMode() {
        return DEBUG;
    }

    public static boolean isOpen(Server.Connection connection) {
        return connection instanceof OpenConnection;
    }
}

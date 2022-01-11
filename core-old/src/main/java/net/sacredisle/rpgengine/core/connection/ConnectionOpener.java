package net.sacredisle.rpgengine.core.connection;

import net.sacredisle.rpgengine.api.ReturnValue;
import net.sacredisle.rpgengine.api.server.Address;
import net.sacredisle.rpgengine.api.server.Server;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Giovanni on 1/6/2022
 */
public class ConnectionOpener {

    /**
     * Creates and opens a new connection, can open a connection with the network or
     * open it locally. Local is used for debug environments.
     *
     * @param address The address of this server.
     */
    public static ReturnValue<Result, OpenConnection> open(@Nullable Server.Connection connection, Address address, boolean local) {
        if (OpenConnection.isOpen(connection))
            return new ReturnValue<Result, OpenConnection>() {
                @Override
                public Result getFirst() {
                    return Result.ALREADY_CONNECTED;
                }

                @Override
                public OpenConnection getSecond() {
                    return null;
                }
            };
        return new ReturnValue<Result, OpenConnection>() {
            @Override
            public Result getFirst() {
                return Result.CONNECTION_OPENED;
            }

            @Override
            public OpenConnection getSecond() {
                return new OpenConnection(
                        new Server(address, "Server-01", "LocalEngineServer"),
                        null,
                        local);
            }
        };
    }

    public enum Result {

        CONNECTION_OPENED(true),
        ALREADY_CONNECTED(false),
        CONNECTION_REFUSED(false);

        private final boolean success;

        Result(boolean b) {
            this.success = b;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}

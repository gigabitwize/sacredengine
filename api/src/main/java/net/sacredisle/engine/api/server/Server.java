package net.sacredisle.engine.api.server;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/12/2022
 */
public record Server(Address address,
                     String id,
                     String displayName) {

    public Server(@NotNull Address address, String id, String displayName) {
        this.address = address;
        this.id = id;
        this.displayName = displayName;
    }
}

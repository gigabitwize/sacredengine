package net.sacredisle.engine.api.name;

import java.util.UUID;

/**
 * Created by Giovanni on 1/11/2022
 */
public class Name {

    private final UUID uuid;
    private String name;

    public Name(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}

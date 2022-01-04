package net.sacredisle.rpgengine.api.name;

import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class Username {

    private final UUID id;
    private final String lastName;

    public Username(UUID id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }
}

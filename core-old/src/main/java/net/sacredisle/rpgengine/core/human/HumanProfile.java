package net.sacredisle.rpgengine.core.human;

import net.minestom.server.entity.PlayerSkin;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/5/2022
 */
public record HumanProfile(
        @NotNull String userName,
        @NotNull PlayerSkin skin) {

    public HumanProfile(String userName, PlayerSkin skin) {
        this.userName = userName;
        this.skin = skin;
    }

    public HumanProfile(String userName) {
        this(userName, PlayerSkin.fromUsername(userName));
    }
}

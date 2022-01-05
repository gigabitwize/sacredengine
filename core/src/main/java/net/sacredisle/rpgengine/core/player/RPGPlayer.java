package net.sacredisle.rpgengine.core.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.sacredisle.rpgengine.api.name.Username;
import net.sacredisle.rpgengine.api.player.IRPGPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/4/2022
 */
public class RPGPlayer extends Player implements IRPGPlayer {

    private final Username username;
    private TextComponent displayName;
    private int exp, level = 1;

    public RPGPlayer(Username username, @NotNull PlayerConnection playerConnection) {
        super(username.getId(), username.getLastName(), playerConnection);
        this.username = username;
        this.displayName = Component.text(username.getLastName());
    }

    /**
     * Adds experience to this player.
     */
    public void addExperience(int amount) {
        this.exp += amount;
    }

    @Override
    public void setDisplayName(TextComponent displayName) {
        this.displayName = displayName;
    }

    @Override
    public void setRPGLevel(int level) {
        this.level = level;
    }

    @Override
    public void setRPGExperience(int experience) {
        this.exp = experience;
    }

    @Override
    public Username getLiveUsername() {
        return username;
    }

    @Override
    public String getLiveName() {
        return username.getLastName();
    }

    @Override
    public TextComponent getLiveDisplayName() {
        return displayName;
    }

    @Override
    public int getRPGExp() {
        return exp;
    }

    @Override
    public int getRPGLevel() {
        return level;
    }
}

package net.sacredisle.rpgengine.core.human;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.coordinate.Pos;
import net.sacredisle.rpgengine.api.Advancing;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/5/2022
 */
public class RPGHumanCreature extends RPGHuman implements Advancing {

    private int level;

    public RPGHumanCreature(@NotNull RPGWorldInstance instance, @NotNull Pos spawnPosition, @NotNull HumanProfile profile) {
        super(instance, spawnPosition, profile);
    }

    @Override
    public void setDisplayName(TextComponent str) {
    }

    @Override
    public void setRPGLevel(int level) {
        this.level = level;
    }

    @Override
    @Deprecated
    public void setRPGExperience(int experience) {
    }

    @Override
    public int getRPGExp() {
        return 0;
    }

    @Override
    public int getRPGLevel() {
        return level;
    }
}

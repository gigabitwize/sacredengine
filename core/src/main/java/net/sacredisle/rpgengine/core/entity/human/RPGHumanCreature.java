package net.sacredisle.rpgengine.core.entity.human;

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

    public RPGHumanCreature(@NotNull RPGWorldInstance instance, @NotNull Pos spawnPosition,@NotNull String entityName, @NotNull TextComponent customName) {
        super(instance, spawnPosition, entityName, customName);
    }

    @Override
    public void setDisplayName(TextComponent str) {
        this.customName = str;
        getEntityMeta().setNotifyAboutChanges(false);
        this.setCustomName(Component.text("[Lvl. ").color(NamedTextColor.GOLD)
                .append(Component.text(level).color(NamedTextColor.GOLD))
                .append(Component.text("] ").color(NamedTextColor.GOLD))
                .append(str));
        this.setCustomNameVisible(true);
        getEntityMeta().setNotifyAboutChanges(true);
    }

    @Override
    public void setRPGLevel(int level) {
        this.level = level;
        setDisplayName(customName); // Update
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

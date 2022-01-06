package net.sacredisle.rpgengine.core.entity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.sacredisle.rpgengine.api.entity.IRPGCreature;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * An entity object that has support for navigation and AI. Can be
 * ANY entity type.
 */
public class RPGCreature extends EntityCreature implements IRPGCreature {

    private final String entityName;
    private int level = 1;
    private TextComponent customName;

    public RPGCreature(@NotNull EntityType entityType, int level, String entityName, TextComponent customName) {
        super(entityType, UUID.randomUUID());
        this.level = level;
        this.entityName = entityName;
        this.customName = customName;
        setDisplayName(customName);
    }

    @Override
    public void setDisplayName(TextComponent str) {
        /* [Lvl. XXX] NAME */
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

    @Override
    public void setRPGLevel(int level) {
        this.level = level;
    }

    @Override
    public String getLiveName() {
        return entityName;
    }

    @Override
    public TextComponent getLiveDisplayName() {
        return customName;
    }
}

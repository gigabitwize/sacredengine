package net.sacredisle.rpgengine.core.entity;

import net.kyori.adventure.text.TextComponent;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.sacredisle.rpgengine.api.entity.IRPGEntity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * A base entity object that requires no AI and navigation. For example
 * static NPCs or other kinds of entities that don't require any actual
 * behaviour.
 */
public class RPGEntity extends LivingEntity implements IRPGEntity {

    private final String entityName;
    private TextComponent customName;

    public RPGEntity(@NotNull EntityType entityType, String entityName, TextComponent customName) {
        super(entityType, UUID.randomUUID());
        this.entityName = entityName;
        this.customName = customName;
    }

    @Override
    public void setDisplayName(TextComponent str) {
        this.customName = str;
        getEntityMeta().setNotifyAboutChanges(false);
        this.setCustomName(str);
        this.setCustomNameVisible(true);
        getEntityMeta().setNotifyAboutChanges(true);
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

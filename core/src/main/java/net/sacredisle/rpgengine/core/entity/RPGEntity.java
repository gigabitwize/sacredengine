package net.sacredisle.rpgengine.core.entity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.sacredisle.rpgengine.api.entity.IRPGEntity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 *
 * An entity object that requires no AI and navigation. For example
 * static NPCs or other kinds of entities that don't require any actual
 * behaviour.
 */
public class RPGEntity extends LivingEntity implements IRPGEntity {

    private String customName;
    private final String entityName;

    public RPGEntity(@NotNull EntityType entityType, String entityName, String customName) {
        super(entityType, UUID.randomUUID());
        this.entityName = entityName;
        this.customName = customName;
    }

    @Override
    public void setDisplayName(String str) {
        /* [Lvl. XXX] NAME */
        this.customName = str;
        getEntityMeta().setNotifyAboutChanges(false);
        this.setCustomName(Component.text(str).color(NamedTextColor.RED));
        this.setCustomNameVisible(true);
        getEntityMeta().setNotifyAboutChanges(true);
    }

    @Override
    public String getLiveName() {
        return entityName;
    }

    @Override
    public String getLiveDisplayName() {
        return customName;
    }
}

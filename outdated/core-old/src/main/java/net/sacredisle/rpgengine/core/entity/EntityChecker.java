package net.sacredisle.rpgengine.core.entity;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.entity.EntitySpawnEvent;
import net.sacredisle.rpgengine.api.entity.IRPGEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * Class that checks if an entity is an instance of {@link net.sacredisle.rpgengine.api.entity.IRPGEntity}, and
 * if it isn't, it will remove the entity. In general, this class shouldn't be necessary since there are no
 * other vectors through which entities can spawn other than this engine. However, someone might mess up and
 * create a new instance of an entity using the Minestom classes rather than using the {@link RPGCreature} or
 * {@link RPGEntity} classes.
 */
public class EntityChecker implements EventListener<EntitySpawnEvent> {

    @Override
    public @NotNull Result run(@NotNull EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) return Result.SUCCESS;
        if (!(entity instanceof IRPGEntity)) {
            entity.remove();
            return Result.SUCCESS;
        }
        return Result.SUCCESS;
    }

    @Override
    public @NotNull Class<EntitySpawnEvent> eventType() {
        return EntitySpawnEvent.class;
    }
}

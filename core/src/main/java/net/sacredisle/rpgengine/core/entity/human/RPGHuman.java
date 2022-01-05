package net.sacredisle.rpgengine.core.entity.human;

import net.kyori.adventure.text.TextComponent;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.entity.fakeplayer.FakePlayerOption;
import net.minestom.server.instance.Instance;
import net.sacredisle.rpgengine.api.entity.IRPGEntity;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import net.sacredisle.rpgengine.core.tag.Tags;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Created by Giovanni on 1/5/2022
 */
public class RPGHuman extends FakePlayer implements IRPGEntity {

    protected TextComponent customName;
    private final String entityName;
    private final WeakReference<RPGWorldInstance> spawnInstance;

    public RPGHuman(@NotNull RPGWorldInstance instance, @NotNull Pos spawnPosition, @NotNull String entityName, @NotNull TextComponent customName) {
        super(UUID.randomUUID(), entityName, new FakePlayerOption().setInTabList(false).setRegistered(false), fakePlayer -> {
            fakePlayer.teleport(spawnPosition);
        });
        setTag(Tags.IGNORE_SPAWN_EVENTS, "true");
        this.spawnInstance = new WeakReference<>(instance);
        this.customName = customName;
        this.entityName = entityName;
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

    public RPGWorldInstance getSpawnInstance() {
        return spawnInstance.get();
    }
}

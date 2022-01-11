package net.sacredisle.rpgengine.core.human;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.entity.fakeplayer.FakePlayerOption;
import net.sacredisle.rpgengine.api.entity.IRPGEntity;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Created by Giovanni on 1/5/2022
 */
public class RPGHuman extends FakePlayer implements IRPGEntity {

    private final HumanProfile profile;
    private final WeakReference<RPGWorldInstance> spawnInstance;
    private final Pos spawnPosition;

    public RPGHuman(@NotNull RPGWorldInstance instance, @NotNull Pos spawnPosition, @NotNull HumanProfile profile) {
        super(UUID.randomUUID(), profile.userName(), new FakePlayerOption().setInTabList(false).setRegistered(false), null);
        this.profile = profile;
        this.spawnInstance = new WeakReference<>(instance);
        this.spawnPosition = spawnPosition;
        setSkin(profile.skin());
    }

    @Override
    public void setDisplayName(TextComponent str) {
    }

    @Override
    public String getLiveName() {
        return profile.userName();
    }

    @Override
    public TextComponent getLiveDisplayName() {
        return Component.text(profile.userName());
    }

    public Pos getSpawnPosition() {
        return spawnPosition;
    }

    public RPGWorldInstance getSpawnInstance() {
        return spawnInstance.get();
    }
}

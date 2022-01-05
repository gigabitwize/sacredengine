package net.sacredisle.rpgengine.core.instance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.IChunkLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class RPGWorldInstance extends InstanceContainer implements IRPGInstance {

    private Pos spawn;
    private TextComponent displayName;
    private final String name;

    public RPGWorldInstance(@NotNull UUID uniqueId,
                            @NotNull DimensionType dimensionType,
                            @Nullable IChunkLoader loader,
                            @NotNull Pos spawn,
                            @NotNull String name,
                            @Nullable TextComponent displayName) {
        super(uniqueId, dimensionType, loader);
        this.spawn = spawn;
        this.name = name;
        this.displayName = Objects.requireNonNullElseGet(displayName, () -> Component.text(this.name));
    }

    @Override
    public void setDisplayName(TextComponent displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getLiveName() {
        return name;
    }

    @Override
    public TextComponent getLiveDisplayName() {
        return displayName;
    }

    @Override
    public Pos getSpawn() {
        return spawn;
    }

    @Override
    public void setSpawn(Pos instanceSpawn) {
        this.spawn = instanceSpawn;
    }
}

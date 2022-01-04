package net.sacredisle.rpgengine.core.instance;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.IChunkLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import net.sacredisle.rpgengine.api.instance.IRPGInstance;
import net.sacredisle.rpgengine.api.player.IRPGPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class RPGWorldInstance extends InstanceContainer implements IRPGInstance {

    private Pos spawn;

    public RPGWorldInstance(@NotNull UUID uniqueId,
                            @NotNull DimensionType dimensionType,
                            @Nullable IChunkLoader loader,
                            @NotNull Pos spawn) {
        super(uniqueId, dimensionType, loader);
        this.spawn = spawn;
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

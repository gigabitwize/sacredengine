package net.sacredisle.engine.instance;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.world.DimensionType;
import net.sacredisle.engine.SacredEngine;
import net.sacredisle.engine.api.exception.InvalidInstanceException;
import net.sacredisle.engine.api.instance.RPGInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Created by Giovanni on 1/11/2022
 */
@SuppressWarnings("UnstableApiUsage")
public class RPGInstanceImpl extends InstanceContainer implements RPGInstance {

    private final String id;
    private final Pos spawnPosition;
    private boolean enabled, buildingEnabled, canUnload;

    public RPGInstanceImpl(@NotNull String id,
                           @NotNull Pos spawnPosition,
                           @Nullable DimensionType dimensionType,
                           boolean buildingEnabled) {
        super(UUID.randomUUID(), dimensionType == null ? RPGInstance.DEFAULT_DIMENSION : dimensionType, new AnvilLoader("world"));
        this.id = id;
        this.spawnPosition = spawnPosition;
        this.buildingEnabled = buildingEnabled;
    }

    @Override
    public void unload() {
        if (!canUnload) return;
        SacredEngine.LOG.info("Unloading " + getId());
        for (Instance instance : MinecraftServer.getInstanceManager().getInstances()) {
            if (instance instanceof RPGInstanceImpl rpgInstance) {
                if (rpgInstance == this) continue;
                if (!rpgInstance.isEnabled()) continue;
                movePlayersTo(rpgInstance);
                break;
            }
        }
        MinecraftServer.getInstanceManager().unregisterInstance(this);
        SacredEngine.LOG.info(getId() + " has been unloaded.");
    }

    @Override
    public void movePlayersTo(@NotNull RPGInstance newInstance) {
        if (!(newInstance instanceof Instance))
            throw new InvalidInstanceException(newInstance.getClass(), "is not a valid Minestom Instance");
        if (newInstance == this)
            throw new InvalidInstanceException(newInstance.getClass(), "it's the same as the current one");
        if (!newInstance.isEnabled())
            throw new InvalidInstanceException(newInstance.getClass(), "is not enabled");
        getPlayers().forEach(player -> player.setInstance((Instance) newInstance, player.getPosition()));
    }

    @Override
    public boolean placeBlock(BlockHandler.@NotNull Placement placement) {
        if (!buildingEnabled) return false;
        return super.placeBlock(placement);
    }

    @Override
    public boolean breakBlock(@NotNull Player player, @NotNull Point blockPosition) {
        if (!buildingEnabled) return false;
        return super.breakBlock(player, blockPosition);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Instance getInstance() {
        return this;
    }

    @Override
    public Pos getPosition() {
        return spawnPosition;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean b) {
        this.enabled = b;
    }

    /**
     * Returns whether building is enabled in this instnace.
     */
    public boolean isBuildingEnabled() {
        return buildingEnabled;
    }

    /**
     * Sets whether building is enabled in this instance.
     */
    public void setBuildingEnabled(boolean buildingEnabled) {
        this.buildingEnabled = buildingEnabled;
    }

    /**
     * Sets whether this instance can be unloaded through {@link #unload()}
     */
    public void setCanUnload(boolean canUnload) {
        this.canUnload = canUnload;
    }
}

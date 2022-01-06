package net.sacredisle.rpgengine.core.vanilla.wall;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.instance.block.rule.vanilla.WallPlacementRule;
import net.sacredisle.rpgengine.api.vanilla.VanillaProvider;
import net.sacredisle.rpgengine.api.vanilla.VanillaUtil;
import net.sacredisle.rpgengine.core.vanilla.VanillaFunctionImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/6/2022
 */
public class WallFunction extends VanillaFunctionImpl<PlayerBlockPlaceEvent> {

    public WallFunction() {
        super(new EventListener<PlayerBlockPlaceEvent>() {
            @Override
            public @NotNull Class<PlayerBlockPlaceEvent> eventType() {
                return PlayerBlockPlaceEvent.class;
            }

            @Override
            public @NotNull Result run(@NotNull PlayerBlockPlaceEvent event) {
                if (VanillaProvider.shouldRun(WallFunction.class) && VanillaUtil.isWall(event.getBlock()))
                    MinecraftServer.getBlockManager().registerBlockPlacementRule(new WallPlacementRule(event.getBlock()));
                return Result.SUCCESS;
            }
        });
    }
}

package net.sacredisle.rpgengine.api.vanilla;

import net.minestom.server.instance.block.Block;

/**
 * Created by Giovanni on 1/6/2022
 */
public class VanillaUtil {

    /**
     * Returns whether a {@link Block} is stairs or not.
     */
    public static boolean isStairs(Block block) {
       return block.name().toLowerCase().contains("stairs");
    }

    /**
     * Returns whether a {@link Block} is a wall/fence or not.
     */
    public static boolean isWall(Block block) {
        return block.name().toLowerCase().contains("wall") || block.name().toLowerCase().contains("fence")
                && !block.name().toLowerCase().contains("banner")
                && !block.name().toLowerCase().contains("sign")
                && !block.name().toLowerCase().contains("torch")
                && !block.name().toLowerCase().contains("skull")
                && !block.name().toLowerCase().contains("coral");
    }
}

package net.sacredisle.rpgengine.api;

import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/5/2022
 */
public interface MOTD {

    /**
     * Returns the first line of the MOTD.
     */
    @NotNull
    TextComponent getFirstLine();

    /**
     * Returns the second line of the MOTD.
     */
    @NotNull
    TextComponent getSecondLine();
}

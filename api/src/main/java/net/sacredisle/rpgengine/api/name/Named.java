package net.sacredisle.rpgengine.api.name;

import net.kyori.adventure.text.TextComponent;

/**
 * Created by Giovanni on 1/4/2022
 *
 * A named object.
 */
public interface Named {

    /**
     * Returns the actual name.
     */
    String getLiveName();

    /**
     * Returns the display name.
     */
    TextComponent getLiveDisplayName();

    /**
     * Sets the display name.
     */
    void setDisplayName(TextComponent str);
}

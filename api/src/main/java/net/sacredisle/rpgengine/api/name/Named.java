package net.sacredisle.rpgengine.api.name;

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
    String getLiveDisplayName();

    /**
     * Sets the display name.
     */
    void setDisplayName(String str);
}

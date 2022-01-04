package net.sacredisle.rpgengine.api;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * Interface for leveling functionality(exp, levels, level up etc).
 */
public interface Advancing {

    /**
     * Returns the current EXP this advancing object has.
     */
    int getRPGExp();

    /**
     * Returns the current level this advancing object has.
     */
    int getRPGLevel();

    /**
     * Sets the current level.
     */
    void setRPGLevel(int level);

    /**
     * Sets the current experience.
     */
    void setRPGExperience(int experience);

    /**
     * Returns the required amount of EXP to reach a certain level.
     *
     * @param level The level to calculate for.
     */
    static int calculatingRequiredExp(int level) {
        return 0; // TODO
    }
}

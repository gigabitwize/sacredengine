package net.sacredisle.rpgengine.api.entity;

import net.sacredisle.rpgengine.api.Advancing;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * Barebones creature interface. Has support for navigation and AI, unlike
 * {@link IRPGEntity}.
 */
public interface IRPGCreature extends IRPGEntity, Advancing { }

package net.sacredisle.engine.api.model.entity;

import net.sacredisle.engine.api.model.Model;

import java.util.List;

/**
 * Created by Giovanni on 1/11/2022
 */
public interface EntityModel extends Model {

    /**
     * Returns this entity model its head {@link Bone}.
     */
    Bone getHead();

    /**
     * Returns this entity models its {@link Bone}s, does not include {@link #getHead()}.
     */
    List<Bone> getBones();
}

package net.sacredisle.engine.api.model.entity;

import java.util.List;

/**
 * Created by Giovanni on 1/11/2022
 */
public interface Bone {

    /**
     * Returns the name of this bone.
     */
    String getName();

    /**
     * Returns a list of child bones this bone may have.
     */
    List<Bone> getChildren();
}

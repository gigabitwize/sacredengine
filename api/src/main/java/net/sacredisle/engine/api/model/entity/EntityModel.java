package net.sacredisle.engine.api.model.entity;

import net.minestom.server.coordinate.Pos;
import net.sacredisle.engine.api.Actor;
import net.sacredisle.engine.api.model.Model;

import java.util.List;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * A 3D custom entity model, has support for animations.
 */
public interface EntityModel extends Model<Pos>, Actor {

    /**
     * Returns this entity model its head {@link Bone}.
     */
    Bone getHead();

    /**
     * Returns this entity models its {@link Bone}s, does not include {@link #getHead()}.
     */
    List<Bone> getBones();

    @Override
    default Type getType() {
        return Type.ENTITY;
    }
}

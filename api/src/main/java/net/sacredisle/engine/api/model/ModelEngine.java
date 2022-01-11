package net.sacredisle.engine.api.model;

import java.util.List;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * The Sacred Engine model engine.
 * <p>
 * Provides functionality for custom 3D items, blocks and
 * custom 3D monsters made using BlockBench. The model engine
 * can directly load BlockBench 3D models and their animations.
 * <p>
 * Animations are done with armor stands.
 */
public interface ModelEngine {

    /**
     * Returns a list of registered {@link Model}s.
     */
    List<Model> getModels();
}

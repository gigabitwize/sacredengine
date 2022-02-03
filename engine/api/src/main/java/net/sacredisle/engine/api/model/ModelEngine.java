package net.sacredisle.engine.api.model;

import net.sacredisle.engine.api.Engine;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
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

    Path DEFAULT_PATH = Path.of(System.getProperty("user.dir") + "/engine" + Engine.BUILD_VERSION + "/sacred3d/models/");

    /**
     * Returns a list of registered {@link Model}s.
     */
    List<Model<?>> getModels();

    /**
     * Returns a {@link Model} by type and name.
     */
    @Nullable Model<?> getModel(Model.Type type, String name);

    /**
     * Returns whether 3D model functionality & processing should be enabled or not.
     */
    boolean isEnabled();

    /**
     * Sets whether 3D model functionality & processing should be enabled or not.
     */
    void setEnabled(boolean b);
}

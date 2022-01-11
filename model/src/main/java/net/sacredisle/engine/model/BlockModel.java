package net.sacredisle.engine.model;

import net.minestom.server.coordinate.Pos;
import net.sacredisle.engine.api.model.Model;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/11/2022
 */
public record BlockModel(String name) implements Model<Pos> {

    public BlockModel(@NotNull String name) {
        this.name = name;
    }

    @Override
    public Pos apply(Pos pos) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return Type.BLOCK;
    }
}

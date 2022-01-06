package net.sacredisle.rpgengine.api.instance.generator;

import net.minestom.server.instance.ChunkGenerator;

/**
 * Created by Giovanni on 1/4/2022
 */
public enum GeneratorType {

    FLAT(FlatGenerator.class),
    OCEAN(OceanGenerator.class),
    VOID(VoidGenerator.class);

    private final Class<? extends ChunkGenerator> clazz;

    GeneratorType(Class<? extends ChunkGenerator> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ChunkGenerator> getGenerator() {
        return clazz;
    }
}

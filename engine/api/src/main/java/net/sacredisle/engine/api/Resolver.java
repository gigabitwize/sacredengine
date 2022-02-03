package net.sacredisle.engine.api;

/**
 * Created by Giovanni on 1/30/2022
 */
public interface Resolver<K, V> extends Iterable<V> {

    /**
     * Resolves a value (V) by key (K).
     */
    V resolve(K k);
}

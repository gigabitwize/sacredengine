package net.sacredisle.rpgengine.api;

import java.util.Iterator;

/**
 * Created by Giovanni on 1/4/2022
 */
public interface Resolver<K, V> {

    Iterator<V> iterate();

    /**
     * Resolves a value (V) by key (K).
     */
    V resolve(K key);
}

package net.sacredisle.rpgengine.api;

/**
 * Created by Giovanni on 1/5/2022
 */
public interface Query<A, B> {

    A getA();

    B getB();

    default boolean hasA() {
        return getA() != null;
    }

    default boolean hasB() {
        return getB() != null;
    }
}

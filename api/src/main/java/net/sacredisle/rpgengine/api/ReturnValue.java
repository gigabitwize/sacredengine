package net.sacredisle.rpgengine.api;

/**
 * Created by Giovanni on 1/6/2022
 */
public interface ReturnValue<A, B> {

    A getFirst();

    B getSecond();
}

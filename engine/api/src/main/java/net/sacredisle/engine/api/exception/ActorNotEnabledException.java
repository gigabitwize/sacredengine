package net.sacredisle.engine.api.exception;

import net.sacredisle.engine.api.Actor;

/**
 * Created by Giovanni on 1/11/2022
 */
public class ActorNotEnabledException extends RuntimeException {

    public ActorNotEnabledException(Actor actor) {
        super("[Actor: " + actor.getClass().getSimpleName() + "] is not enabled");
    }
}

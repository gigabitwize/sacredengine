package net.sacredisle.engine.api.exception;

import net.minestom.server.instance.Instance;

/**
 * Created by Giovanni on 1/11/2022
 */
public class InvalidInstanceException extends RuntimeException {

    public InvalidInstanceException(Class<?> clazz, String message) {
        super(clazz.getSimpleName() + " is invalid, " + message);
    }
}

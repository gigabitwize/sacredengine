package net.sacredisle.engine.api.exception;

/**
 * Created by Giovanni on 1/11/2022
 */
public class EngineNotEnabledException extends RuntimeException {

    public EngineNotEnabledException(Class<?> engineClass, String message) {
        super(engineClass.getSimpleName() + " is not enabled, " + message);
    }
}

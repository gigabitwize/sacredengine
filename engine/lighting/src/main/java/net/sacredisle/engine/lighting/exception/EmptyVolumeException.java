package net.sacredisle.engine.lighting.exception;

/**
 * Created by Giovanni on 1/11/2022
 */
public class EmptyVolumeException extends RuntimeException {

    public EmptyVolumeException() {
        super("Tried to register a volume but it's empty");
    }
}

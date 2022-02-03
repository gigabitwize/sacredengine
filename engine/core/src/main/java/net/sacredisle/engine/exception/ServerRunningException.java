package net.sacredisle.engine.exception;

/**
 * Created by Giovanni on 1/12/2022
 */
public class ServerRunningException extends RuntimeException {

    public ServerRunningException() {
        super("The Sacred Server is already running!");
    }
}

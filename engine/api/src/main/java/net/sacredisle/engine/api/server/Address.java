package net.sacredisle.engine.api.server;

/**
 * Created by Giovanni on 1/12/2022
 */
public record Address(String ip, int port) {

    public Address(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}

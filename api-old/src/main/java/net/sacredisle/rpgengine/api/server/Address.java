package net.sacredisle.rpgengine.api.server;

import java.net.InetSocketAddress;

/**
 * Created by Giovanni on 1/6/2022
 */
public record Address(String ip, int port) {

    public Address(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public InetSocketAddress toSocketAddress() {
        return new InetSocketAddress(ip, port);
    }
}

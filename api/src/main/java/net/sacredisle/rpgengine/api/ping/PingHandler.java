package net.sacredisle.rpgengine.api.ping;

/**
 * Created by Giovanni on 1/5/2022
 */
public interface PingHandler {

    /**
     * Handles an incoming {@link Ping}. This can be a typical Minecraft connection
     * ping coming from a {@link net.minestom.server.network.player.PlayerConnection}
     * or a ping request from another Sacred Isle server.
     */
    Ping.Response handle(Ping ping);
}

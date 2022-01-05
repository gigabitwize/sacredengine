package net.sacredisle.rpgengine.core.skin;

import net.minestom.server.entity.PlayerSkin;
import net.sacredisle.rpgengine.api.Query;
import net.sacredisle.rpgengine.api.Resolver;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Giovanni on 1/5/2022
 * <p>
 * A resolver for player skins. The resolver makes use of
 * {@link PlayerSkin#fromUsername(String)} or {@link PlayerSkin#fromUuid(String)}
 * and caches the result.
 */
public class SkinResolver implements Resolver<Query<UUID, String>, PlayerSkin> {

    private static SkinResolver resolver;
    private static Map<String, PlayerSkin> SKIN_CACHE;

    public SkinResolver() {
        SKIN_CACHE = new ConcurrentHashMap<>();
    }

    @Override
    public Iterator<PlayerSkin> iterate() {
        return SKIN_CACHE.values().iterator();
    }

    @Override
    public PlayerSkin resolve(Query<UUID, String> query) {
        if (query.hasA()) {
            UUID uuid = query.getA();
            if (SKIN_CACHE.containsKey(uuid.toString()))
                return SKIN_CACHE.get(uuid.toString());
            else {
                PlayerSkin playerSkin = PlayerSkin.fromUuid(uuid.toString());
                SKIN_CACHE.put(uuid.toString(), playerSkin);
                return playerSkin;
            }
        } else if (query.hasB()) {
            String userName = query.getB();
            if (SKIN_CACHE.containsKey(userName))
                return SKIN_CACHE.get(userName);
            else {
                PlayerSkin playerSkin = PlayerSkin.fromUsername(userName);
                SKIN_CACHE.put(userName, playerSkin);
                return playerSkin;
            }
        }
        // Query is empty
        return null;
    }

    public static SkinResolver get() {
        if (resolver == null)
            resolver = new SkinResolver();
        return resolver;
    }
}

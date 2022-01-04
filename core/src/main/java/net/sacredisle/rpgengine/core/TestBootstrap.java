package net.sacredisle.rpgengine.core;

import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.network.player.PlayerConnection;
import net.sacredisle.rpgengine.api.exception.AlreadyRunningException;
import net.sacredisle.rpgengine.api.name.Username;
import net.sacredisle.rpgengine.core.command.GenerateInstanceCommand;
import net.sacredisle.rpgengine.core.command.InstancesCommand;
import net.sacredisle.rpgengine.core.command.SwitchInstanceCommand;
import net.sacredisle.rpgengine.core.player.RPGPlayer;
import net.sacredisle.rpgengine.core.player.RPGPlayerProvider;
import org.jetbrains.annotations.NotNull;

import java.net.BindException;
import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class TestBootstrap {

    public static void main(String[] args) {
        try {
            RPGEngine rpgEngine = new RPGEngine(new String[]{
                    "localhost",
                    "25565",
                    "ocean",
                    "debug"
            }, new RPGPlayerProvider());

            rpgEngine.registerCommand(new GenerateInstanceCommand());
            rpgEngine.registerCommand(new SwitchInstanceCommand());
            rpgEngine.registerCommand(new InstancesCommand());
        } catch (BindException | AlreadyRunningException e) {
            e.printStackTrace();
        }
    }
}

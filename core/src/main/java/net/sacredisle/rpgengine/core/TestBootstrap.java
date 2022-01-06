package net.sacredisle.rpgengine.core;

import net.sacredisle.rpgengine.api.ReturnValue;
import net.sacredisle.rpgengine.api.exception.AlreadyRunningException;
import net.sacredisle.rpgengine.api.exception.ConnectionNotReadyException;
import net.sacredisle.rpgengine.api.server.Address;
import net.sacredisle.rpgengine.api.server.Server;
import net.sacredisle.rpgengine.core.command.EntityCommand;
import net.sacredisle.rpgengine.core.command.GenerateInstanceCommand;
import net.sacredisle.rpgengine.core.command.InstancesCommand;
import net.sacredisle.rpgengine.core.command.SwitchInstanceCommand;
import net.sacredisle.rpgengine.core.connection.ConnectionOpener;
import net.sacredisle.rpgengine.core.connection.OpenConnection;
import net.sacredisle.rpgengine.core.player.RPGPlayerProvider;

import java.net.BindException;

/**
 * Created by Giovanni on 1/4/2022
 * <p>
 * This is a test class.
 */
public class TestBootstrap {

    public static void main(String[] args) {
        OpenConnection.LOG.info("Setting up connection, please wait..");

        Server.Connection connection = null;
        ReturnValue<ConnectionOpener.Result, OpenConnection> status = ConnectionOpener.open(connection, new Address("localhost", 25565), true);
        if (status.getFirst().isSuccess())
            connection = status.getSecond();
        else {
            OpenConnection.LOG.error("Connection failed, reason: " + status.getFirst().name());
            return;
        }
        OpenConnection.LOG.info("Connection opened! Environment: LOCAL");

        try {
            RPGEngine rpgEngine = new RPGEngine(
                    connection,
                    new String[]{"ocean"},
                    new RPGPlayerProvider());

            rpgEngine.registerCommand(new GenerateInstanceCommand());
            rpgEngine.registerCommand(new SwitchInstanceCommand());
            rpgEngine.registerCommand(new InstancesCommand());
            rpgEngine.registerCommand(new EntityCommand());
        } catch (BindException | AlreadyRunningException | ConnectionNotReadyException e) {
            e.printStackTrace();
        }
    }
}

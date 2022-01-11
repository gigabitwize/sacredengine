package net.sacredisle.rpgengine.core.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;
import net.minestom.server.permission.Permission;
import net.sacredisle.rpgengine.api.util.Classes;
import net.sacredisle.rpgengine.api.instance.generator.FlatGenerator;
import net.sacredisle.rpgengine.api.instance.generator.Generator;
import net.sacredisle.rpgengine.api.instance.generator.GeneratorType;
import net.sacredisle.rpgengine.core.RPGEngine;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import net.sacredisle.rpgengine.core.permission.CommandPermissions;

import java.util.UUID;

/**
 * Created by Giovanni on 1/4/2022
 */
public class GenerateInstanceCommand extends RPGCommand {

    public GenerateInstanceCommand() {
        super("generate", "gi", "gen-inst", "gen");

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("");
            sender.sendMessage(Component.text("Incorrect syntax, use: /generate <name> <type>").color(NamedTextColor.RED));
        });

        ArgumentString argumentString = ArgumentType.String("name");
        ArgumentEnum<GeneratorType> typeArg = ArgumentType.Enum("type", GeneratorType.class);
        addSyntax((sender, context) -> {
            String instanceName = context.get(argumentString);
            GeneratorType generatorType = context.get(typeArg);
            Generator generator = null;

            if (generatorType == GeneratorType.FLAT)
                generator = new FlatGenerator(Block.SANDSTONE);
            else generator = (Generator) Classes.newInstance(generatorType.getGenerator());

            if (generator == null) {
                sender.sendMessage("");
                sender.sendMessage(Component.text("Error initializing generator class for " + context.get(typeArg)).color(NamedTextColor.RED));
                return;
            }

            RPGWorldInstance newInstance = new RPGWorldInstance(
                    UUID.randomUUID(),
                    RPGEngine.DEFAULT_DIMENSION,
                    null,
                    new Pos(0, generator.getSpawnY(), 0),
                    instanceName, null);
            newInstance.setChunkGenerator(generator);
            newInstance.setTime(13000);
            MinecraftServer.getInstanceManager().registerInstance(newInstance);

            sender.sendMessage("");
            sender.sendMessage(
                    Component
                            .text("Done! New instance").color(NamedTextColor.GREEN).append(Component
                                    .text("(" + instanceName + ")").color(NamedTextColor.DARK_AQUA).append(Component
                                            .text(" has been created").color(NamedTextColor.GREEN))));
        }, argumentString, typeArg);
    }

    @Override
    public Permission getPermission() {
        return CommandPermissions.EXEC_MANAGE_INSTANCES;
    }
}
